package org.hbhk.rss.core.server.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.rss.core.server.context.UserContext;
import org.hbhk.rss.core.server.service.IUserService;
import org.hbhk.rss.core.server.service.IWeatherService;
import org.hbhk.rss.core.shared.consts.UserRequestMenu;
import org.hbhk.rss.weixinapi.server.handle.HandleMessageAdapter;
import org.hbhk.rss.weixinapi.server.msg.Msg4Text;
import org.hbhk.rss.weixinapi.server.security.DefaultSession;
import org.hbhk.rss.weixinapi.server.security.MySecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	// TOKEN 是你在微信平台开发模式中设置的哦
	public static final String TOKEN = "hbhk_token";

	@Autowired
	private IUserService userService;
	@Autowired
	private IWeatherService weatherService;
	private Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public void auth(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		log.info("accept auth:" + echostr);
		// 重写totring方法，得到三个参数的拼接字符串
		List<String> list = new ArrayList<String>(3) {
			private static final long serialVersionUID = 2621444383666420433L;

			public String toString() {
				return this.get(0) + this.get(1) + this.get(2);
			}
		};
		list.add(TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);// 排序
		String tmpStr = new MySecurity().encode(list.toString(),
				MySecurity.SHA_1);// SHA-1加密
		Writer out = response.getWriter();
		if (signature.equals(tmpStr)) {
			out.write(echostr);// 请求验证成功，返回随机码
		} else {
			out.write("");
		}
		out.flush();
		out.close();
		log.info("reply auth:" + echostr);
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public void dealMsg(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		InputStream is = UserContext.getCurrentContext().getInputStream();
		OutputStream os = response.getOutputStream();

		log.info("start accept msg");
		final DefaultSession session = DefaultSession.newInstance();
		session.addOnHandleMessageListener(new HandleMessageAdapter() {
			@Override
			public void onTextMsg(Msg4Text msg) {
				String msgStr = msg.getContent();
				log.info("收到微信消息：" + msgStr);
				String currUser = msg.getFromUserName();
				String usermenu = msgStr.trim();
				if (UserRequestMenu.menus.contains(usermenu)) {
					userService.saveCurrMenu(currUser, usermenu);
				}
				String menu = userService.getCurrMenu(currUser);

				if (menu == null || menu.equals("")) {
					getMainMenu(session);
					return;
				}
				if (UserRequestMenu.menus.contains(usermenu)
						&& !usermenu.equals(menu)) {
					sendMsgInfo("如需选择其他菜单,请输入r返回上一级菜单,e返回主菜单", session);
					return;
				}
				if (usermenu.equals("r")) {
					sendMsgInfo("返回上一级菜单", session);
					userService.returnlastMenu(currUser);
					return;
				}
				if (usermenu.equals("e")) {
					userService.removeCurrMenu(currUser);
					getMainMenu(session);
					return;
				}
				if (UserRequestMenu.menus.contains(msgStr)) {
					StringBuilder menus = new StringBuilder();
					menus.append("欢迎使用hbhk查询系统:因有所有!\n");
					if (UserRequestMenu.weather.equals(msgStr)) {
						menus.append("[1].天气预报\n");
						menus.append("请输入城市名称进行查询\n");
					} else {
						menus.append("[2].快递查询\n");
						menus.append("请输入快递公司名称+运单号 例如:申通快递+123456789\n");
					}
					sendMsgInfo(menus.toString(), session);
					return;
				}
				if (msgStr != menu) {
					// 天气预报
					if (UserRequestMenu.weather.equals(menu)) {
						weatherService.getBaiduWeatherToXml(msgStr, session);
					}
					// 快递查询
					if (UserRequestMenu.weather.equals(menu)) {
					}
				}

			}
		});

		// 必须调用这两个方法
		// 如果不调用close方法，将会出现响应数据串到其它Servlet中。
		session.process(is, os);// 处理微信消息
		log.info("reply msg");
		session.close();// 关闭Session
	}

	private void sendMsgInfo(String msg, DefaultSession session) {
		Msg4Text rmsg = new Msg4Text();
		rmsg.setFromUserName(UserContext.getCurrentContext().getMaster());
		rmsg.setToUserName(UserContext.getCurrentContext().getCurrentUserName());
		rmsg.setContent(msg);
		session.callback(rmsg);
	}

	private void getMainMenu(DefaultSession session) {
		StringBuilder menus = new StringBuilder();
		menus.append("欢迎使用hbhk查询系统:因有所有!回复对应数字进行体验.\n");
		menus.append("[1].天气预报\n");
		menus.append("[2].快递查询\n");
		sendMsgInfo(menus.toString(), session);
	}

}
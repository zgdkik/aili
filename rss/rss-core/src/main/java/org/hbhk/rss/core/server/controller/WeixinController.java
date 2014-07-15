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
import org.hbhk.rss.core.shared.consts.UserRequestType;
import org.marker.weixin.DefaultSession;
import org.marker.weixin.HandleMessageAdapter;
import org.marker.weixin.MySecurity;
import org.marker.weixin.msg.Msg4Text;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	// TOKEN 是你在微信平台开发模式中设置的哦
	public static final String TOKEN = "hbhk_token";

	private Log  log = LogFactory.getLog(getClass());
	@RequestMapping(value ="/auth",method=RequestMethod.GET)
	public void auth(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		log.info("accept auth:"+echostr);
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
		log.info("reply auth:"+echostr);
	}

	@RequestMapping(value ="/auth",method=RequestMethod.POST)
	public void dealMsg(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		InputStream is = request.getInputStream();
		OutputStream os = response.getOutputStream();
		log.info("start accept msg");
		final DefaultSession session = DefaultSession.newInstance();
		session.addOnHandleMessageListener(new HandleMessageAdapter() {
			@Override
			public void onTextMsg(Msg4Text msg) {
				String msgStr = msg.getContent();
				//天气预报
				if(UserRequestType.weather.equals(msgStr)){
					
				}
				//快递查询
				if(UserRequestType.weather.equals(msgStr)){
					
				}
				
				log.info("收到微信消息：" + msg.getContent());
				Msg4Text rmsg = new Msg4Text();
				rmsg.setFromUserName(msg.getToUserName());
				rmsg.setToUserName(msg.getFromUserName());
				StringBuilder  menu= new StringBuilder();
				menu.append("欢迎使用hbhk查询系统:因有所有!\n");
				menu.append("[1].天气预报\n");
				menu.append("[2].快递查询\n");
				rmsg.setContent(menu.toString());
				session.callback(rmsg);
//				// 回复一条消息
//				Data4Item d1 = new Data4Item("hbhk", "测试描述",
//						"http://cms.yl-blog.com/themes/blue/images/logo.png",
//						"cms.yl-blog.com");
//				Data4Item d2 = new Data4Item(
//						"hbhk",
//						"测试描述",
//						"http://www.yl-blog.com/template/ylblog/images/logo.png",
//						"www.yl-blog.com");
//				Msg4ImageText mit = new Msg4ImageText();
//				mit.setFromUserName(msg.getToUserName());
//				mit.setToUserName(msg.getFromUserName());
//				mit.setCreateTime(msg.getCreateTime());
//				mit.addItem(d1);
//				mit.addItem(d2);
//				session.callback(mit);

			}
		});

		// 必须调用这两个方法
		// 如果不调用close方法，将会出现响应数据串到其它Servlet中。
		session.process(is, os);// 处理微信消息
		log.info("reply msg");
		session.close();// 关闭Session
	}

}
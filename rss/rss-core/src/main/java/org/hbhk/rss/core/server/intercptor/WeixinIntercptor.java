package org.hbhk.rss.core.server.intercptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.rss.core.server.context.UserContext;
import org.hbhk.rss.core.server.service.IUserService;
import org.hbhk.rss.core.shared.pojo.UserMsgLogEntity;
import org.hbhk.rss.weixinapi.server.msg.Msg4Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WeixinIntercptor extends HandlerInterceptorAdapter {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IUserService userService;

	private static DocumentBuilder builder;
	static {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestMethod= request.getMethod();
		if(!requestMethod.equals("POST")){
			return true;
		}
		InputStream is = request.getInputStream();
		UserContext.setInputStream(is);
		UserContext  cache =UserContext.getCurrentContext();
		InputStream logis =cache.getInputStream();
		InputStream currgis =cache.getInputStream();
		try {
			org.w3c.dom.Document document = builder.parse(currgis);
			Msg4Head head = new Msg4Head();
			head.read(document);
			UserContext.setCurrentUserName(head.getFromUserName());
			UserContext.setMaster(head.getToUserName());
			UserContext.setCurrentMsg4Head(head);

			UserMsgLogEntity logEntity = new UserMsgLogEntity();
			logEntity.setMsg(getUserMsg(logis));
			userService.saveUserMsgLog(logEntity);
			log.info("current user:" + head.getFromUserName());
		} catch (Exception e) {
			log.error("解析微信消息头出错", e);
			return false;
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		UserContext.remove();
	}

	private String getUserMsg(InputStream is) {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder("");
		try {
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
		} catch (IOException e) {
			log.error("解析InputStream出错", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				log.error("解析InputStream出错", e);
			}
		}
		log.info("微信内容："+sb.toString());
		return sb.toString();
	}

}

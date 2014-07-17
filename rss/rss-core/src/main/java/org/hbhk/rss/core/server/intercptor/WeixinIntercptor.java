package org.hbhk.rss.core.server.intercptor;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.rss.core.server.context.UserContext;
import org.hbhk.rss.weixinapi.server.msg.Msg4Head;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WeixinIntercptor extends HandlerInterceptorAdapter {

	private Log log = LogFactory.getLog(getClass());
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
		InputStream is = request.getInputStream();
		org.w3c.dom.Document document = builder.parse(is);
		Msg4Head head = new Msg4Head();
		head.read(document);
		UserContext.setCurrentUserName(head.getFromUserName());
		log.info("current user:"+head.getFromUserName());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		UserContext.remove();
	}

}

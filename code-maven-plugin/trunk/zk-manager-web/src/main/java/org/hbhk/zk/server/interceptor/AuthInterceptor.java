package org.hbhk.zk.server.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.I0Itec.zkclient.ZkClient;
import org.dom4j.Document;
import org.dom4j.Element;
import org.hbhk.zk.server.context.RequestContext;
import org.hbhk.zk.server.controller.ZkClientController;
import org.hbhk.zk.share.model.User;
import org.hbhk.zk.share.util.CookieUtil;
import org.hbhk.zk.share.util.FileLoadUtil;
import org.hbhk.zk.share.util.XmlTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	public static final Logger log = LoggerFactory
			.getLogger(AuthInterceptor.class);

	public static List<User> users = new ArrayList<User>();

	public static final String user_session_key = "user_session_key";

	@Value("#{zk['zk.host']}")
	private String zkHost;
	@Value("#{zk['zk.root']}")
	private String root;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		loadUsers();
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute(user_session_key);
		String uri = request.getRequestURI();
		log.debug(uri);
		String base = request.getContextPath();
		request.setAttribute("base", base);
		if(uri.indexOf("JSESSIONID")==-1){
			if (StringUtils.isEmpty(user) && !uri.endsWith("/login")) {
				response.sendRedirect(base + "/login");
				return false;
			}
		}
		if(!uri.endsWith("/login")){
			Cookie cookie =  CookieUtil.getCookie(request, "zkHost");
			Cookie cookieRoot =  CookieUtil.getCookie(request, "root");
			request.setAttribute("zkHost", zkHost);
			request.setAttribute("zkRoot", root);
			RequestContext.setZkHost(zkHost);
			RequestContext.setRoot(root);
		
			if(cookie!=null){
				String zkHost = cookie.getValue();
				if(!StringUtils.isEmpty(zkHost)){
					RequestContext.setZkHost(zkHost);
					request.setAttribute("zkHost", zkHost);
					Object val = RequestContext.getSession().getAttribute("zkClient");
					if(val==null){
						ZkClient zkC = new ZkClient(zkHost,25000);
						if(ZkClientController.zkMap.containsKey(zkHost)){
							zkC = ZkClientController.zkMap.get(zkHost);
						}else{
							zkC = new ZkClient(zkHost, 25000);
							ZkClientController.zkMap.put(zkHost, zkC);
						}
						RequestContext.setZkClient(zkC);
					}
				}
			}
			if(cookieRoot!=null){
				String zkRoot = cookieRoot.getValue();
				if(!StringUtils.isEmpty(zkRoot)){
					RequestContext.setRoot(zkRoot);
					request.setAttribute("zkRoot", zkRoot);
				}
			}
		}
	
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	@SuppressWarnings("unchecked")
	private void loadUsers() throws IOException {
		if (users.size() > 0) {
			return;
		}
		Resource[] resources = FileLoadUtil
				.getResourcesForClasspathByPath("users.xml");
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				Document doc = XmlTool.read(resource.getURL());
				Element ele = doc.getRootElement();
				List<Element> users = ele.elements();
				for (Element element : users) {
					List<Element> userele = element.elements();
					User user = new User();
					for (Element element2 : userele) {
						String name = element2.getName();
						String value = XmlTool.getElementValue(element2);
						if (name.equals("user")) {
							user.setUser(value);
						} else {
							user.setPwd(value);
						}
					}
					AuthInterceptor.users.add(user);
				}
			}
		}

	}
}

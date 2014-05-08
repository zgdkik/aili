package org.hbhk.aili.security.server.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.core.server.annotation.SecurityFilter;
import org.hbhk.aili.core.server.web.WebApplicationContextHolder;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.UserConstants;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

public class SecurityInterceptor implements HandlerInterceptor {

	private Log log = LogFactory.getLog(getClass());
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	private IUserService userService;

	private static final String loginurl = "/security/loginpage.ctrl";

	private static Set<String> notSecurityUrl = new HashSet<String>();
	static {
		notSecurityUrl.add(loginurl);
		notSecurityUrl.add("/security/validateCode.ctrl");
		notSecurityUrl.add("/security/login.ctrl");

	}

	@SuppressWarnings("unchecked")
	public void validateCount(HttpServletRequest request,
			HttpServletResponse response, String username)
			throws ServletException, IOException {
		String loginUser = null;
		if (StringUtils.isEmpty(username)) {
			loginUser = (String) request.getParameter("username");
		} else {
			loginUser = username;
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/security/authorizeError.ctrl");
		if (StringUtils.isEmpty(username)) {
			// 得到application验证用户登录次数
			ServletContext application = request.getSession()
					.getServletContext();
			List<String> countStr = (List<String>) application
					.getAttribute(UserConstants.USER_INFO_COUNT);
			List<String> userCount = new ArrayList<String>();
			if (!CollectionUtils.isEmpty(countStr)
					&& !StringUtils.isEmpty(loginUser)) {
				for (String user : countStr) {
					if (user.equals(loginUser)) {
						log.info("username:" + user);
						userCount.add(user);
					}
				}
			}
			Integer count = (Integer) application
					.getAttribute(UserConstants.USER_LOGIN_COUNT);
			if (userCount.size() > 0 && count != null
					&& userCount.size() >= count) {
				request.setAttribute("errorMsg", "你(" + loginUser
						+ ")已经在别处登录,服务器只允许" + count + "次登录!");
				dispatcher.forward(request, response);
				return;
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return false;
		}
		HandlerMethod method = (HandlerMethod) handler;
		SecurityFilter annotation = (SecurityFilter) method
				.getMethodAnnotation(org.hbhk.aili.core.server.annotation.SecurityFilter.class);

		if (annotation != null && annotation.value() == false) {
			return true;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginurl);
		// 用户请求URL
		String url = urlPathHelper.getLookupPathForRequest(request);
		if (notSecurityUrl.contains(url)) {
			return true;
		}
		String username = (String) request.getSession().getAttribute(
				UserConstants.CURRENT_USER_NAME);
		if (StringUtils.isEmpty(username)) {
			request.setAttribute("errorMsg", "你还没有登录");
			dispatcher.forward(request, response);
			return false;
		}
		if (userService == null) {
			userService = (IUserService) WebApplicationContextHolder
					.getWebApplicationContext().getBean("userService");
		}
		boolean auth = userService.validate(url, username);
		// 是否有权限
		if (auth == true) {
			return true;
		} else {
			dispatcher = request.getRequestDispatcher("/security/error.ctrl");
			request.setAttribute("errorMsg", "请求的URL不存在或没有权限访问!");
			dispatcher.forward(request, response);
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

}

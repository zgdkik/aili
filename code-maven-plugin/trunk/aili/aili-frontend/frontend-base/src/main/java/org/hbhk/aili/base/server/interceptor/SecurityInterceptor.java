package org.hbhk.aili.base.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hbhk.aili.base.shared.exception.UserNotLoginException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/user/login";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String accessURL = request.getServletPath();
		try {
			if(!loginUrl.equals(accessURL)){
				SecurityAccessor.checkURLAccessSecurity(accessURL);
			}
		} catch (UserNotLoginException e) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + loginUrl);
			return false;
		}
		return true;
	}

	public String getLoginUrl() {
		return loginUrl;
	}
	

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}

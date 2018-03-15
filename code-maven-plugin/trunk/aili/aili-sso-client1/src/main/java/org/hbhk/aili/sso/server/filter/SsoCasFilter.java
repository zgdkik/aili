package org.hbhk.aili.sso.server.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;

public class SsoCasFilter extends CasFilter {
	
	private String successUrl;

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		HttpSession session = httpRequest.getSession();
		CasToken  casToken = (CasToken) token;
		if (casToken != null) {
			String userName = (String) casToken.getPrincipal();
			String  ticket = (String) casToken.getCredentials();
			if (StringUtils.isNotEmpty(userName)) {
				// 设置用户信息
				session.setAttribute("userName", userName);
				session.setAttribute("ticket", ticket);
			}
		}
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String contextPath = httpRequest.getContextPath();
		httpResponse.sendRedirect(contextPath + successUrl);
		return true;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	
	

}

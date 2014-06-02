package org.hbhk.mvc.server.ant;

import javax.servlet.http.HttpServletRequest;

public class AntUrlMatcher {
	HttpServletRequest request;

	public AntUrlMatcher(HttpServletRequest request) {
		this.request = request;
	}

	public String getContextUrl() {
		return request.getContextPath();
	}

	public String getRequestUrl() {
		String url = request.getRequestURI();
		url = url.substring(getContextUrl().length(), url.length());
		return url;
	}
}

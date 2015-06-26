package org.hbhk.aili.core.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AppContextFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	/**
	 * 设置应用信息
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

	}

	@Override
	public void destroy() {

	}
}

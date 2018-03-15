package org.hbhk.aili.sso.server.filter;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

public class SsoFilter implements Filter {
	public static final Logger log = LoggerFactory.getLogger(SsoFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		httpRequest.setAttribute("ticket", session.getAttribute("ticket"));
	}

	@Override
	public void destroy() {

	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(DigestUtils.md5Digest("qqqqqq".getBytes()) + ":"
				+ DigestUtils.md5DigestAsHex("qqqqqq".getBytes()));

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	}
}

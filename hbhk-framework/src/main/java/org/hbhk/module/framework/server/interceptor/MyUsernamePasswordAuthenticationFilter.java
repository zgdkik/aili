package org.hbhk.module.framework.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hbhk.module.framework.server.context.UserConstants;
import org.hbhk.module.framework.server.service.impl.UserCacheImpl;
import org.hbhk.module.framework.shared.domain.UserEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
    public static final String remember_me ="_spring_security_remember_me";
    
    private UserCacheImpl  userCache;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}
		// 检测验证码
		checkValidateCode(request);

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request);
		String remember_me = request.getParameter("_spring_security_remember_me");

		// 验证用户账号与密码是否对应
        //数据库获取 TODO 
		UserEntity users = userCache.getUser(username);
		//users.setPassword("135246");

		if (users == null || !users.getPassword().equals(password)) {
			throw new AuthenticationServiceException("用户名或者密码错误！");
		}

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		// Place the last username attempted into HttpSession for views

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected void checkValidateCode(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		// 让上一次的验证码失效
		session.setAttribute(UserConstants.VALIDATECODE_SESSION_KEY, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter)
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(UserConstants.VALIDATECODE_SESSION_KEY);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(UserConstants.VALIDATECODE_SESSION_KEY);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

	public UserCacheImpl getUserCache() {
		return userCache;
	}

	public void setUserCache(UserCacheImpl userCache) {
		this.userCache = userCache;
	}

	
}

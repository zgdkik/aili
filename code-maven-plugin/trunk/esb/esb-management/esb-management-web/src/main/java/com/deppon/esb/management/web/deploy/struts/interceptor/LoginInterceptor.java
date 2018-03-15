package com.deppon.esb.management.web.deploy.struts.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.stereotype.Component;

import com.deppon.esb.management.user.domain.SysUserInfo;
import com.deppon.esb.management.user.service.ISysUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

@Component("loginInterceptor")
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -5798470957418616547L;
	public static final String USER_SESSION_KEY = "esb.session.user";
	public static final String COOKIE_REMEMBERME_KEY = "esb.cookie.rememberme";
	public static final String GOING_TO_URL_KEY = "GOING_TO";

	@Resource
	private ISysUserService sysUserService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext
				.get(StrutsStatics.HTTP_REQUEST);
		Map session = actionContext.getSession();
		if (session != null && session.get(USER_SESSION_KEY) != null) {
			request.setAttribute("currentUserName", ((SysUserInfo) session
					.get(LoginInterceptor.USER_SESSION_KEY)).getUserName());
			return invocation.invoke();
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (COOKIE_REMEMBERME_KEY.equals(cookie.getName())) {
					String value = cookie.getValue();
					if (StringUtils.isNotBlank(value)) {
						String[] split = value.split("==");
						String userName = split[0];
						String password = split[1];
						SysUserInfo sysUserInfo = sysUserService.attemptLogin(
								userName, password);
						if (sysUserInfo != null) {
							if (session != null) {
								session.put(USER_SESSION_KEY, sysUserInfo);
							}
						} else {
							setGoingToURL(session, invocation);
							return "login";
						}
					} else {
						setGoingToURL(session, invocation);
						return "login";
					}
					return invocation.invoke();
				}
			}
		}
		setGoingToURL(session, invocation);
		return "login";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setGoingToURL(Map session, ActionInvocation invocation) {
		String url = "";
		String namespace = invocation.getProxy().getNamespace();
		if (StringUtils.isNotBlank(namespace) && !namespace.equals("/")) {
			url = url + namespace;
		}
		String actionName = invocation.getProxy().getActionName();
		if (StringUtils.isNotBlank(actionName)) {
			url = url + "/" + actionName + ".action";
		}
		session.put(GOING_TO_URL_KEY, url);
	}

}

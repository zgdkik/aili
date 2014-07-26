package org.hbhk.maikkr.core.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.security.server.annotation.NeedLogin;
import org.hbhk.aili.security.server.cache.UserCache;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/user/loginpage.htm";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return false;
		}
		HandlerMethod method = (HandlerMethod) handler;
		NeedLogin needLogin = (NeedLogin) method
				.getMethodAnnotation(NeedLogin.class);

		if (needLogin == null) {
			needLogin = method.getMethod().getDeclaringClass()
					.getAnnotation(NeedLogin.class);
		}
		if (needLogin != null) {
			String currentUser = (String) RequestContext.getSession()
					.getAttribute(UserConstants.CURRENT_USER_NAME);
			if (StringUtils.isNotEmpty(currentUser)) {
				request.setAttribute("cuser", currentUser);
				UserInfo user = (UserInfo) CacheManager.getInstance()
						.getCache(UserCache.cacheID).get(currentUser);
				request.setAttribute("cuserName", user.getName());
				return true;
			}
			// 非ajax的请求
			// 才需要保存iback_url做为登录后的跳转地址
			if (request.getHeader("X-Requested-With") == null) {
				// request.getSession().setAttribute("BACK_URL",
				// URLEncoder.encode(uri, "UTF-8"));
				String url = needLogin.returnUrl();
				if (StringUtils.isNotEmpty(url)) {
					response.sendRedirect(request.getContextPath() + url);
				} else {
					response.sendRedirect(request.getContextPath() + loginUrl);
				}
				return false;
			} else {
				response.sendError(900);
				return false;
			}

		}

		return true;
	}

}

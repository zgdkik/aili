package org.hbhk.maikkr.core.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.security.server.cache.UserCache;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/user/loginpage.htm";

	@Autowired
	private IBlogService blogService;

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
		String currentUser = (String) RequestContext.getSession().getAttribute(
				UserConstants.CURRENT_USER_NAME);
		if (needLogin != null) {
			if (StringUtils.isNotEmpty(currentUser)) {
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

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String currentUser = (String) RequestContext.getSession().getAttribute(
				UserConstants.CURRENT_USER_NAME);
		request.setAttribute("cuser", currentUser);
		if (StringUtils.isNotEmpty(currentUser)) {
			UserInfo user = (UserInfo) CacheManager.getInstance()
					.getCache(UserCache.cacheID).get(currentUser);
			request.setAttribute("cuserName", user.getName());
			int tc = blogService.getUserThemeCount();
			int ac = blogService.getUserAttentionCount();
			request.setAttribute("tc", tc);
			request.setAttribute("ac", ac);
			user.setPassword(null);
			request.setAttribute("userInfo", user);
			request.setAttribute("head", user.getUserHeadImg());
		} else {
			request.setAttribute("tc", 0);
			request.setAttribute("ac", 0);
			request.setAttribute("userInfo", null);
			request.setAttribute("cuserName", null);
			request.setAttribute("head", null);
		}
		String returnUrl = request.getServletPath();
		String strBackUrl = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath() // 项目名称
				+ request.getServletPath(); // 请求页面或其他地址
		if (!loginUrl.equals(returnUrl) && !returnUrl.endsWith(".jsp")
				&& modelAndView != null && modelAndView.getViewName() != null
				&& !returnUrl.equals("/security/logout.htm")) {
			request.getSession().setAttribute("returnUrl", strBackUrl);
		}
		super.postHandle(request, response, handler, modelAndView);
	}
}

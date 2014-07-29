package org.hbhk.maikkr.backend.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.maikkr.backend.server.annotation.NeedLogin;
import org.hbhk.maikkr.backend.server.service.IAdminService;
import org.hbhk.maikkr.backend.shared.pojo.AdminConstants;
import org.hbhk.maikkr.backend.shared.pojo.AdminInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminIntercptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/backend/loginpage.htm";

	@Autowired
	private IAdminService adminService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return false;
		}
		// 只拦截后台url
		String requestUrl = request.getServletPath();
		if (!requestUrl.startsWith("/backend")) {
			return true;
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
					.getAttribute(AdminConstants.adminkey);
			if (StringUtils.isNotEmpty(currentUser)) {
				request.setAttribute("cuser", currentUser);
				AdminInfo admin = new AdminInfo();
				admin.setEmail(currentUser);
				AdminInfo user = adminService.get(admin);
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
package org.hbhk.maikkr.core.server.interceptor;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.core.share.util.PropertiesUtil;
import org.hbhk.aili.security.server.cache.UserCache;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.hbhk.maikkr.core.server.service.ISiteInfoService;
import org.hbhk.maikkr.core.shared.pojo.SiteInfo;
import org.hbhk.maikkr.user.server.service.IBlogService;
import org.hbhk.maikkr.user.server.service.ICareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/user/loginpage.htm";
	
	private static List<String> nourls = new ArrayList<String>();
	static{
		nourls.add("/user/loginpage.htm");
		nourls.add("/user/register.htm");
	}

	@Autowired
	private IBlogService blogService;
	@Autowired
	private ICareService careService;
	@Autowired
	private ISiteInfoService siteInfoService;

	public static SiteInfo siteInfo = null;

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
		String code = request.getParameter("code");
		if(code != null){
			request.setAttribute("code", code.toLowerCase());
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
				request.getSession().setAttribute("returnUrl", request.getServletPath());
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
		// 设置网站信息
		if (siteInfo == null) {
			SiteInfo site = new SiteInfo();
			site.setStatus(1);
			siteInfo = siteInfoService.getOne(site);
		}

		request.setAttribute("siteInfo", siteInfo);
		request.setAttribute("file_server",
				PropertiesUtil.getPValue("file.server"));
		String currentUser = (String) RequestContext.getSession().getAttribute(
				UserConstants.CURRENT_USER_NAME);
		request.setAttribute("cuser", currentUser);
		if (StringUtils.isNotEmpty(currentUser)) {
			UserInfo user = (UserInfo) CacheManager.getInstance()
					.getCache(UserCache.cacheID).get(currentUser);
			String nickName =user.getNickName();
			if(nickName != null){
				request.setAttribute("cuser", nickName);
			}
			request.setAttribute("cuserName", user.getName());
			int tc = blogService.getUserThemeCount();
			// int ac = blogService.getUserAttentionCount();
			int careCount = careService.myCareCount();
			request.setAttribute("tc", tc);
			request.setAttribute("ac", careCount);
			user.setPassword(null);
			request.setAttribute("userInfo", user);
			String head = user.getUserHeadImg();
			request.setAttribute("head", head);
		} else {
			request.setAttribute("tc", 0);
			request.setAttribute("ac", 0);
			request.setAttribute("userInfo", null);
			request.setAttribute("cuserName", null);
			request.setAttribute("head", null);
			request.setAttribute("careCount", 0);
		}
		
		int port = request.getServerPort();
		String strBackUrl = "http://" + request.getServerName() // 服务器地址
				+ ":" + (port != 80 ? port : "") // 端口号
				+ request.getContextPath() // 项目名称
				+ request.getServletPath(); // 请求页面或其他地址
		String returnUrl = request.getServletPath();
		if (!nourls.contains(returnUrl) && !returnUrl.endsWith(".jsp")
				&& modelAndView != null && modelAndView.getViewName() != null
				&& !returnUrl.equals("/security/logout.htm")) {
			if ("/user/main".equals(returnUrl)) {
				URI uri = new URI(strBackUrl);
				String host = "http://" + uri.getHost();
				if (host.equals("localhost") || host.startsWith("127")) {
					if (port != 80) {
						host = host + ":" + uri.getPort();
					}
				}
				request.getSession().setAttribute("returnUrl", host);
			} else {
				request.getSession().setAttribute("returnUrl", strBackUrl);
			}

		}
		super.postHandle(request, response, handler, modelAndView);
	}

	public static void main(String[] args) {
		String ss = "files/userImages\\a1024784402\\a1408253331514.jpg";
		System.out.println(ss);
		System.out.println(ss.replaceAll("\\\\", "/"));
	}
}

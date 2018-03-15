package org.hbhk.aili.base.server.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.base.server.context.AppContext;
import org.hbhk.aili.base.server.context.UserContext;
import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.constants.SystemParameterConstants;
import org.hbhk.aili.base.share.constants.UserConstants;
import org.hbhk.aili.base.share.entity.IUser;
import org.hbhk.aili.base.share.entity.IUserMenu;
import org.hbhk.aili.base.share.exception.UserNotLoginException;
import org.hbhk.aili.base.share.util.SystemParameterUtil;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.share.ex.BusinessException;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/user/login";
	
	public static List<String> notUrl = new ArrayList<>();
	static{
		notUrl.add("/user/login");
		notUrl.add("/errors/404");
		notUrl.add("/errors/error");
		notUrl.add("/user/getCaptcha");
		notUrl.add("/user/retrievePassword");
		notUrl.add("/user/login");
		notUrl.add("/user/forget");
		notUrl.add("/mobile/login");
		notUrl.add("/mobile/logout");
		notUrl.add("/mobile/update");
		notUrl.add("/mobile/download");
	}


	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String accessURL = request.getServletPath();
		try {
			if (!notUrl.contains(accessURL)) {
				SecurityAccessor.checkURLAccessSecurity(accessURL);
			}
		} catch (UserNotLoginException e) {
			String contentType = request.getHeader(FrontendConstants.CONTENT_TYPE);
			if(MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)) {
				throw  new BusinessException("会话已失效,请重新登录	");
			}else{
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + loginUrl);
			}
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String accessURL = request.getServletPath();
		request.setAttribute("url", accessURL);
		if (!isAjax(request)&&!notUrl.contains(accessURL)) {
			// 设置用户菜单
			ICache<String, List<IUserMenu>> cache = CacheManager.getInstance().getCache(UserConstants.USER_MENU_LIST_CACHE_ID);
			List<IUserMenu> userMenus = cache.get(UserContext.getCurrentUser().getUserName());
			request.setAttribute(UserConstants.USER_MENU_LIST, userMenus);
			request.setAttribute("menus", JsonUtil.toJson(userMenus));
		}
		IUser user = UserContext.getCurrentUser();
		request.setAttribute(UserConstants.USER_CONTEXT, user);
		request.setAttribute(UserConstants.USER_CONTEXT_JSON, JsonUtil.toJsonInclusion(user));
		//设置静态资源版本
		String version = SystemParameterUtil.getValue(SystemParameterConstants.version);
		if(StringUtils.isEmpty(version)){
			version = "1.0.0";
		}
		request.setAttribute(SystemParameterConstants.version, version);
		request.setAttribute("env", AppContext.getAppContext().getEnv());
		request.setAttribute("app_title", SystemParameterUtil.getValue("app.title"));
	}
	
	boolean isAjax(HttpServletRequest request){
	    return  (request.getHeader("X-Requested-With") != null  && "XMLHttpRequest".equals( request.getHeader("X-Requested-With").toString())   ) ;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	

	public List<String> getNotUrl() {
		return notUrl;
	}

	public void setNotUrl(List<String> notUrl) {
		SecurityInterceptor.notUrl.addAll(notUrl) ;
	}

}

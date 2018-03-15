package com.feisuo.sds.base.server.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.feisuo.sds.base.server.context.AppContext;
import com.feisuo.sds.base.server.context.UserContext;
import com.feisuo.sds.base.share.constants.SystemParameterConstants;
import com.feisuo.sds.base.share.constants.UserConstants;
import com.feisuo.sds.base.share.entity.IUser;
import com.feisuo.sds.base.share.entity.IUserMenu;
import com.feisuo.sds.base.share.exception.UserNotLoginException;
import com.feisuo.sds.base.share.util.SystemParameterUtil;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/user/login";
	
	private static List<String> notUrl = new ArrayList<>();
	static{
		notUrl.add("/user/login");
		notUrl.add("/errors/404");
		notUrl.add("/errors/error");
		notUrl.add("/user/getCaptcha");
		notUrl.add("/user/retrievePassword");
		notUrl.add("/user/login");
		notUrl.add("/user/forget");
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
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + loginUrl);
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

}

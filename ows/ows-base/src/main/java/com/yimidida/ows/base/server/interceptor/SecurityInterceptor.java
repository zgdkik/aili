package com.yimidida.ows.base.server.interceptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yimidida.ows.base.server.context.AppContext;
import com.yimidida.ows.base.server.context.UserContext;
import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.constants.SystemParameterConstants;
import com.yimidida.ows.base.share.constants.UserConstants;
import com.yimidida.ows.base.share.entity.IUser;
import com.yimidida.ows.base.share.entity.IUserMenu;
import com.yimidida.ows.base.share.exception.UserNotLoginException;
import com.yimidida.ows.base.share.util.SystemParameterUtil;
import com.yimidida.ymdp.cache.server.CacheManager;
import com.yimidida.ymdp.cache.server.ICache;
import com.yimidida.ymdp.core.share.ex.BusinessException;
import com.yimidida.ymdp.core.share.util.JsonUtil;

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
			List<IUserMenu> userMenus = cache.get(UserContext.getCurrentUser().getCompCode()+","+UserContext.getCurrentUser().getUserName());
			List<IUserMenu> newUserMenus =  deepCopy(userMenus);
			newUserMenus = dealUserMenu(newUserMenus);
			request.setAttribute(UserConstants.USER_MENU_LIST, newUserMenus);
			request.setAttribute("menus", JsonUtil.toJson(newUserMenus));
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
	
	@SuppressWarnings("unchecked")
	public List<IUserMenu> deepCopy(List<IUserMenu> src) throws IOException, ClassNotFoundException{             
	        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();             
	        ObjectOutputStream out = new ObjectOutputStream(byteOut);             
	        out.writeObject(src);                    
	        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());             
	        ObjectInputStream in =new ObjectInputStream(byteIn);             
	        List<IUserMenu> dest =(List<IUserMenu>) in.readObject();             
	        return dest;         
	    } 
	
	private List<IUserMenu> dealUserMenu(List<IUserMenu> menus) {
		List<IUserMenu> menuList = new ArrayList<IUserMenu>();
		String appType = SystemParameterUtil.getValue("app.type");
	    for (IUserMenu tree : menus) {
            if(appType.equals(tree.getParentId())){
            	menuList.add(tree);
            }
            for (IUserMenu t : menus) {
                if(t.getParentId()!=null && t.getParentId().equals(tree.getAcl())){
                    if(tree.getChildList() == null){
                        List<IUserMenu> myChildrens = new ArrayList<IUserMenu>();
                        myChildrens.add(t);
                        tree.setChildList(myChildrens);
                    }else{
                        tree.getChildList().add(t);
                    }
                }
            }
        }
		return menuList;
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

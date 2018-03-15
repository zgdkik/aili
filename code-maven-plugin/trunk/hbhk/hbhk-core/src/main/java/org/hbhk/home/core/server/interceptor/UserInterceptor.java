package org.hbhk.home.core.server.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter{

	///login
	// 
	static List<String>  urls = new ArrayList<String>();
	static{
		urls.add("/");
		urls.add("/login");
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url =request.getServletPath();
		if(request.getSession().getAttribute("userName")==null && !urls.contains(url) 
				&& !((url.indexOf("scripts")>-1)
				|| (url.indexOf("images")>-1)
				|| (url.indexOf("styles")>-1))
				){
            request.getRequestDispatcher("/").forward(request, response);
            return false;  
		}
		return true;
	}
	
}

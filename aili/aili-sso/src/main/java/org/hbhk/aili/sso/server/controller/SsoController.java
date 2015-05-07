package org.hbhk.aili.sso.server.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SsoController implements InitializingBean {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request ,HttpServletResponse response) {
		Cookie[]  cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				String CASTGC= cookie.getName();
				if(CASTGC.equals("CASTGC")){
					Cookie cookie1 = new Cookie("hbhk", cookie.getValue());
					cookie.setPath("/");
					response.addCookie(cookie1);
				}
			}
		}
		
		return "index";
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("aaaaaaaaaaaa");
	}

}

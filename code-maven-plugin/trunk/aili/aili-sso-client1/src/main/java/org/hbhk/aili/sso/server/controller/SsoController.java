package org.hbhk.aili.sso.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class SsoController implements InitializingBean {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request ,HttpServletResponse response) {
//		CasAuthenticationToken principal=  (CasAuthenticationToken) request.getUserPrincipal();
//		String ticket = principal.getCredentials().toString();
//		request.setAttribute("ticket", ticket); 
//		System.out.println(request.getHeader("name"));
//		response.addHeader("name", "hbhk");
		return "index";
	}
	
	@RequestMapping("/test/{name}")
	public String index1(HttpServletRequest request ,HttpServletResponse response) {
		return "index";
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("aaaaaaaaaaaa");
	}

}

package org.hbhk.aili.sso.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SsoController implements InitializingBean {
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request ,HttpServletResponse response) {
		CasAuthenticationToken principal=  (CasAuthenticationToken) request.getUserPrincipal();
		String ticket = principal.getCredentials().toString();
		request.setAttribute("ticket", ticket); 
		return "index";
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("aaaaaaaaaaaa");
	}

}

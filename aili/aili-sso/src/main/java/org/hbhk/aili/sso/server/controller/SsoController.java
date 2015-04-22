package org.hbhk.aili.sso.server.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SsoController implements InitializingBean {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("aaaaaaaaaaaa");
	}

}

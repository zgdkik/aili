package org.hbhk.maikkr.user.server.controller;

import org.hbhk.aili.security.server.annotation.NeedLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/main")
	@NeedLogin
	public String main() {
		return "main";
	}
	
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "loginpage";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	

}
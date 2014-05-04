package org.hbhk.aili.security.server.controller;

import javax.annotation.Resource;

import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.SecurityConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SecurityConstant.moduleName)
public class UserController {

	@Resource
	private IUserService userService;
	
	@RequestMapping("/login")
	public String login(String username , String password){
		userService.login(username, password);
		return "redirect:"+SecurityConstant.moduleName+"/main.ctrl";
	}
	
	@RequestMapping("/main")
	public String main(){
		return "main";
	}


}
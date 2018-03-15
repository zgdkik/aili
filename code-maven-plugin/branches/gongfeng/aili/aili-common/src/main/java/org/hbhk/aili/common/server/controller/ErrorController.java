package org.hbhk.aili.common.server.controller;

import org.hbhk.aili.base.server.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorController  extends AbstractController{
	
	@RequestMapping("/error")
	public String error(){
		return "/errors/error";
		
	}
	@RequestMapping("/404")
	public String error404(){
		return "/errors/404";
	}

}

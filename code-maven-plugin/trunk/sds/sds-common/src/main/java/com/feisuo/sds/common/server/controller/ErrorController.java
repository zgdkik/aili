package com.feisuo.sds.common.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feisuo.sds.base.server.controller.AbstractController;

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

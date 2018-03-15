package org.hbhk.aili.common.server.controller;

import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.common.server.service.IClientService;
import org.hbhk.aili.rest.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
@RequestMapping("/common")
public class CommonController extends AbstractController {

	@Autowired
	private IClientService clientService;
	
	@Reference
	private IUserService userService;
	
	@RequestMapping("/test")
	@ResponseBody
	public  String test(){
		return clientService.test();
		
	}
	
	
	@RequestMapping("/test1")
	@ResponseBody
	public  String test1(){
		return userService.registerUser();
		
	}
	
}

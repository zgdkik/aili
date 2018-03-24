package com.yimidida.ows.user.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimidida.ows.base.server.controller.AbstractController;


@Controller
public class TestController extends AbstractController {
	
	
	
	@RequestMapping("/test/{name}")
	public String test(@PathVariable String name){
		return name;
	}
}

package org.hbhk.aili.user.server.controller;

import org.hbhk.aili.base.server.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController extends AbstractController {
	
	
	@RequestMapping("/test/{name}")
	public String test(@PathVariable String name){
		return name;
	}
}

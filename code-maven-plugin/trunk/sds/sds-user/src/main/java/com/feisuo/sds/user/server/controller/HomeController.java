package com.feisuo.sds.user.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feisuo.sds.base.server.controller.AbstractController;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}

}

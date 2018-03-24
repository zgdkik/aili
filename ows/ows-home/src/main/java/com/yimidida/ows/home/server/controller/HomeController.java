package com.yimidida.ows.home.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimidida.ows.base.server.controller.AbstractController;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping("/")
	public String home(Model model) {
		return "home";
	}
	@RequestMapping("/login")
	public String admin(Model model) {
		return "login";
	}

}

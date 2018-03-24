package com.yimidida.ows.user.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yimidida.ows.base.server.controller.AbstractController;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	@RequestMapping("/home")
	public String home(Model model) {
		return "admin/home";
	}

}

package org.hbhk.zlj.frontend.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutUsController {

	@RequestMapping("/about.html")
	public String home() {
		return "about";
	}
}

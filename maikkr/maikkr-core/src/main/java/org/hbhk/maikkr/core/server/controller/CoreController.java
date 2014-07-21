package org.hbhk.maikkr.core.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/core")
public class CoreController {

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

}
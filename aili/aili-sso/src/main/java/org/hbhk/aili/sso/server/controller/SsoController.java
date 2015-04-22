package org.hbhk.aili.sso.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SsoController {
	
	@RequestMapping(value={"/","/index"})
	public String index() {
		return "index";

	}

}

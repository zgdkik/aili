package org.hbhk.maikkr.backend.server.controller;

import org.hbhk.aili.core.server.annotation.NeedLogin;
import org.hbhk.aili.core.server.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@NeedLogin
@RequestMapping("/backend")
public class BackendController extends BaseController {

	@RequestMapping("loginpage")
	public String loginpage() {
		return "login";

	}
}
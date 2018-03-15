package org.hbhk.hls.user.server.controller;

import org.hbhk.aili.base.server.controller.AbstractController;
import org.hbhk.aili.base.share.util.SystemParameterUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends AbstractController {

	@RequestMapping("/")
	public String home(Model model) {
		String appType = SystemParameterUtil.getValue("app.theme");
		if("easyui".equals(appType)){
			return "home-ui";
		}
		return "home";
	}

}

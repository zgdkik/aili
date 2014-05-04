package org.hbhk.aili.core.server.controller;

import org.hbhk.aili.core.share.util.ModuleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ModuleConstants.MODULE_FRAMEWORK)
public class CommonController {

	
	 @RequestMapping("/timeout")
	 public  String sessionTimeOut(){
		 
		 return "/framework/error/timeout";
	 }
}

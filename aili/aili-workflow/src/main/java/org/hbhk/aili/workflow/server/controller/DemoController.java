package org.hbhk.aili.workflow.server.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController implements InitializingBean {


	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}
}

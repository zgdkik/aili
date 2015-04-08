package org.hbhk.aili.webflow.server.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController implements InitializingBean {

	private Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = {"/viewCart" })
	public String viewCart() {
		return "viewCart";
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("asdasd");
	}
}

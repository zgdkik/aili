package org.hbhk.aili.job.server.quartz.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.impl.StdScheduler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class QuartzContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sc) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(sc.getServletContext());
		StdScheduler startQuertz = webApplicationContext
				.getBean(StdScheduler.class);
		if (startQuertz != null) {
			startQuertz.shutdown();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}

}

package org.hbhk.module.framework.server.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AppContextListener implements ServletContextListener {

	private  final Log log = LogFactory.getLog(getClass());

	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().setAttribute("appcontext",
				sce.getServletContext().getContextPath());
		log.info("appcontext:" + sce.getServletContext().getContextPath());
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}

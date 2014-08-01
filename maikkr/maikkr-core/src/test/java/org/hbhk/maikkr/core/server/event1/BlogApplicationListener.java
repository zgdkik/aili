package org.hbhk.maikkr.core.server.event1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class BlogApplicationListener implements
		ApplicationListener<ApplicationEvent> {

	private Log log = LogFactory.getLog(getClass());

	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof BlogHitsEvent) {
			BlogHitsEvent blogHitsEvent = (BlogHitsEvent) event;
			blogHitsEvent.updateBlogHit();
			log.info("主题热度更新:");
			
		}
	}

}

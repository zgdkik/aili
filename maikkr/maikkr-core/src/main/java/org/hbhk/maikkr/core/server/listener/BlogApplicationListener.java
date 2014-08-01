package org.hbhk.maikkr.core.server.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.maikkr.core.server.event.UpdateBlogHitsEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BlogApplicationListener implements
		ApplicationListener<ApplicationEvent> {

	private Log log = LogFactory.getLog(getClass());

	public void onApplicationEvent(ApplicationEvent event) {

		if (event instanceof UpdateBlogHitsEvent) {
			UpdateBlogHitsEvent blogHitsEvent = (UpdateBlogHitsEvent) event;
			log.info("主题热度更新:" + blogHitsEvent.getBlogId());
		}
	}

}

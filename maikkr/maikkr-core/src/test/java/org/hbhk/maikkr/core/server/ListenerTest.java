package org.hbhk.maikkr.core.server;

import org.hbhk.maikkr.core.server.event1.BlogHitsEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListenerTest {

	public static void main(String[] args) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
		BlogHitsEvent blogHitsEvent = new BlogHitsEvent(context);
		context.publishEvent(blogHitsEvent);
	}
}

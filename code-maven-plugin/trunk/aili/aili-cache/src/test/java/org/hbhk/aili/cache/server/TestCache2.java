package org.hbhk.aili.cache.server;

import org.hbhk.aili.cache.server.templet.impl.RedisCacheTemplet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCache2 {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:cache/spring-redis.xml");
		System.out.println(context.getBean(RedisCacheTemplet.class).set("name1", "hbhk1222"));
	}

}

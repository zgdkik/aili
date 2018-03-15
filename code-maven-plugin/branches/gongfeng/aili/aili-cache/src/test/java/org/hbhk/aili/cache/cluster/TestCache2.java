package org.hbhk.aili.cache.cluster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisSentinelPool;

public class TestCache2 {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-redis.xml");

		JedisSentinelPool cacheSupportTest = (JedisSentinelPool) context
				.getBean("jedisSentinelPool");
		cacheSupportTest.getResource().set("name".getBytes(), "hbhk".getBytes());
	}

}

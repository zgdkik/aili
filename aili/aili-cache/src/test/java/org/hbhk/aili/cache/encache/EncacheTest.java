package org.hbhk.aili.cache.encache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EncacheTest {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-encache.xml");
		UserService userService = (UserService) context.getBean("userService");
	}
}

package org.hbhk.test.ds;

import org.hbhk.aili.core.server.context.AiliPropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DynDsTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring.xml");
		Object object =  AiliPropertyPlaceholderConfigurer.properties.get("name");
		System.out.println("value:"+object);
		IUserService us = (IUserService) context.getBean("userService");
		us.getUser();
		//us.getUser1();
	}
}

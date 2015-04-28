package org.hbhk.aili.cache.encache;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EncacheTest {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-encache.xml");
		IUserService userService = (IUserService) context.getBean("userService");
		for (int i = 0; i < 1000; i++) {
			System.out.println("获取最新数据");
			System.out.println(userService.findById(1).getName());
			TimeUnit.SECONDS.sleep(3);
			
		}
	
	}
}

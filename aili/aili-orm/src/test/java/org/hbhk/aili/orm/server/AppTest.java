package org.hbhk.aili.orm.server;

import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.server.test.TestAiliDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest{
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		TestAiliDao t = (TestAiliDao) context.getBean("testAiliDao");
		Page p = new Page();
		p.setSize(5);
		p.setStart(0);
		t.create("hbhk",p);
	}
	
}
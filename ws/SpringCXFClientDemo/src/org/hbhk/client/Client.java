package org.hbhk.client;

import org.hbhk.server.HelloWorld;
import org.hbhk.service.Person;
import org.hbhk.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Client {

	private Client() {

	}

	public static void main(String args[]) throws Exception {
		// START SNIPPET: client
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");

		HelloWorld client = (HelloWorld) context.getBean("helloClient");

		// String response = client.sayHi("hbhk");
		// System.out.println("Response: " + response);

		// TestCXF1 cxf1 = (TestCXF1) context.getBean("testcxf");
		// cxf1.test();

		UserService userService = (UserService) context.getBean("userService");
		Person p = new Person();
		p.setName("hbhk");
		userService.saveUser(p);
		// User user = new User();
		// user.setId(2228);
		// user.setEmail("hbhk2");bosTest

		// System.out.println(userService.saveUser("sdfsdf"));
		// ITestBOS testBOS = (ITestBOS) context.getBean("bosTest");
		// System.out.println(testBOS.getUserName("hbhk"));
		// TestBosWS testBosWS = (TestBosWS) context.getBean("testbosws");
		// testBosWS.returnBankPayRefund("sdf", "1", new Date(), 111, 111,
		// new String[] { "df", "dsfs" });

	}
}

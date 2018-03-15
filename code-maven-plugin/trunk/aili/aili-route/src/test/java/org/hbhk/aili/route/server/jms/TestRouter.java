package org.hbhk.aili.route.server.jms;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.RecipientList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRouter {

	@RecipientList
	public String[] route() {
		//@XPath("/order/@customer") String customer
		return new String[] { "jms:queue:hbhk1", "jms:queue:hbhk2" };
//		if (isGoldCustomer(customer)) {
//			return new String[] { "jms:queue:hbhk1", "jms:queue:hbhk2" };
//		} else {
//			return new String[] { "jms:queue:hbhk3" };
//		}
	}
	
	public String route1() {
		return "jms:queue:hbhk1";
	}
	
	public String route2(@Headers Map<String, Object> header,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		return "direct:normal";
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"jms/camel-jms-route.xml");
		System.in.read();
	}
}

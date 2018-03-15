package org.hbhk.aili.route.server.jms;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelTest {

	private static String user = ActiveMQConnection.DEFAULT_USER;
	private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		context.addComponent("jms",
				JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		System.out.println(url + " " + user + password);
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("jms:queue:hbhk").to("jms:queue:TOOL.DEFAULT1");
			}
		});
		context.start();
		boolean loop = true;
		while (loop) {
			Thread.sleep(25000);
		}

		context.stop();
	}
}

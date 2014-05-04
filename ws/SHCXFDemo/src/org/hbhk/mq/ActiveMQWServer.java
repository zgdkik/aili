package org.hbhk.mq;

import javax.xml.ws.Endpoint;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.hbhk.service.impl.UserServiceImpl;

public class ActiveMQWServer {

	// 设置JMS的URI的格式：jms:queue or topic:destination
	private static final String JMS_ENDPOINT_URI = "jms:queue:jaxws.jmstransport.queue?timeToLive=1000"
			+ "&jndiConnectionFactoryName=ConnectionFactory"
			+ "&jndiInitialContextFactory"
			+ "=org.apache.activemq.jndi.ActiveMQInitialContextFactory";

	private ActiveMQWServer() {
	}

	public static void main(String args[]) throws Exception {
		ActiveMQWServer server = new ActiveMQWServer();
		// 初始化Broker的方法
		server.initBrokerService();
		// 启动JAXWS相关的 服务
		server.launchJaxwsApi();

		System.out.println("Server ready... Press any key to exit");
		System.in.read();
		System.out.println("Server exiting");
		System.exit(0);
	}

	/**
	 * 启动ActiveMQ的Broker
	 * 
	 * @throws Exception
	 */
	public void initBrokerService() throws Exception {
		BrokerService brokerService = new BrokerService();
		brokerService
				.addConnector(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
		brokerService.setDataDirectory("target/activemq-data");
		brokerService.start();
	}

	/**
	 * 发布WebService服务
	 * 
	 */
	private void launchJaxwsApi() {
		Endpoint.publish(JMS_ENDPOINT_URI, new UserServiceImpl());
	}
}

package org.hbhk.aili.jms.server.factory;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.ibm.mq.jms.MQQueueConnectionFactory;

public class MQConnectionFactory {

	public static ConnectionFactory getIbmMQConnectionFactory(String qmn,
			String hostName, int port, int ccsid, String channelName) {
		MQQueueConnectionFactory cf = null;
		try {
			cf = new MQQueueConnectionFactory();
			cf.setTransportType(1);
			cf.setQueueManager(qmn);
			cf.setHostName(hostName);
			cf.setChannel(channelName);
			cf.setPort(port);
			cf.setCCSID(ccsid);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cf;
	}

	public static ConnectionFactory getActiveMQConnectionFactory(String brokerUrl) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				brokerUrl);
		return connectionFactory;
	}

}

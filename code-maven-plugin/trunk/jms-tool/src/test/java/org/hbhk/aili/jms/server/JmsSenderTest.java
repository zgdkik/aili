package org.hbhk.aili.jms.server;

import javax.jms.ConnectionFactory;
import javax.jms.TextMessage;

import org.hbhk.aili.jms.server.factory.MQConnectionFactory;
import org.hbhk.aili.jms.server.sender.JmsSender;

/**
 * 发送JMS异步消息基类
 */
public class JmsSenderTest  {

	
	public static void main(String[] args) throws Exception {
		ConnectionFactory cf = MQConnectionFactory.getActiveMQConnectionFactory("tcp://127.0.0.1:61616");
		JmsSender sender = new JmsSender(cf);
		TextMessage  msg = cf.createConnection().createSession(true, 1).createTextMessage();
		msg.setStringProperty("name", "hbhk");
		msg.setText("11111111111111");
		for (int i = 0; i < 10000; i++) {
			sender.sendJms("hbhk", msg);
		}
		
		System.exit(0);
	}

}

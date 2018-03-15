package org.hbhk.aili.jms.server;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class TestVirtualTopic {

	public static void main(String[] args) {
		try {

			ActiveMQConnectionFactory factoryA = new ActiveMQConnectionFactory(
					"tcp://127.0.0.1:61616");

			Queue queue = new ActiveMQQueue(getVirtualTopicConsumerNameA());
			ActiveMQConnection conn = (ActiveMQConnection) factoryA
					.createConnection();
			conn.start();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer1 = session.createConsumer( queue );
			//final AtomicInteger aint1 = new AtomicInteger(0);
			MessageListener listenerA = new MessageListener() {
				public void onMessage(Message message) {
					try {
						System.out.println(//aint1.incrementAndGet()
								""+ " => receive from `"+ getVirtualTopicConsumerNameA() +": " + message);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			consumer1.setMessageListener(listenerA);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  protected static String getVirtualTopicName() {
	        return "VirtualTopic.TEST";
	    }

	    protected static String getVirtualTopicConsumerNameA() {
	        return "Consumer.A.VirtualTopic.TEST";
	    }
	    
	    protected static String getVirtualTopicConsumerNameB() {
	        return "Consumer.B.VirtualTopic.TEST";
	    }

}


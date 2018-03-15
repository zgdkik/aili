package org.hbhk.aili.route.server.jms;

import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.share.RouteConstant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestJms {
 
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"jms/spring-jms-send.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
		for (int i = 0; i < 1; i++) {
			 
			TimeUnit.SECONDS.sleep(1); 
			final StringBuffer sb = new StringBuffer(i);
			sb.append(i+1);
			jmsTemplate.send("hbhk1", new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage();
					
					message.setStringProperty(RouteConstant.interfaceCode, "hbhk");
					if(Integer.parseInt(sb.toString())%2 == 0){
						message.setStringProperty("dpesb_reqOrCalbak", ESBServiceConstant.RT_REQUEST);
					}else{
						message.setStringProperty("dpesb_reqOrCalbak", ESBServiceConstant.RT_CALLBACK);
					}
					//message.setStringProperty("CamelSlipEndpoint", "CamelSlipEndpoint");
					message.setText("22222222222222222");
					System.out.println(message);
					return message;
				}
			});
		}
		System.exit(0);
	
	}

	

}

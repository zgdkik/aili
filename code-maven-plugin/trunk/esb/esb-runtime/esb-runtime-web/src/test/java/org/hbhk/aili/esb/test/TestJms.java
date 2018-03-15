package org.hbhk.aili.esb.test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TestJms {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jms.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
		jmsTemplate.send("QU_HBHK_REQUEST_COM_OUT", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText("22222222222222222");
				header2JMSProperties(message);  
				System.out.println(message);    
				return message; 
			}   
		});

	} 

	public static void header2JMSProperties(TextMessage message)
			throws JMSException { 
		// 头转换
		message.setStringProperty(ESBServiceConstant.ESB_SERVICE_CODE,
				"shbhk1");
		String uuid = System.currentTimeMillis()+"";
		message.setStringProperty(ESBServiceConstant.VERSION,uuid);
		message.setStringProperty(ESBServiceConstant.BUSINESSID,uuid);
		message.setStringProperty(ESBServiceConstant.REQUEST_ID,uuid);
		message.setStringProperty(ESBServiceConstant.SOURCE_SYSTEM,"hbhk");
		message.setIntProperty(ESBServiceConstant.MESSAGE_FORMATE,1);
		message.setIntProperty(ESBServiceConstant.EXCHANGE_PATTERN,1);
	}

}

package com.deppon.esb.server.common.sender;

import java.util.Enumeration;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.w3c.dom.Element;


public class JmsSender {
	
	private static final Logger LOGGER = Logger
			.getLogger(JmsSender.class);
	
	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void send(String queueName,final String body) {
		jmsTemplate.send(queueName,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage requestMessage = session.createTextMessage();
				requestMessage.setStringProperty("SYSTEMCODE", body);
				requestMessage.setText("ESB request user configuration!");
				return requestMessage;
			}
		});
	}
	
}

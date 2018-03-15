package com.deppon.esb.server.common.receive;


import javax.jms.Message;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

public class JmsReceiver {
	private JmsTemplate jmsTemplate;
	
	private Logger LOG = Logger.getLogger(JmsReceiver.class);

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Message getMessage(String destination) throws Exception {
		Message message = jmsTemplate.receive(destination);
		return message;
	}

}
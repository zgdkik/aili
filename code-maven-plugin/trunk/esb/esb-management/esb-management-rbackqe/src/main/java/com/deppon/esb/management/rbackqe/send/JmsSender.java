package com.deppon.esb.management.rbackqe.send;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.deppon.esb.management.rbackqe.intface.send.IJmsSenderTemplate;

public class JmsSender {

	private IJmsSenderTemplate senderTemplate;

	public boolean sendJmsMessage(Message message, Session session, String queueName) throws JMSException{
		return senderTemplate.send(message, session, queueName);
	}

	public final IJmsSenderTemplate getSenderTemplate() {
		return senderTemplate;
	}

	public final void setSenderTemplate(IJmsSenderTemplate senderTemplate) {
		this.senderTemplate = senderTemplate;
	}

}

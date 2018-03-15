package com.deppon.esb.management.rbackqe.intface.send;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public interface IJmsSenderTemplate {
	
	/**发送消息 
	 * @throws JMSException */
	boolean send(Message message,Session session,String queueName) throws JMSException;
	
}

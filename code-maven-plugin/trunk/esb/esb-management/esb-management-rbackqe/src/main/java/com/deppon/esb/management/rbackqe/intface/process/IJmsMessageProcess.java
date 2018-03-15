package com.deppon.esb.management.rbackqe.intface.process;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public interface IJmsMessageProcess {

	boolean process(Session session, Message message) throws JMSException;
		
}

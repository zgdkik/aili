package com.deppon.esb.management.rbackqe.intface.process;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.deppon.esb.management.rbackqe.intface.service.IMessageProcessJudge;
import com.deppon.esb.management.rbackqe.send.JmsSender;

public class JmsMessageProcess extends JmsSender implements IJmsMessageProcess {

	private IMessageProcessJudge messageProcessJudge;

	public IMessageProcessJudge getMessageProcessJudge() {
		return messageProcessJudge;
	}

	public void setMessageProcessJudge(IMessageProcessJudge messageProcessJudge) {
		this.messageProcessJudge = messageProcessJudge;
	}

	@Override
	public boolean process(Session session, Message message)
			throws JMSException {
		if (message == null) {
			return true;
		}
		String queueName = messageProcess(message);
		if (queueName != null) {
			return sendMessage(message, session, queueName);
		}
		return false;
	}

	protected String messageProcess(Message message) throws JMSException {
		return messageProcessJudge.judeMessage(message);
	}

	protected boolean sendMessage(Message message, Session session,
			String queueName) throws JMSException {
		return sendJmsMessage(message, session, queueName);
	}

}

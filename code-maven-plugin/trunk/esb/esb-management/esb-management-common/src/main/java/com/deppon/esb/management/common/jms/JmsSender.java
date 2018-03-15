package com.deppon.esb.management.common.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.deppon.esb.management.common.constant.ESBConstants;
import com.ibm.mq.jms.JMSC;
import com.ibm.mq.jms.MQQueue;

/**
 * 一期移植，JMS发送器
 * 
 * @author HuangHua
 * @date 2012-12-27 上午9:54:56
 */
public class JmsSender {
	/**
	 * 日志
	 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(JmsSender.class);
	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * 重发JMS消息 传入的参是个Message
	 * 
	 */
	public void sendJmsMessage(final String queueName, final Message message) {
		if (message instanceof Message) {
			getJmsTemplate().send(queueName, new MessageCreator() {

				@Override
				public Message createMessage(Session session) throws JMSException {
					return message;
				}
			});
		}
	}

	/**
	 * 供重发jms消息使用； 传入的object对象必须实现Serializable接口；
	 * 
	 * @param queueName
	 * @param object
	 * @throws JmsException
	 */
	@Deprecated
	public void sendJmsMessage(final String queueName, final Serializable object) throws JmsException {
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(object);
			}
		});
	}

	/**
	 * 
	 * 
	 * @param queueName
	 * @param object
	 * @throws Exception
	 */
	public void sendJmsMessageWithProperty(final String queueName, final Serializable object) throws Exception {
		QueueConnectionFactory connectionFactory = (QueueConnectionFactory) jmsTemplate.getConnectionFactory();
		QueueConnection connection = null;
		QueueSession session = null;
		try {
			connection = connectionFactory.createQueueConnection();
			connection.start();
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueName);
			if (queue instanceof MQQueue) {
				((MQQueue) queue).setTargetClient(JMSC.MQJMS_CLIENT_JMS_COMPLIANT);
			}

			QueueSender sender = session.createSender(queue);
			ObjectMessage message = session.createObjectMessage(object);
			message.setStringProperty(ESBConstants.REDO_SYSTEMSEND, ESBConstants.REDO_SYSTEMSEND);

			sender.send(message);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (session != null)
				session.close();
			if (connection != null)
				connection.close();
		}
	}
}

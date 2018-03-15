package org.hbhk.aili.jms.server.sender;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.hbhk.aili.jms.server.factory.MQConnectionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 发送JMS异步消息基类
 */
@Scope()
public class JmsSender implements IJmsSender {

	// jms模板
	private JmsTemplate jmsTemplate;

	public JmsSender(ConnectionFactory cf) {
		jmsTemplate = new JmsTemplate(cf);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * 发送JMS异步消息
	 */
	public void sendJms(String queueName, final TextMessage msg) {
		// 队列名称为空，抛异常
		if (StringUtils.isEmpty(queueName)) {
			throw new NullPointerException("队列名称不能为空");
		}
		// 发送消息
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message = msg;
				return message;
			}
		});
	}

}

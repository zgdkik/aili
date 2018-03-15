package com.deppon.esb.management.svccfg.message;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * The Class ConfigurationResponseSender.
 * 
 * @author HuangHua
 * @date 2013-1-8 下午5:26:36
 */
public class ConfigurationResponseSender {

	/** The config jms template. */
	private JmsTemplate configJmsTemplate;

	/**
	 * Send.
	 * 
	 * @param queueName
	 *            the queue name
	 * @param header
	 *            the header
	 * @param content
	 *            the content
	 */
	public void send(String queueName, final Map<String, String> header,
			final String content) {
		configJmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage(content);
				Iterator<Entry<String, String>> iter = header.entrySet()
						.iterator();
				while (iter.hasNext()) {
					Entry<String, String> entry = (Entry<String, String>) iter
							.next();
					String key = entry.getKey();
					String val = entry.getValue();
					message.setStringProperty(key, val);
				}
				return message;
			}
		});
	}

	/**
	 * 获取 config jms template.
	 * 
	 * @return the config jms template
	 */
	public JmsTemplate getConfigJmsTemplate() {
		return configJmsTemplate;
	}

	/**
	 * 设置 config jms template.
	 * 
	 * @param configJmsTemplate
	 *            the new config jms template
	 */
	public void setConfigJmsTemplate(JmsTemplate configJmsTemplate) {
		this.configJmsTemplate = configJmsTemplate;
	}

}

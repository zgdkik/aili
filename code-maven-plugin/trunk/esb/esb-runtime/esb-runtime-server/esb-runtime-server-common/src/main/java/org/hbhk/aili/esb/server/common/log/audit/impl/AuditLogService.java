package org.hbhk.aili.esb.server.common.log.audit.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.camel.Exchange;
import org.apache.camel.component.jms.JmsMessage;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;
import org.hbhk.aili.esb.server.common.log.audit.IAuditLogService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 审计日志实现类.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:47:44
 */
public class AuditLogService implements IAuditLogService {
	// private static Log log = LogFactory.getLog(AuditLogService.class);

	/** The audit queue name. */
	private String auditQueueName;

	/** The jms template. */
	private JmsTemplate jmsTemplate;

	/**
	 * 保存审计日志.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws ESBRunTimeException
	 *             the eSB run time exception
	 * @author HuangHua
	 * 
	 * @date 2012-12-21 上午10:47:59
	 * @see org.hbhk.aili.esb.server.common.log.audit.IAuditLogService#saveAudit(org.apache.camel.Exchange)
	 */
	@Override
	public void saveAudit(Exchange exchange) throws ESBRunTimeException, JMSException {
		org.apache.camel.Message message = exchange.getIn();
		if (!(message instanceof JmsMessage)) {
			throw new ESBRunTimeException("消息不是JMS消息，请检查消息来源！");
		}
		JmsMessage jmsMessage = (JmsMessage) message;

		final Message sendMessage = jmsMessage.getJmsMessage();
		
		jmsTemplate.send(auditQueueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				
				return sendMessage;
			}
		});
	}

	/**
	 * Gets the audit queue name.
	 * 
	 * @return the audit queue name
	 */
	public String getAuditQueueName() {
		return auditQueueName;
	}

	/**
	 * Sets the audit queue name.
	 * 
	 * @param auditQueueName
	 *            the new audit queue name
	 */
	public void setAuditQueueName(String auditQueueName) {
		this.auditQueueName = auditQueueName;
	}

	/**
	 * Gets the jms template.
	 * 
	 * @return the jms template
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * Sets the jms template.
	 * 
	 * @param jmsTemplate
	 *            the new jms template
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}

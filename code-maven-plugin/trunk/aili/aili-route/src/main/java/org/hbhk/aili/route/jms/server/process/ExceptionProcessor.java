package org.hbhk.aili.route.jms.server.process;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jms.JmsMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.common.exception.ESBRunTimeException;
import org.hbhk.aili.route.jms.server.common.CommonExceptionInfo;
import org.hbhk.aili.route.jms.server.common.exception.ESBConvertException;
import org.hbhk.aili.route.jms.server.common.transformer.CommonExceptionInfoTrans;
import org.hbhk.aili.route.jms.server.common.utils.IPUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


/**
 * 消息校验失败后的，记录失败队列和异常队列处理.
 * 
 */
public class ExceptionProcessor implements Processor {
	
	/** The Constant EXCEPTION_CODE. */
	private static final String EXCEPTION_CODE = "ESB_SYS_NOT_FOUND_SERVICE";

	/** The log. */
	private static final Log LOGGER = LogFactory.getLog(ExceptionProcessor.class);

	/** 失败消息队列名. */
	private String failQueueName;
	
	/** 异常消息队列名. */
	private String exceptionQueueName;
	
	/** The jms template. */
	private JmsTemplate jmsTemplate;

	/**
	 * 处理方法.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:46:32
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		org.apache.camel.Message message = exchange.getIn();
		if (!(message instanceof JmsMessage)) {
			throw new ESBRunTimeException("消息不是JMS消息，请检查消息来源！");
		}
		JmsMessage jmsMessage = (JmsMessage) message;
		final Message sendMessage = jmsMessage.getJmsMessage();
		// 发送失败队列
		jmsTemplate.send(failQueueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return sendMessage;
			}
		});
		// 发送异常队列
		jmsTemplate.send(exceptionQueueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return generateMessage(session, sendMessage);
			}
		});
	}

	/**
	 * 消息处理与生成.
	 * 
	 * @param session
	 *            the session
	 * @param sendMessage
	 *            the send message
	 * @return the text message
	 * @throws JMSException
	 *             the jMS exception
	 */
	public TextMessage generateMessage(Session session,
			Message sendMessage) throws JMSException {
		String esbServiceCode = sendMessage
				.getStringProperty(ESBServiceConstant.ESB_SERVICE_CODE);
		String backServiceCode = sendMessage
				.getStringProperty(ESBServiceConstant.BACK_SERVICE_CODE);
		String businessId = sendMessage
				.getStringProperty(ESBServiceConstant.BUSINESSID);
		String businessDesc1 = sendMessage
				.getStringProperty(ESBServiceConstant.BUSINESSDESC1);
		String businessDesc2 = sendMessage
				.getStringProperty(ESBServiceConstant.BUSINESSDESC2);
		String businessDesc3 = sendMessage
				.getStringProperty(ESBServiceConstant.BUSINESSDESC3);
		String requestId = sendMessage
				.getStringProperty(ESBServiceConstant.REQUEST_ID);
		String responseId = sendMessage
				.getStringProperty(ESBServiceConstant.RESPONSE_ID);
		String ip = IPUtils.getSystemIP();
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setCreatedTime(new Date());
		commonExceptionInfo.setDetailedInfo("消息不符合规范，请检查根据ESB消息规范修改！");
		commonExceptionInfo.setExceptioncode(EXCEPTION_CODE);
		commonExceptionInfo
				.setExceptiontype(ESBServiceConstant.SYSTEM_EXCEPTION);
		commonExceptionInfo.setMessage("不符合规范！");

		CommonExceptionInfoTrans exceptionInfoTrans = new CommonExceptionInfoTrans();
		String body = null;
		try {
			body = exceptionInfoTrans.fromMessage(commonExceptionInfo);
		} catch (ESBConvertException e) {
			LOGGER.error(e.getMessage(), e);
		}
		TextMessage message = session.createTextMessage(body);
		message.setStringProperty(ESBServiceConstant.ESB_SERVICE_CODE,
				esbServiceCode);
		message.setStringProperty(ESBServiceConstant.BACK_SERVICE_CODE,
				backServiceCode);
		message.setStringProperty(ESBServiceConstant.BUSINESSID, businessId);
		message.setStringProperty(ESBServiceConstant.BUSINESSDESC1,
				businessDesc1);
		message.setStringProperty(ESBServiceConstant.BUSINESSDESC2,
				businessDesc2);
		message.setStringProperty(ESBServiceConstant.BUSINESSDESC3,
				businessDesc3);
		message.setStringProperty(ESBServiceConstant.REQUEST_ID, requestId);
		message.setStringProperty(ESBServiceConstant.RESPONSE_ID, responseId);
		message.setStringProperty(ESBServiceConstant.HOST_IP, ip);

		return message;
	}

	/**
	 * Gets the fail queue name.
	 * 
	 * @return the fail queue name
	 */
	public String getFailQueueName() {
		return failQueueName;
	}

	/**
	 * Sets the fail queue name.
	 * 
	 * @param failQueueName
	 *            the new fail queue name
	 */
	public void setFailQueueName(String failQueueName) {
		this.failQueueName = failQueueName;
	}

	/**
	 * Gets the exception queue name.
	 * 
	 * @return the exception queue name
	 */
	public String getExceptionQueueName() {
		return exceptionQueueName;
	}

	/**
	 * Sets the exception queue name.
	 * 
	 * @param exceptionQueueName
	 *            the new exception queue name
	 */
	public void setExceptionQueueName(String exceptionQueueName) {
		this.exceptionQueueName = exceptionQueueName;
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

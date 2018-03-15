package org.hbhk.aili.esb.server.common.log.exception.impl;

import java.util.Date;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.exception.ESBConvertException;
import org.hbhk.aili.esb.server.common.log.exception.IExceptionService;
import org.hbhk.aili.esb.server.common.transformer.CommonExceptionInfoTrans;
import org.hbhk.aili.esb.server.common.utils.IPUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 异常处理实现.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:48:55
 */
public class ExceptionService implements IExceptionService {

	/** The Constant EXCEPTION_CODE. */
	private static final String EXCEPTION_CODE = "ESB_SYS_OTHER";
	
	/** The log. */
	private static Log log = LogFactory.getLog(ExceptionService.class);

	/** The jms template. */
	private JmsTemplate jmsTemplate;
	
	/** The exception queue name. */
	private String exceptionQueueName;//异常消息队列名

	/**
	 * 保存异常.
	 * 
	 * @param header
	 *            the header
	 * @param commonException
	 *            the common exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:49:17
	 * @see org.hbhk.aili.esb.server.common.log.exception.IExceptionService#saveException(java.util.Map,
	 *      org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo)
	 */
	@Override
	public void saveException(Map<String, Object> header, CommonExceptionInfo commonException) {
		final Map<String, Object> headers= header;
		jmsTemplate.send(exceptionQueueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return generateMessage(session, headers);
			}
		});
	}
	
	/**
	 * 生成消息.
	 * 
	 * @param session
	 *            the session
	 * @param header
	 *            the header
	 * @return the text message
	 * @throws JMSException
	 *             the jMS exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:49:24
	 */
	public TextMessage generateMessage(Session session, Map<String, Object> header) throws JMSException{
		final String esbServiceCode = (String) header.get(ESBServiceConstant.ESB_SERVICE_CODE);
		final String backServiceCode = (String) header.get(ESBServiceConstant.BACK_SERVICE_CODE);
		final String businessId = (String) header.get(ESBServiceConstant.BUSINESSID);
		final String businessDesc1 = (String) header.get(ESBServiceConstant.BUSINESSDESC1);
		final String businessDesc2 = (String) header.get(ESBServiceConstant.BUSINESSDESC2);
		final String businessDesc3 = (String) header.get(ESBServiceConstant.BUSINESSDESC3);
		final 	String requestId= (String) header.get(ESBServiceConstant.REQUEST_ID);
		final String responseId = (String) header.get(ESBServiceConstant.RESPONSE_ID);
		final 	String ip = IPUtils.getSystemIP();
		
		CommonExceptionInfoTrans exceptionInfoTrans = new CommonExceptionInfoTrans();
		CommonExceptionInfo commonExceptionInfo = new CommonExceptionInfo();
		commonExceptionInfo.setCreatedTime(new Date());
		commonExceptionInfo.setDetailedInfo("没有找到相应的服务。可能是没有在ESB中注册，也可能是传入的服务编码有误！");
		commonExceptionInfo.setExceptioncode(EXCEPTION_CODE);
		commonExceptionInfo.setExceptiontype(ESBServiceConstant.SYSTEM_EXCEPTION);
		commonExceptionInfo.setMessage("没找到服务！");
		TextMessage message = session.createTextMessage();
		message.setStringProperty(ESBServiceConstant.ESB_SERVICE_CODE, esbServiceCode);
		message.setStringProperty(ESBServiceConstant.BACK_SERVICE_CODE, backServiceCode);
		message.setStringProperty(ESBServiceConstant.BUSINESSID, businessId);
		message.setStringProperty(ESBServiceConstant.BUSINESSDESC1, businessDesc1);
		message.setStringProperty(ESBServiceConstant.BUSINESSDESC2, businessDesc2);
		message.setStringProperty(ESBServiceConstant.BUSINESSDESC3, businessDesc3);
		message.setStringProperty(ESBServiceConstant.REQUEST_ID, requestId);
		message.setStringProperty(ESBServiceConstant.RESPONSE_ID, responseId);
		message.setStringProperty(ESBServiceConstant.HOST_IP, ip);
		String body = null;
		try {
			body = exceptionInfoTrans.fromMessage(commonExceptionInfo);
			message.setText(body);
		} catch (ESBConvertException e) {
			log.error(e.getMessage(), e);
		}
		return message;
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

}

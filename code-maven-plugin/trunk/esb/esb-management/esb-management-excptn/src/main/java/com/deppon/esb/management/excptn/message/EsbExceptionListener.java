/*
 * PROJECT NAME: esb-management-excptn
 * PACKAGE NAME: com.deppon.esb.management.excptn
 * FILE    NAME: EsbExceptionListener.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.excptn.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.EsbLogMessage;
import com.deppon.esb.management.common.entity.jms.header.AuthInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.common.transformer.IMessageTransform;
import com.deppon.esb.management.common.util.EsbHeaderUtil;
import com.deppon.esb.management.excptn.domain.EsbExceptionInfo;
import com.deppon.esb.management.excptn.service.IEsbExceptionService;
import com.deppon.esb.management.exptn.generate.CommonExceptionInfo;

/**
 * The listener interface for receiving esbException events. The class that is
 * interested in processing a esbException event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addEsbExceptionListener<code> method. When
 * the esbException event occurs, that object's appropriate
 * method is invoked.
 * 
 * @author HuangHua
 * @date 2013-1-11 上午11:55:48
 */
public class EsbExceptionListener implements MessageListener {
	
	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EsbExceptionListener.class);

	/** The esb exception service. */
	private IEsbExceptionService esbExceptionService;
	
	/** The message transform. */
	private IMessageTransform messageTransform;
	private ObjectMapper mapper = new ObjectMapper();
	/**
	 * 获取 esb exception service.
	 * 
	 * @return the esb exception service
	 */
	public IEsbExceptionService getEsbExceptionService() {
		return esbExceptionService;
	}

	/**
	 * 设置 esb exception service.
	 * 
	 * @param esbExceptionService
	 *            the new esb exception service
	 */
	public void setEsbExceptionService(IEsbExceptionService esbExceptionService) {
		this.esbExceptionService = esbExceptionService;
	}

	/**
	 * On message.
	 * 
	 * @param message
	 *            the message
	 * @author HuangHua
	 * @date 2013-1-11 下午12:30:35
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			EsbExceptionInfo esbExceptionInfo;
			try {
				if(textMessage.getText() == null ||"".equals(textMessage.getText())){
					LOGGER.warn("得到一个空消息");
					return ;
				}
				if(textMessage.propertyExists(ESBServiceConstant.CONFORMITY)){
					//TODO 
					List<EsbLogMessage> list = generateEsbLogMessage(textMessage.getText());
					for(EsbLogMessage msg:list){
						EsbHeader esbHeader = EsbHeaderUtil.map2ESBHeader(msg.getHeader());
						CommonExceptionInfo commonExceptionInfo = (CommonExceptionInfo) messageTransform.toMessage(msg.getBody());
						esbExceptionInfo = new EsbExceptionInfo();
						esbExceptionInfo.setEsbHeader(esbHeader);
						esbExceptionInfo.setCommonExceptionInfo(commonExceptionInfo);
						Long dateLongValue = (Long)msg.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME);
						esbExceptionInfo.setCreateTime(new Date(dateLongValue));
						esbExceptionService.saveEsbException(esbExceptionInfo);
					}
				}
				else{					
					esbExceptionInfo = generateEntity(textMessage);
					esbExceptionService.saveEsbException(esbExceptionInfo);
				}
				LOGGER.debug("save EsbException success.");
			} catch (Exception e) {
				try {
					LOGGER.error("报文信息 :"+textMessage.getText());
				} catch (JMSException e1) {
					//ignore
				}
				LOGGER.error(e.getMessage(), e);
			}
		} else {
			LOGGER.error("收到的异常日志竟然不是TextMessage,怎么办? 收到的消息:"
					+ message.toString());
		}
	}

	public List<EsbLogMessage> generateEsbLogMessage(String message)throws Exception{
		if(message == null){
			return new ArrayList<EsbLogMessage>();
		}
		return  mapper.readValue(message, new TypeReference<List<EsbLogMessage>>(){});
	}
	
	/**
	 * Generate entity.
	 * 
	 * @param textMessage
	 *            the text message
	 * @return the esb exception info
	 * @throws JMSException
	 *             the jMS exception
	 * @author HuangHua
	 * @date 2013-1-11 下午12:33:46
	 */
	private EsbExceptionInfo generateEntity(TextMessage textMessage) throws JMSException{
		EsbHeader esbHeader = new EsbHeader();
		AuthInfo auth = new AuthInfo();
		if(textMessage.propertyExists(ESBServiceConstant.USER_NAME)){
			auth.setUsername(textMessage.getStringProperty(ESBServiceConstant.USER_NAME));
		}
		if(textMessage.propertyExists(ESBServiceConstant.PASSWORD)){
			auth.setPassword(textMessage.getStringProperty(ESBServiceConstant.PASSWORD));
		}
		esbHeader.setAuthentication(auth);
		if(textMessage.propertyExists(ESBServiceConstant.BACK_SERVICE_CODE)){
			esbHeader.setBackServiceCode(textMessage.getStringProperty(ESBServiceConstant.BACK_SERVICE_CODE));
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC1)){
			String bizDesc1 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC1);
			bizDesc1 = StringUtils.substring(bizDesc1, 0, 300);// 字段长度截取
			esbHeader.setBusinessDesc1(bizDesc1);
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC2)){
			String bizDesc2 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC2);
			// 字段长度截取
			bizDesc2 = StringUtils.substring(bizDesc2, 0, 300);
			esbHeader.setBusinessDesc2(bizDesc2);
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC3)){
			String bizDesc3 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC3);
			// 字段长度截取
			bizDesc3 = StringUtils.substring(bizDesc3, 0, 300);
			esbHeader.setBusinessDesc3(textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC3));
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSID)){
			String businessId = textMessage.getStringProperty(ESBServiceConstant.BUSINESSID);
			// 字段长度截取
			businessId = StringUtils.substring(businessId, 0, 300);
			esbHeader.setBusinessId(businessId);
		}
		if(textMessage.propertyExists(ESBServiceConstant.ESB_SERVICE_CODE)){
			esbHeader.setEsbServiceCode(textMessage.getStringProperty(ESBServiceConstant.ESB_SERVICE_CODE));
		}
		if(textMessage.propertyExists(ESBServiceConstant.EXCHANGE_PATTERN)){
			esbHeader.setExchangePattern(textMessage.getIntProperty(ESBServiceConstant.EXCHANGE_PATTERN));
		}
		if(textMessage.propertyExists(ESBServiceConstant.MESSAGE_FORMATE)){
			esbHeader.setMessageFormat(textMessage.getStringProperty(ESBServiceConstant.MESSAGE_FORMATE));
		}
		if(textMessage.propertyExists(ESBServiceConstant.REQUEST_ID)){
			String requestId = textMessage.getStringProperty(ESBServiceConstant.REQUEST_ID);
			// 字段长度截取
			requestId = StringUtils.substring(requestId, 0, 80);
			esbHeader.setRequestId(requestId);
		}
		if(textMessage.propertyExists(ESBServiceConstant.RESPONSE_ID)){
			String responseId = textMessage.getStringProperty(ESBServiceConstant.RESPONSE_ID);
			// 字段长度截取
			responseId = StringUtils.substring(responseId, 0, 80);
			esbHeader.setResponseId(responseId);
		}
		if(textMessage.propertyExists(ESBServiceConstant.RESULT_CODE)){
			esbHeader.setResultCode(textMessage.getIntProperty(ESBServiceConstant.RESULT_CODE));
		}
		if(textMessage.propertyExists(ESBServiceConstant.SENTSEQUENCE)){
			esbHeader.setSentSequence(textMessage.getIntProperty(ESBServiceConstant.SENTSEQUENCE));
		}
		if(textMessage.propertyExists(ESBServiceConstant.SOURCE_SYSTEM)){
			esbHeader.setSourceSystem(textMessage.getStringProperty(ESBServiceConstant.SOURCE_SYSTEM));
		}
		if(textMessage.propertyExists(ESBServiceConstant.TARGET_SYSTEM)){
			esbHeader.setTargetSystem(textMessage.getStringProperty(ESBServiceConstant.TARGET_SYSTEM));
		}
		if(textMessage.propertyExists(ESBServiceConstant.VERSION)){
			esbHeader.setVersion(textMessage.getStringProperty(ESBServiceConstant.VERSION));
		}
		String excptnString = textMessage.getText();
		CommonExceptionInfo commonExceptionInfo = (CommonExceptionInfo) messageTransform.toMessage(excptnString);
		
		EsbExceptionInfo esbExceptionInfo = new EsbExceptionInfo();
		esbExceptionInfo.setEsbHeader(esbHeader);
		esbExceptionInfo.setCommonExceptionInfo(commonExceptionInfo);
		if(textMessage.propertyExists(ESBServiceConstant.HOST_IP)){
			esbExceptionInfo.setHostIp(textMessage.getStringProperty(ESBServiceConstant.HOST_IP));
		}
		esbExceptionInfo.setCreateTime(new Date());
		return esbExceptionInfo;
	}

	public IMessageTransform getMessageTransform() {
		return messageTransform;
	}

	public void setMessageTransform(IMessageTransform messageTransform) {
		this.messageTransform = messageTransform;
	}

}

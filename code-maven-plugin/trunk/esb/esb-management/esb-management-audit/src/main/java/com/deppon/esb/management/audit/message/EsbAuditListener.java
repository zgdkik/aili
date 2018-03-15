/*
 * PROJECT NAME: esb-management-audit
 * PACKAGE NAME: com.deppon.esb.management.audit.message
 * FILE    NAME: EsbAuditListener.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.audit.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.audit.domain.EsbAuditInfo;
import com.deppon.esb.management.audit.service.IEsbAuditService;
import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.EsbLogMessage;
import com.deppon.esb.management.common.entity.jms.header.AuthInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.common.jms.SessionAwareBatchMessageListener;
import com.deppon.esb.management.common.util.EsbHeaderUtil;

/**
 * ESB审计日志监听器.
 * 
 * @author HuangHua
 * @date 2013-1-10 下午4:39:42
 */
public class EsbAuditListener implements SessionAwareBatchMessageListener<TextMessage> {

	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EsbAuditListener.class);

	/** 审计日志处理service接口. */
	private IEsbAuditService esbAuditService;

	/**
	 * 监听消息.
	 * 
	 * @author HuangHua
	 * @date 2013-4-11 下午3:01:20
	 * @see com.deppon.esb.management.common.jms.SessionAwareBatchMessageListener#onMessages(javax.jms.Session, java.util.List)
	 */
	@Override
	public void onMessages(Session session, List<TextMessage> messages)
			throws JMSException {
		for (TextMessage message : messages) {
			TextMessage textMessage = (TextMessage) message;
			try {
				if(textMessage.propertyExists(ESBServiceConstant.CONFORMITY)){
					List<EsbLogMessage> logMessage = generateEsbLogMessage(textMessage.getText());
					List<EsbAuditInfo> auditList = generateEsbAuditInfo(logMessage);
					esbAuditService.saveAudit(auditList);
				} else {
					EsbAuditInfo esbAuditInfo =null;
					esbAuditInfo = generateEntity(textMessage);
					esbAuditService.saveAudit(esbAuditInfo);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		
		}
	}

	/**
	 * Generate entity.
	 * 
	 * @param textMessage
	 *            the text message
	 * @return the esb audit info
	 * @author HuangHua
	 * @throws JMSException
	 * @date 2013-1-10 下午4:47:01
	 */
	private EsbAuditInfo generateEntity(TextMessage textMessage)
			throws JMSException {
		EsbAuditInfo esbAuditInfo = new EsbAuditInfo();
		esbAuditInfo.setCreateTime(new Date());
		esbAuditInfo.setEsbBody(textMessage.getText());
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
		if (textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC1)) {
			String bizDesc1 = textMessage
					.getStringProperty(ESBServiceConstant.BUSINESSDESC1);
			esbHeader.setBusinessDesc1(bizDesc1);
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC2)){
			String bizDesc2 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC2);
			esbHeader.setBusinessDesc2(bizDesc2);
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC3)){
			String bizDesc3 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC3);
			esbHeader.setBusinessDesc3(bizDesc3);
		}
		if (textMessage.propertyExists(ESBServiceConstant.BUSINESSID)) {
			String businessId = textMessage.getStringProperty(ESBServiceConstant.BUSINESSID);
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
			esbHeader.setRequestId(textMessage.getStringProperty(ESBServiceConstant.REQUEST_ID));
		}
		if(textMessage.propertyExists(ESBServiceConstant.RESPONSE_ID)){
			String responseId = textMessage.getStringProperty(ESBServiceConstant.RESPONSE_ID);
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
		esbAuditInfo.setEsbHeader(esbHeader);
		return esbAuditInfo;
	}

	/**
	 * 获取 审计日志处理service接口.
	 * 
	 * @return the 审计日志处理service接口
	 */
	public IEsbAuditService getEsbAuditService() {
		return esbAuditService;
	}

	/**
	 * 设置 审计日志处理service接口.
	 * 
	 * @param esbAuditService
	 *            the new 审计日志处理service接口
	 */
	public void setEsbAuditService(IEsbAuditService esbAuditService) {
		this.esbAuditService = esbAuditService;
	}
	private ObjectMapper mapper ;
	/**
	 * 
	 * 生成审计日志对象
	 * @author qiancheng
	 * @param 
	 * @date 2013-4-19 上午9:53:21
	 * @return
	 */
	public List<EsbLogMessage> generateEsbLogMessage(String message)throws Exception{
		if(message == null){
			return new ArrayList<EsbLogMessage>();
		}
		return  mapper.readValue(message, new TypeReference<List<EsbLogMessage>>(){});
	}

	public List<EsbAuditInfo> generateEsbAuditInfo(List<EsbLogMessage> list){
		List<EsbAuditInfo> target = new ArrayList<EsbAuditInfo>();
		for(EsbLogMessage msg :list){
			EsbAuditInfo info = new EsbAuditInfo();
			info.setEsbBody(msg.getBody());
			Long dateLongValue = (Long)msg.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME);
			info.setCreateTime(new Date(dateLongValue));
			EsbHeader esbHeader = EsbHeaderUtil.map2ESBHeader(msg.getHeader());
			info.setEsbHeader(esbHeader);
			target.add(info);
		}
		return target;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	
}

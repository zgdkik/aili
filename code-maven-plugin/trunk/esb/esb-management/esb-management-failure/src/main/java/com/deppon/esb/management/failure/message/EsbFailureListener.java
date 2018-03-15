/*
 * PROJECT NAME: esb-management-Failure
 * PACKAGE NAME: com.deppon.esb.management.Failure.message
 * FILE    NAME: EsbFailureListener.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.failure.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.EsbLogMessage;
import com.deppon.esb.management.common.entity.jms.header.AuthInfo;
import com.deppon.esb.management.common.entity.jms.header.EsbHeader;
import com.deppon.esb.management.common.util.EsbHeaderUtil;
import com.deppon.esb.management.failure.domain.EsbFailureInfo;
import com.deppon.esb.management.failure.service.IEsbFailureService;

/**
 * 失败消息监听器
 * 获取失败信息保
 */
public class EsbFailureListener implements MessageListener {

	/**
	 * 日志 
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EsbFailureListener.class);

	/**
	 * 失败消息处理服务类
	 */
	private IEsbFailureService esbFailureService;
	
	private ObjectMapper mapper;

	/**
	 * 监听失败消息
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				if(textMessage.getText() == null ||"".equals(textMessage.getText())){
					LOGGER.warn("得到一个空消息");
					return ;
				}
				if (message.propertyExists(ESBServiceConstant.CONFORMITY)) {
					List<EsbLogMessage> logMessage = generateEsbLogMessage(((TextMessage) message).getText());
					List<EsbFailureInfo> esbFailureInfo = generateEsbFailure(logMessage);
					esbFailureService.saveFailure(esbFailureInfo);
				} else {
					EsbFailureInfo esbFailureInfo = generateEntity(textMessage);
					esbFailureService.saveFailure(esbFailureInfo);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		} else {
			LOGGER.error("监听到的消息不是 TextMessage 请消息发送端确认发送的消息:"
					+ message.toString());
		}
	}
	
	/**
	 *
	 * @author HuangHua
	 * @date 2013-6-3 下午4:52:08
	 */
	private List<EsbFailureInfo> generateEsbFailure(
			List<EsbLogMessage> logMessage) {
		List<EsbFailureInfo> target = new ArrayList<EsbFailureInfo>();
		for(EsbLogMessage msg :logMessage){
			EsbFailureInfo info = new EsbFailureInfo();
			info.setEsbBody(msg.getBody());
			if(msg.getHeader().containsKey(ESBServiceConstant.ESB_LOGMSG_CREATETIME)){
				Long dateLongValue = (Long)msg.getHeader().get(ESBServiceConstant.ESB_LOGMSG_CREATETIME);
				info.setCreateTime(new Date(dateLongValue));
			} else {
				info.setCreateTime(new Date());
			}
			EsbHeader esbHeader = EsbHeaderUtil.map2ESBHeader(msg.getHeader());
			info.setEsbHeader(esbHeader);
			target.add(info);
		}
		return target;
	}

	/**
	 * 生成日志对象
	 * @author HuangHua
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @date 2013-6-3 下午4:48:59
	 */
	public  List<EsbLogMessage> generateEsbLogMessage(String message) throws JsonParseException, JsonMappingException, IOException{
		if(message == null){
			return new ArrayList<EsbLogMessage>();
		}
		return  mapper.readValue(message, new TypeReference<List<EsbLogMessage>>(){});
	}

	/**
	 * 更具监听到的失败信息生成失败消息实体
	 */
	private EsbFailureInfo generateEntity(TextMessage textMessage)
			throws JMSException {
		EsbFailureInfo esbFailureInfo = new EsbFailureInfo();
		esbFailureInfo.setCreateTime(new Date());
		esbFailureInfo.setEsbBody(textMessage.getText());
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
			if(bizDesc1 != null){
				if (bizDesc1.length() > 300) {// 字段长度截取
					bizDesc1 = StringUtils.substring(bizDesc1, 0, 300);
				}
			}
			esbHeader.setBusinessDesc1(bizDesc1);
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC2)){
			String bizDesc2 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC2);
			if(bizDesc2 != null){
				if (bizDesc2.length() > 300) {// 字段长度截取
					bizDesc2 = StringUtils.substring(bizDesc2, 0, 300);
				}
			}
			esbHeader.setBusinessDesc2(bizDesc2);
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSDESC3)){
			String bizDesc3 = textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC3);
			if(bizDesc3 != null){
				if (bizDesc3.length() > 300) {// 字段长度截取
					bizDesc3 = StringUtils.substring(bizDesc3, 0, 300);
				}
			}
			esbHeader.setBusinessDesc3(textMessage.getStringProperty(ESBServiceConstant.BUSINESSDESC3));
		}
		if(textMessage.propertyExists(ESBServiceConstant.BUSINESSID)){
			String businessId = textMessage.getStringProperty(ESBServiceConstant.BUSINESSID);
			if(businessId != null){
				if (businessId.length() > 300) {// 字段长度截取
					businessId = StringUtils.substring(businessId, 0, 300);
				}
			}
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
			if(requestId != null){
				if (requestId.length() > 80) {// 字段长度截取
					requestId = StringUtils.substring(requestId, 0, 80);
				}
			}
			esbHeader.setRequestId(requestId);
		}
		if(textMessage.propertyExists(ESBServiceConstant.RESPONSE_ID)){
			String responseId = textMessage.getStringProperty(ESBServiceConstant.RESPONSE_ID);
			if(responseId != null){
				if (responseId.length() > 80) {// 字段长度截取
					responseId = StringUtils.substring(responseId, 0, 80);
				}
			}
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
		esbFailureInfo.setEsbHeader(esbHeader);
		return esbFailureInfo;
	}

	public IEsbFailureService getEsbFailureService() {
		return esbFailureService;
	}

	public void setEsbFailureService(IEsbFailureService esbFailureService) {
		this.esbFailureService = esbFailureService;
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}


}

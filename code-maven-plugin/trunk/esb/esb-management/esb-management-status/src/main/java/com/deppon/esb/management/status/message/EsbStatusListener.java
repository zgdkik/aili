/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status
 * FILE    NAME: EsbStatusListener.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.status.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;

import com.deppon.esb.management.common.constant.ESBServiceConstant;
import com.deppon.esb.management.common.entity.EsbLogMessage;
import com.deppon.esb.management.common.entity.jms.header.StatusInfo;
import com.deppon.esb.management.common.exception.ESBStatusIllegalPatternException;
import com.deppon.esb.management.common.jms.SessionAwareBatchMessageListener;
import com.deppon.esb.management.status.domain.EsbStatusInfo;
import com.deppon.esb.management.status.service.IEsbStatusService;

/**
 * ESB状态日志监听器.
 * 
 * @author HuangHua
 * @date 2013-1-10 下午6:11:39
 */
public class EsbStatusListener implements
		SessionAwareBatchMessageListener<TextMessage> {

	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EsbStatusListener.class);

	/** ESB状态处理服务接口. */
	private IEsbStatusService esbStatusService;

	private ObjectMapper mapper;

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 监听消息.
	 * 
	 * @author HuangHua
	 * @date 2013-4-11 下午2:58:11
	 * @see com.deppon.esb.management.common.jms.SessionAwareBatchMessageListener#onMessages(javax.jms.Session,
	 *      java.util.List)
	 */
	@Override
	public void onMessages(Session session, List<TextMessage> messages)
			throws JMSException {
		for (TextMessage message : messages) {
			TextMessage textMessage = (TextMessage) message;
			try {
				List<EsbStatusInfo> esbStatusInfos = null;
				if(textMessage.getText() == null ||"".equals(textMessage.getText())){
					LOGGER.warn("得到一个空消息");
					return ;
				}
				if (textMessage.propertyExists(ESBServiceConstant.CONFORMITY)) {
					List<EsbLogMessage> logMessage = generateEsblogMessage(textMessage
							.getText());
					esbStatusInfos = generateStatusInfos(logMessage);
				} else {
					esbStatusInfos = generateStatusInfos(textMessage);
				}
				esbStatusService.saveStatus(esbStatusInfos);
			} catch (JsonParseException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (JsonMappingException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (JmsException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	protected List<EsbLogMessage> generateEsblogMessage(String message)
			throws JsonParseException, JsonMappingException, IOException {
		if (message == null) {
			return new ArrayList<EsbLogMessage>();
		}
		return mapper.readValue(message,
				new TypeReference<List<EsbLogMessage>>() {
				});
	}

	/**
	 * 状态格式不正确的时候,不进行保存,只打log
	 * @author HuangHua
	 * @date 2013-6-3 下午4:11:07
	 */
	private List<EsbStatusInfo> generateStatusInfos(List<EsbLogMessage> list) {
		List<EsbStatusInfo> esbStatusInfos = new ArrayList<EsbStatusInfo>();
		for (EsbLogMessage msg : list) {
			String backServiceCode = (String) msg.getHeader().get(
					ESBServiceConstant.BACK_SERVICE_CODE);
			String businessId = (String) msg.getHeader().get(
					ESBServiceConstant.BUSINESSID);
			if(businessId != null){
				if (businessId.length() > 300) {// 字段长度截取
					businessId = StringUtils.substring(businessId, 0, 300);
				}
			}
			String esbServiceCode = (String) msg.getHeader().get(
					ESBServiceConstant.ESB_SERVICE_CODE);
			String requestId = (String) msg.getHeader().get(
					ESBServiceConstant.REQUEST_ID);
			String responseId = (String) msg.getHeader().get(
					ESBServiceConstant.RESPONSE_ID);
			responseId = StringUtils.substring(responseId, 0, 80);
			String sourceSystem = (String) msg.getHeader().get(
					ESBServiceConstant.SOURCE_SYSTEM);
			String targetSystem = (String) msg.getHeader().get(
					ESBServiceConstant.TARGET_SYSTEM);
			String[] statusArray = msg.getBody().split(":");
			for (String st : statusArray) {// 根据状态消息格式转换
				EsbStatusInfo info = new EsbStatusInfo();
				info.setCreateTime(new Date());
				info.setBackServiceCode(backServiceCode);
				info.setBusinessId(businessId);
				info.setEsbServiceCode(esbServiceCode);
				info.setRequestId(requestId);
				info.setResponseId(responseId);
				info.setSourceSystem(sourceSystem);
				info.setTargetSystem(targetSystem);
				String[] sts = st.split("@");

				String statusId = sts[0].trim();
				String timeStamp = "0";
				if (sts.length >= 2) {// 如果状态不规范,则不会保存改状态
					timeStamp = sts[1].trim();
					if (!timeStamp.matches("[0-9]{1,}")) {
						printLog(esbServiceCode, backServiceCode, businessId, requestId, responseId, sourceSystem, targetSystem, st);
						continue;
					}
				} else {
					printLog(esbServiceCode, backServiceCode, businessId, requestId, responseId, sourceSystem, targetSystem, st);
					continue;
				}

				StatusInfo statusInfo = new StatusInfo();
				statusInfo.setStatusId(statusId);
				statusInfo.setTimeStamp(Long.parseLong(timeStamp));
				info.setStatusInfo(statusInfo);
				esbStatusInfos.add(info);
			}
		}
		return esbStatusInfos;
	}

	/**
	 * Generate status infos.
	 * 
	 * @param textMessage
	 *            the text message
	 * @return the list
	 * @throws JMSException
	 *             the jMS exception
	 * @author HuangHua
	 * @date 2013-1-10 下午6:16:10
	 */
	private List<EsbStatusInfo> generateStatusInfos(TextMessage textMessage)
			throws JMSException {
		String status = textMessage.getText();
		/*
		 * 格式 <StatusId>@<TimeStamp>:<StatusId>@<TimeStamp>:… 例子
		 * 302@123456789:305@123456790
		 */
		if (!status
				.matches("(ST_[0-9]{3}@[0-9]{1,}){1,}(:ST_[0-9]{3}@[0-9]{1,}){0,}")) {
			StringBuffer stringBuffer = new StringBuffer();
			@SuppressWarnings("unchecked")
			Enumeration<String> headers = textMessage.getPropertyNames();
			while (headers.hasMoreElements()) {
				String propertyName = headers.nextElement();
				stringBuffer.append(propertyName);
				stringBuffer.append(":");
				stringBuffer
						.append(textMessage.getObjectProperty(propertyName));
				stringBuffer.append(" ; ");
			}
			throw new ESBStatusIllegalPatternException(
					"状态消息格式不合法.原始消息:header:[" + stringBuffer + "]body["
							+ status + "]");
		}
		List<EsbStatusInfo> esbStatusInfos = new ArrayList<EsbStatusInfo>();
		String backServiceCode = textMessage
				.getStringProperty(ESBServiceConstant.BACK_SERVICE_CODE);
		String businessId = textMessage
				.getStringProperty(ESBServiceConstant.BUSINESSID);
		businessId = StringUtils.substring(businessId, 0, 300);
		String esbServiceCode = textMessage
				.getStringProperty(ESBServiceConstant.ESB_SERVICE_CODE);
		String requestId = textMessage
				.getStringProperty(ESBServiceConstant.REQUEST_ID);
		String responseId = textMessage
				.getStringProperty(ESBServiceConstant.RESPONSE_ID);
		responseId = StringUtils.substring(responseId, 0, 80);
		String sourceSystem = textMessage
				.getStringProperty(ESBServiceConstant.SOURCE_SYSTEM);
		String targetSystem = textMessage
				.getStringProperty(ESBServiceConstant.TARGET_SYSTEM);
		String[] statusArray = status.split(":");
		for (String st : statusArray) {// 根据状态消息格式转换
			EsbStatusInfo info = new EsbStatusInfo();
			info.setCreateTime(new Date());
			info.setBackServiceCode(backServiceCode);
			info.setBusinessId(businessId);
			info.setEsbServiceCode(esbServiceCode);
			info.setRequestId(requestId);
			info.setResponseId(responseId);
			info.setSourceSystem(sourceSystem);
			info.setTargetSystem(targetSystem);
			String[] sts = st.split("@");
			StatusInfo statusInfo = new StatusInfo();
			statusInfo.setStatusId(sts[0]);
			statusInfo.setTimeStamp(Long.parseLong(sts[1]));
			info.setStatusInfo(statusInfo);
			esbStatusInfos.add(info);
		}
		return esbStatusInfos;
	}

	/**
	 * 获取 eSB状态处理服务接口.
	 * 
	 * @return the eSB状态处理服务接口
	 */
	public IEsbStatusService getEsbStatusService() {
		return esbStatusService;
	}

	/**
	 * 设置 eSB状态处理服务接口.
	 * 
	 * @param esbStatusService
	 *            the new eSB状态处理服务接口
	 */
	public void setEsbStatusService(IEsbStatusService esbStatusService) {
		this.esbStatusService = esbStatusService;
	}

	private void printLog(String esbServiceCode, String backServiceCode,
			String businessId, String requestId, String responseId,
			String sourceSystem, String targetSystem,String str) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Status warn log:");
		buffer.append("esbServiceCode:");
		buffer.append(esbServiceCode);
		buffer.append(";");

		buffer.append("backServiceCode:");
		buffer.append(backServiceCode);
		buffer.append(";");

		buffer.append("businessId:");
		buffer.append(businessId);
		buffer.append(";");

		buffer.append("requestId:");
		buffer.append(requestId);
		buffer.append(";");

		buffer.append("responseId:");
		buffer.append(responseId);
		buffer.append(";");

		buffer.append("sourceSystem:");
		buffer.append(sourceSystem);
		buffer.append(";");

		buffer.append("targetSystem:");
		buffer.append(targetSystem);
		buffer.append(";");
		
		buffer.append("status:");
		buffer.append(str);

		LOGGER.warn(buffer.toString());
	}

}

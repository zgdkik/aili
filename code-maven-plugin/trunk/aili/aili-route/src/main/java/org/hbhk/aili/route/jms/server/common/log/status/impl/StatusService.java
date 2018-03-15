package org.hbhk.aili.route.jms.server.common.log.status.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.server.common.log.status.IStatusService;
import org.hbhk.aili.route.jms.server.common.log.status.StatusInfo;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;


/**
 * 状态处理类.
 * 
 * @author HuangHua
 */
public class StatusService implements IStatusService {

	/** The jms template. */
	private JmsTemplate jmsTemplate;

	/** The status queue name. */
	private String statusQueueName;

	/**
	 * 保存状态信息.
	 * 
	 * @param headers
	 *            the headers
	 */
	public void saveStatus(Map<String, Object> headers) {
		StatusInfo status = buildStatus(headers);
		if (status.getBody() != null && !"".equals(status.getBody())) {
			sendStatus(status);
		}
	}

	/**
	 * 生成status的规范格式 如：key@value:key@value:key@value 注：会删掉以前的状态信息.
	 * 
	 * @param headers
	 *            the headers
	 * @return the status info
	 */
	private StatusInfo buildStatus(Map<String, Object> headers) {

		StatusInfo status = new StatusInfo();

		status.setBizId(String.valueOf(headers
				.get(ESBServiceConstant.BUSINESSID)));
		status.setBizDesc1(String.valueOf(headers
				.get(ESBServiceConstant.BUSINESSDESC1)));
		status.setBizDesc2(String.valueOf(headers
				.get(ESBServiceConstant.BUSINESSDESC2)));
		status.setBizDesc3(String.valueOf(headers
				.get(ESBServiceConstant.BUSINESSDESC3)));
		status.setRequestId(String.valueOf(headers
				.get(ESBServiceConstant.REQUEST_ID)));
		status.setResponseId(String.valueOf(headers
				.get(ESBServiceConstant.RESPONSE_ID)));
		status.setServiceCode(String.valueOf(headers
				.get(ESBServiceConstant.ESB_SERVICE_CODE)));
		status.setSourceSystem(String.valueOf(headers
				.get(ESBServiceConstant.SOURCE_SYSTEM)));
		status.setTargetSystem(String.valueOf(headers
				.get(ESBServiceConstant.TARGET_SYSTEM)));

		StringBuffer statusBody = new StringBuffer();

		List<String> keyList = new ArrayList<String>();

		Iterator<Entry<String, Object>> iterator = headers.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			String value = String.valueOf(entry.getValue());// Long 2 String
			if (isStatusKey(key) && isNum(value)) {
/*
				if (key.length() >= 3) {
					key = key.substring(3);// 去掉前面的前缀：ST_
				}
*/
				statusBody.append(key + "@" + value + ":");
				keyList.add(key);
			}
		}
		// for (String key : keyList) {// 删除状态
		// headers.remove(key);
		// }
		if (statusBody.length() > 1) {
			status.setBody(statusBody.substring(0, statusBody.length() - 1));// 去掉最后一个冒号
		}
		return status;
	}

	/**
	 * Send status.
	 * 
	 * @param status
	 *            the status
	 */
	private void sendStatus(final StatusInfo status) {
		jmsTemplate.send(statusQueueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage(status
						.getBody());
				message.setStringProperty(ESBServiceConstant.ESB_SERVICE_CODE,
						status.getServiceCode());
				message.setStringProperty(ESBServiceConstant.BUSINESSID,
						status.getBizId());
				message.setStringProperty(ESBServiceConstant.BUSINESSDESC1,
						status.getBizDesc1());
				message.setStringProperty(ESBServiceConstant.BUSINESSDESC2,
						status.getBizDesc2());
				message.setStringProperty(ESBServiceConstant.BUSINESSDESC3,
						status.getBizDesc3());
				message.setStringProperty(ESBServiceConstant.REQUEST_ID,
						status.getRequestId());
				message.setStringProperty(ESBServiceConstant.RESPONSE_ID,
						status.getResponseId());
				message.setStringProperty(ESBServiceConstant.SOURCE_SYSTEM,
						status.getSourceSystem());
				message.setStringProperty(ESBServiceConstant.TARGET_SYSTEM,
						status.getTargetSystem());
				return message;
			}
		});
	}

	/**
	 * Checks if is status key.</br>
	 * only record three phase status:</br>
	 *	1. status in ESB."ST_1" is request in consumer;</br>
	 *	2. status in ESB."ST_2" is request in ESB;</br>
	 *	3. "ST_4" is response in ESB.</br>
	 * 
	 * @param str
	 *            the str
	 * @return true, if is status key
	 */
	private boolean isStatusKey(String str) {
		if (str == null) {
			return false;
		}
		str = str.trim();
		if (str.toUpperCase().startsWith("ST_1")
				|| str.toUpperCase().startsWith("ST_2")
				|| str.toUpperCase().startsWith("ST_4")
				|| str.toUpperCase().equals("ST_308")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是数字.
	 * 
	 * @param str
	 *            the str
	 * @return true, if is num
	 */
	private boolean isNum(String str) {
		if (!str.matches("[0-9]*")) {
			return false;
		}
		return true;
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
	 * Gets the status queue name.
	 * 
	 * @return the status queue name
	 */
	public String getStatusQueueName() {
		return statusQueueName;
	}

	/**
	 * Sets the status queue name.
	 * 
	 * @param statusQueueName
	 *            the new status queue name
	 */
	public void setStatusQueueName(String statusQueueName) {
		this.statusQueueName = statusQueueName;
	}

}

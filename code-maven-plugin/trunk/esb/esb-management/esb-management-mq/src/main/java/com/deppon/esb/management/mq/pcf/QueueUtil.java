/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.pcf
 * FILE    NAME: QueueUtil.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.pcf;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.mq.domain.LocalQueueBean;
import com.ibm.mq.MQException;
import com.ibm.mq.pcf.CMQC;
import com.ibm.mq.pcf.CMQCFC;
import com.ibm.mq.pcf.PCFException;
import com.ibm.mq.pcf.PCFMessage;
import com.ibm.mq.pcf.PCFMessageAgent;

/**
 * WMQ的队列工具类,使用pcf实现.
 * 
 * @author HuangHua
 * @date 2013-1-28 下午1:47:38
 */
public class QueueUtil {

	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QueueUtil.class);

	/**
	 * List local queue.
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param queueNameReg
	 *            队列名
	 * @return the list
	 * @author HuangHua
	 * @date 2013-1-28 下午2:53:12
	 */
	public static List<LocalQueueBean> listLocalQueue(String hostname,
			int port, String channel, String queueNameReg) {
		List<LocalQueueBean> queueBeans = new ArrayList<LocalQueueBean>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH.mm.ss");
		PCFMessage request;
		PCFMessage[] response;
		// Build the request
		request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q);
		request.addParameter(CMQC.MQCA_Q_NAME, queueNameReg);
		request.addParameter(CMQC.MQIA_Q_TYPE, CMQC.MQQT_LOCAL);
		request.addParameter(CMQCFC.MQIACF_Q_ATTRS, new int[] {
				CMQC.MQCA_Q_NAME, CMQC.MQIA_CURRENT_Q_DEPTH,
				CMQC.MQCA_CREATION_DATE, CMQC.MQCA_CREATION_TIME,
				CMQC.MQIA_OPEN_OUTPUT_COUNT, CMQC.MQIA_OPEN_INPUT_COUNT,
				CMQC.MQIA_MAX_Q_DEPTH, CMQC.MQIA_INHIBIT_PUT,
				CMQC.MQIA_INHIBIT_GET });
		PCFMessageAgent agent = null;
		try {
			// Use the agent to send the request
			agent = new PCFMessageAgent(hostname, port, channel);
			response = agent.send(request);

			// generate result
			String qmgrName = agent.getQManagerName();
			for (int i = 0; i < response.length; i++) {
				LocalQueueBean queueBean = new LocalQueueBean();
				queueBean.setQmgrName(qmgrName.trim());
				String name = response[i]
						.getStringParameterValue(CMQC.MQCA_Q_NAME);
				queueBean.setName(name.trim());

				int allowGet = response[i]
						.getIntParameterValue(CMQC.MQIA_INHIBIT_GET);
				if (allowGet == 0) {
					queueBean.setAllowGet(true);
				} else if (allowGet == 1) {
					queueBean.setAllowGet(false);
				}

				int allowPut = response[i]
						.getIntParameterValue(CMQC.MQIA_INHIBIT_PUT);
				if (allowPut == 0) {
					queueBean.setAllowPut(true);
				} else if (allowPut == 1) {
					queueBean.setAllowPut(false);
				}

				String createDate = response[i]
						.getStringParameterValue(CMQC.MQCA_CREATION_DATE);
				String createTime = response[i]
						.getStringParameterValue(CMQC.MQCA_CREATION_TIME);
				queueBean.setQueueCreateTime(dateFormat.parse(createDate.trim()
						+ " " + createTime.trim()));

				int currentDepth = response[i]
						.getIntParameterValue(CMQC.MQIA_CURRENT_Q_DEPTH);
				queueBean.setCurrentDepth(currentDepth);

				int inputCount = response[i]
						.getIntParameterValue(CMQC.MQIA_OPEN_INPUT_COUNT);
				queueBean.setInputCount(inputCount);

				int maxDepth = response[i]
						.getIntParameterValue(CMQC.MQIA_MAX_Q_DEPTH);
				queueBean.setMaxDepth(maxDepth);

				int outputCount = response[i]
						.getIntParameterValue(CMQC.MQIA_OPEN_OUTPUT_COUNT);
				queueBean.setOutputCount(outputCount);
				queueBean.setCreateTime(new Date());
				queueBean.setId(hostname);
				queueBeans.add(queueBean);
			}
		} catch (PCFException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (MQException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (ParseException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (agent != null) {
				try {
					agent.disconnect();
				} catch (MQException e) {
					LOGGER.error(e.getLocalizedMessage(), e);
				}
			}
		}

		return queueBeans;
	}

	/**
	 * List local queue depth.
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param queueNameReg
	 *            队列名
	 * @return the list
	 * @author HuangHua
	 * @date 2013-1-28 下午2:53:12
	 */
	public static List<LocalQueueBean> listLocalQueueDepth(String hostname,
			int port, String channel, String queueNameReg) {
		List<LocalQueueBean> queueBeans = new ArrayList<LocalQueueBean>();
		PCFMessage request;
		PCFMessage[] response;
		// Build the request
		request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q);
		request.addParameter(CMQC.MQCA_Q_NAME, queueNameReg);
		request.addParameter(CMQC.MQIA_Q_TYPE, CMQC.MQQT_LOCAL);
		request.addParameter(CMQCFC.MQIACF_Q_ATTRS, new int[] {
				CMQC.MQCA_Q_NAME, CMQC.MQIA_CURRENT_Q_DEPTH,
				CMQC.MQIA_MAX_Q_DEPTH });
		PCFMessageAgent agent = null;
		try {
			// Use the agent to send the request
			agent = new PCFMessageAgent(hostname, port, channel);
			response = agent.send(request);

			// generate result
			String qmgrName = agent.getQManagerName();
			for (int i = 0; i < response.length; i++) {
				LocalQueueBean queueBean = new LocalQueueBean();
				queueBean.setQmgrName(qmgrName.trim());
				String name = response[i]
						.getStringParameterValue(CMQC.MQCA_Q_NAME);
				queueBean.setName(name.trim());

				int currentDepth = response[i]
						.getIntParameterValue(CMQC.MQIA_CURRENT_Q_DEPTH);
				queueBean.setCurrentDepth(currentDepth);

				int maxDepth = response[i]
						.getIntParameterValue(CMQC.MQIA_MAX_Q_DEPTH);
				queueBean.setMaxDepth(maxDepth);

				queueBeans.add(queueBean);
			}
		} catch (PCFException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (MQException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (agent != null) {
				try {
					agent.disconnect();
				} catch (MQException e) {
					LOGGER.error(e.getLocalizedMessage(), e);
				}
			}
		}

		return queueBeans;
	}

	/**
	 * false: key = "hostname:" + hostname +",port:" + port + ",channel:" +
	 * channel; value = true--connection;false--disconnection true: key =
	 * qmgrName; value = true--connection;false--disconnection
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param queueNameReg
	 *            队列名
	 * @return the list
	 * @author HuangHua
	 * @date 2013-1-28 下午2:53:12
	 */
	public static Map<String, Boolean> checkConnection(String hostname,
			int port, String channel) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		String key = "hostname:" + hostname + ",port:" + port + ",channel:"
				+ channel;
		result.put(key, false);
		PCFMessage request;
		// Build the request
		request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_Q);
		request.addParameter(CMQC.MQCA_Q_NAME, "*");
		request.addParameter(CMQC.MQIA_Q_TYPE, CMQC.MQQT_LOCAL);
		request.addParameter(CMQCFC.MQIACF_Q_ATTRS,
				new int[] { CMQC.MQCA_Q_NAME });
		PCFMessageAgent agent = null;
		try {
			// Use the agent to send the request
			agent = new PCFMessageAgent(hostname, port, channel);
			agent.send(request);
			result.put(agent.getQManagerName(), true);
			result.remove(key);
		} catch (PCFException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (MQException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (agent != null) {
				try {
					agent.disconnect();
				} catch (MQException e) {
					LOGGER.error(e.getLocalizedMessage(), e);
				}
			}
		}
		return result;
	}

}

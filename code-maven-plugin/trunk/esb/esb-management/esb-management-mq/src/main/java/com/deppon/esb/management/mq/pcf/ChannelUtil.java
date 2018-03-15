/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.pcf
 * FILE    NAME: ChannelUtil.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.pcf;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.mq.domain.ChannelBean;
import com.ibm.mq.MQException;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.pcf.CMQCFC;
import com.ibm.mq.pcf.PCFException;
import com.ibm.mq.pcf.PCFMessage;
import com.ibm.mq.pcf.PCFMessageAgent;

/**
 * WMQ的通道工具类,使用pcf实现.
 * 
 * @author HuangHua
 * @date 2013-1-28 下午2:51:45
 */
public class ChannelUtil {

	/** 常量定义 LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChannelUtil.class);

	/**
	 * List all channel names and types.
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param channelNameReg
	 *            通道名
	 * @return the list
	 */
	public static List<ChannelBean> listAllChannelNamesAndTypes(
			String hostname, int port, String channel, String channelNameReg) {
		List<ChannelBean> channelBeans = new ArrayList<ChannelBean>();
		PCFMessage[] responses;
		PCFMessage request;
		// Build the request
		request = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_NAMES);
		request.addParameter(MQConstants.MQCACH_CHANNEL_NAME, "*");

		// Channel type = ALL.
		request.addParameter(MQConstants.MQIACH_CHANNEL_TYPE,
				MQConstants.MQCHT_ALL);

		PCFMessageAgent agent = null;
		try {
			agent = new PCFMessageAgent(hostname, port, channel);
			responses = agent.send(request);

			String qmgrName = agent.getQManagerName();
			for (int responseNumber = 0; responseNumber < responses.length; responseNumber++) {
				String[] names = (String[]) responses[responseNumber]
						.getParameterValue(MQConstants.MQCACH_CHANNEL_NAMES);

				// There might not be any names, so test this first before
				// attempting to parse the object.
				if (names != null) {
					int[] types = (int[]) responses[responseNumber]
							.getParameterValue(MQConstants.MQIACH_CHANNEL_TYPES);
					for (int index = 0; index < names.length; index++) {
						ChannelBean channelBean = new ChannelBean();
						channelBean.setQmgrName(qmgrName.trim());
						channelBean.setName(names[index].trim());
						channelBean
								.setType(ChannelBean.channelTypes[types[index]]);
						channelBeans.add(channelBean);
					}
				}
			}
		} catch (PCFException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (MQException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				agent.disconnect();
			} catch (MQException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return channelBeans;
	}

	/**
	 * List channels.
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param channelNameReg
	 *            通道名
	 * @return the list
	 */
	public static List<ChannelBean> listChannels(String hostname, int port,
			String channel, String channelNameReg) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH.mm.ss");
		List<ChannelBean> channelBeans = new ArrayList<ChannelBean>();
		PCFMessage request;
		PCFMessage[] response;

		// Build the request
		request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_CHANNEL_STATUS);
		request.addParameter(CMQCFC.MQCACH_CHANNEL_NAME, channelNameReg);

		int[] attrs = new int[] { CMQCFC.MQCACH_CHANNEL_NAME,
				CMQCFC.MQCACH_CONNECTION_NAME, CMQCFC.MQIACH_MSGS,
				MQConstants.MQIACH_MAX_SHARING_CONVS,
				MQConstants.MQIACH_CURRENT_SHARING_CONVS,
				CMQCFC.MQCACH_LAST_MSG_DATE, CMQCFC.MQCACH_LAST_MSG_TIME,
				CMQCFC.MQIACH_CHANNEL_STATUS,
				MQConstants.MQCACH_CHANNEL_START_DATE,
				MQConstants.MQCACH_CHANNEL_START_TIME,
				MQConstants.MQIACH_BYTES_RCVD, MQConstants.MQIACH_BYTES_SENT };
		request.addParameter(MQConstants.MQIACH_CHANNEL_INSTANCE_ATTRS, attrs);

		// Use the agent to send the request
		PCFMessageAgent agent = null;
		try {
			agent = new PCFMessageAgent(hostname, port, channel);
			response = agent.send(request);
			// generate result
			String qmgrName = agent.getQManagerName();
			for (int i = 0; i < response.length; i++) {
				ChannelBean channelBean = new ChannelBean();
				channelBean.setQmgrName(qmgrName);
				String name = response[i].getStringParameterValue(
						CMQCFC.MQCACH_CHANNEL_NAME).trim();
				channelBean.setName(name);

				String connectionName = response[i].getStringParameterValue(
						CMQCFC.MQCACH_CONNECTION_NAME).trim();
				channelBean.setConnectionName(connectionName);

				String setLastGetMsgDate = response[i]
						.getStringParameterValue(CMQCFC.MQCACH_LAST_MSG_DATE);
				String setLastGetMsgTime = response[i]
						.getStringParameterValue(CMQCFC.MQCACH_LAST_MSG_TIME);
				channelBean.setLastGetMsgTime(dateFormat
						.parse(setLastGetMsgDate.trim() + " "
								+ setLastGetMsgTime.trim()));

				int receivedBytes = response[i]
						.getIntParameterValue(MQConstants.MQIACH_BYTES_RCVD);
				channelBean.setReceivedBytes(receivedBytes);

				int sentBytes = response[i]
						.getIntParameterValue(MQConstants.MQIACH_BYTES_SENT);
				channelBean.setSentBytes(sentBytes);

				String startDate = response[i]
						.getStringParameterValue(MQConstants.MQCACH_CHANNEL_START_DATE);
				String startTime = response[i]
						.getStringParameterValue(MQConstants.MQCACH_CHANNEL_START_TIME);
				channelBean.setStartTime(dateFormat.parse(startDate.trim()
						+ " " + startTime.trim()));

				String state = ChannelBean.channelStatus[response[i]
						.getIntParameterValue(CMQCFC.MQIACH_CHANNEL_STATUS)];
				channelBean.setState(state);

				String type = ChannelBean.channelTypes[response[i]
						.getIntParameterValue(CMQCFC.MQIACH_CHANNEL_TYPE)];
				channelBean.setType(type);

				int msgs = response[i]
						.getIntParameterValue(MQConstants.MQIACH_MSGS);
				channelBean.setMsgs(msgs);

				int maxConversations;
				try {
					maxConversations = response[i]
							.getIntParameterValue(MQConstants.MQIACH_MAX_SHARING_CONVS);
				} catch (Exception e) {
					maxConversations = 0;
				}
				channelBean.setMaxConversations(maxConversations);

				int currentConversations;
				try {
					currentConversations = response[i]
							.getIntParameterValue(MQConstants.MQIACH_CURRENT_SHARING_CONVS);
				} catch (Exception e) {
					currentConversations = 0;
				}
				channelBean.setCurrentConversations(currentConversations);

				channelBeans.add(channelBean);
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
			try {
				agent.disconnect();
			} catch (MQException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return channelBeans;
	}
	
	/**
	 * List channels.
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口号
	 * @param channel
	 *            服务器连接通道
	 * @param channelNameReg
	 *            通道名
	 * @return the list
	 */
	public static List<ChannelBean> listChannels2(String hostname, int port,
			String channel, String channelNameReg) {
		List<ChannelBean> channelBeans = new ArrayList<ChannelBean>();
		PCFMessage request;
		PCFMessage[] response;

		// Build the request
		request = new PCFMessage(CMQCFC.MQCMD_INQUIRE_CHANNEL_STATUS);
//		request.addParameter(CMQCFC.MQCHLD_ALL, "*");
		request.addParameter(CMQCFC.MQCACH_CHANNEL_NAME, channelNameReg);

		// Use the agent to send the request
		PCFMessageAgent agent = null;
		try {
			agent = new PCFMessageAgent(hostname, port, channel);
			response = agent.send(request);
			// generate result
			String qmgrName = agent.getQManagerName();
			for (int i = 0; i < response.length; i++) {
				ChannelBean channelBean = new ChannelBean();
				channelBean.setQmgrName(qmgrName);
				String name = response[i].getStringParameterValue(
						CMQCFC.MQCACH_CHANNEL_NAME).trim();
				channelBean.setName(name);

				String state = ChannelBean.channelStatus[response[i]
						.getIntParameterValue(CMQCFC.MQIACH_CHANNEL_STATUS)];
				channelBean.setState(state);

				String type = ChannelBean.channelTypes[response[i]
						.getIntParameterValue(CMQCFC.MQIACH_CHANNEL_TYPE)];
				channelBean.setType(type);

				channelBeans.add(channelBean);
			}
		} catch (PCFException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (MQException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				agent.disconnect();
			} catch (MQException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return channelBeans;
	}

}

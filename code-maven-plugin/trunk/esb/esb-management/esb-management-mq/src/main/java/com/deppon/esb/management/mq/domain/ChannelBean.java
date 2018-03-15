/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.domain
 * FILE    NAME: ChannelBean.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.domain;

import java.util.Date;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 通道Bean.
 * 
 * @author HuangHua
 * @date 2013-1-25 下午2:05:04
 */
public class ChannelBean extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -3245343642536817641L;
	/**
	 * 与下面的"", "SDR", "SVR", "RCVR", "RQSTR", "", "CLTCN", "SVRCN", "CLUSRCVR",
	 * "CLUSSDR", ""这些值对应.作为一个中文翻译
	 */
	public static final String[] channelTypes = { "", "发送方", "服务器", "接收方",
			"请求方", "", "集群连接", "服务器连接", "集群接收方", "集群发送方", "" };
	public static final String[] channelStatus = {"","BINDING","STARTING","RUNNING","PAUSED",
		"STOPPING","RETRYING","STOPPED","REQUESTING","INITIALIZING"};
	public static final int SDR = 1;
	public static final int SVR = 2;
	public static final int RCVR = 3;
	public static final int RQSTR = 4;
	public static final int CLTCN = 6;
	public static final int SVRCN = 7;
	public static final int CLUSRCVR = 8;
	public static final int CLUSSDR = 9;

	/** 通道名. */
	private String name;

	/** 队列管理器名. */
	private String qmgrName;

	/** 通道类型. */
	private String type;

	/** 通道状态. */
	private String state;

	/** 连接名称(IP). */
	private String connectionName;

	/** 启动时间(日期+时间). */
	private Date startTime;

	/** 最后取消息时间(日期+时间). */
	private Date lastGetMsgTime;
	
	/**
	 * 消息
	 */
	private int msgs;

	/** 已发送的字节数. */
	private long sentBytes;

	/** 已接收的字节数. */
	private long receivedBytes;

	/** 最大对话数. */
	private int maxConversations;

	/** 当前对话数. */
	private int currentConversations;

	/**
	 * 获取 通道名.
	 * 
	 * @return the 通道名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 通道名.
	 * 
	 * @param name
	 *            the new 通道名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 通道类型.
	 * 
	 * @return the 通道类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置 通道类型.
	 * 
	 * @param type
	 *            the new 通道类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取 通道状态.
	 * 
	 * @return the 通道状态
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置 通道状态.
	 * 
	 * @param state
	 *            the new 通道状态
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 获取 连接名称(IP).
	 * 
	 * @return the 连接名称(IP)
	 */
	public String getConnectionName() {
		return connectionName;
	}

	/**
	 * 设置 连接名称(IP).
	 * 
	 * @param connectionName
	 *            the new 连接名称(IP)
	 */
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	/**
	 * 获取 启动时间(日期+时间).
	 * 
	 * @return the 启动时间(日期+时间)
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置 启动时间(日期+时间).
	 * 
	 * @param startTime
	 *            the new 启动时间(日期+时间)
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取 最后取消息时间(日期+时间).
	 * 
	 * @return the 最后取消息时间(日期+时间)
	 */
	public Date getLastGetMsgTime() {
		return lastGetMsgTime;
	}

	/**
	 * 设置 最后取消息时间(日期+时间).
	 * 
	 * @param lastGetMsgTime
	 *            the new 最后取消息时间(日期+时间)
	 */
	public void setLastGetMsgTime(Date lastGetMsgTime) {
		this.lastGetMsgTime = lastGetMsgTime;
	}

	/**
	 * 获取 已发送的字节数.
	 * 
	 * @return the 已发送的字节数
	 */
	public long getSentBytes() {
		return sentBytes;
	}

	/**
	 * 设置 已发送的字节数.
	 * 
	 * @param sentBytes
	 *            the new 已发送的字节数
	 */
	public void setSentBytes(long sentBytes) {
		this.sentBytes = sentBytes;
	}

	/**
	 * 获取 已接收的字节数.
	 * 
	 * @return the 已接收的字节数
	 */
	public long getReceivedBytes() {
		return receivedBytes;
	}

	/**
	 * 设置 已接收的字节数.
	 * 
	 * @param receivedBytes
	 *            the new 已接收的字节数
	 */
	public void setReceivedBytes(long receivedBytes) {
		this.receivedBytes = receivedBytes;
	}

	/**
	 * 获取 队列管理器名.
	 * 
	 * @return the 队列管理器名
	 */
	public String getQmgrName() {
		return qmgrName;
	}

	/**
	 * 设置 队列管理器名.
	 * 
	 * @param qmgrName
	 *            the new 队列管理器名
	 */
	public void setQmgrName(String qmgrName) {
		this.qmgrName = qmgrName;
	}

	public int getMaxConversations() {
		return maxConversations;
	}

	public void setMaxConversations(int maxConversations) {
		this.maxConversations = maxConversations;
	}

	public int getCurrentConversations() {
		return currentConversations;
	}

	public void setCurrentConversations(int currentConversations) {
		this.currentConversations = currentConversations;
	}

	public int getMsgs() {
		return msgs;
	}

	public void setMsgs(int msgs) {
		this.msgs = msgs;
	}
}

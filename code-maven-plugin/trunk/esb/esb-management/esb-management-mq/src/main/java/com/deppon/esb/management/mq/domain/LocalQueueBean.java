/*
 * PROJECT NAME: esb-management-mq
 * PACKAGE NAME: com.deppon.esb.management.mq.domain
 * FILE    NAME: QueueBean.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.mq.domain;

import java.util.Date;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 本地队列Bean.
 * 
 * @author HuangHua
 * @date 2013-1-25 下午2:02:41
 */
public class LocalQueueBean extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -106093542315511362L;
	
	/** 队列名. */
	private String name;
	
	/** 队列管理器名. */
	private String qmgrName;

	/** 打开输入计数. */
	private int inputCount;
	
	/** 打开输出计数. */
	private int outputCount;
	
	/** 最大队列深度. */
	private int maxDepth;
	
	/** 当期队列深度. */
	private int currentDepth;
	
	/** 是否允许放入消息. */
	private boolean allowPut;
	
	/** 是否运行取走消息. */
	private boolean allowGet;
	
	/**
	 * 队列创建时间
	 */
	private Date queueCreateTime;

	/**
	 * 获取 打开输入计数.
	 * 
	 * @return the 打开输入计数
	 */
	public int getInputCount() {
		return inputCount;
	}

	/**
	 * 设置 打开输入计数.
	 * 
	 * @param inputCount
	 *            the new 打开输入计数
	 */
	public void setInputCount(int inputCount) {
		this.inputCount = inputCount;
	}

	/**
	 * 获取 打开输出计数.
	 * 
	 * @return the 打开输出计数
	 */
	public int getOutputCount() {
		return outputCount;
	}

	/**
	 * 设置 打开输出计数.
	 * 
	 * @param outputCount
	 *            the new 打开输出计数
	 */
	public void setOutputCount(int outputCount) {
		this.outputCount = outputCount;
	}

	/**
	 * 获取 最大队列深度.
	 * 
	 * @return the 最大队列深度
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * 设置 最大队列深度.
	 * 
	 * @param maxDepth
	 *            the new 最大队列深度
	 */
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	/**
	 * 获取 当期队列深度.
	 * 
	 * @return the 当期队列深度
	 */
	public int getCurrentDepth() {
		return currentDepth;
	}

	/**
	 * 设置 当期队列深度.
	 * 
	 * @param currentDepth
	 *            the new 当期队列深度
	 */
	public void setCurrentDepth(int currentDepth) {
		this.currentDepth = currentDepth;
	}

	/**
	 * Checks if is 是否允许放入消息.
	 * 
	 * @return the 是否允许放入消息
	 */
	public boolean isAllowPut() {
		return allowPut;
	}

	/**
	 * 设置 是否允许放入消息.
	 * 
	 * @param allowPut
	 *            the new 是否允许放入消息
	 */
	public void setAllowPut(boolean allowPut) {
		this.allowPut = allowPut;
	}

	/**
	 * Checks if is 是否运行取走消息.
	 * 
	 * @return the 是否运行取走消息
	 */
	public boolean isAllowGet() {
		return allowGet;
	}

	/**
	 * 设置 是否运行取走消息.
	 * 
	 * @param allowGet
	 *            the new 是否运行取走消息
	 */
	public void setAllowGet(boolean allowGet) {
		this.allowGet = allowGet;
	}

	/**
	 * 获取 队列名.
	 * 
	 * @return the 队列名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 队列名.
	 * 
	 * @param name
	 *            the new 队列名
	 */
	public void setName(String name) {
		this.name = name;
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

	public Date getQueueCreateTime() {
		return queueCreateTime;
	}

	public void setQueueCreateTime(Date queueCreateTime) {
		this.queueCreateTime = queueCreateTime;
	}
}

package com.deppon.esb.management.rbackqe.domain;

public class JmsManageInfo extends JmsCommonInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4624754086926131861L;
	
	//监听队列
	private String queueName;
	//过滤信息
	private String messageSelector;
	//是否事物
	private String sessionTransacted;
	//当手动事物量
	private int sessionAcknowledgeMode;
	//并发数
	private int maxConcurrentConsumers;

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getMessageSelector() {
		return messageSelector;
	}

	public void setMessageSelector(String messageSelector) {
		this.messageSelector = messageSelector;
	}

	public String getSessionTransacted() {
		return sessionTransacted;
	}

	public void setSessionTransacted(String sessionTransacted) {
		this.sessionTransacted = sessionTransacted;
	}

	public int getSessionAcknowledgeMode() {
		return sessionAcknowledgeMode;
	}

	public void setSessionAcknowledgeMode(int sessionAcknowledgeMode) {
		this.sessionAcknowledgeMode = sessionAcknowledgeMode;
	}

	public int getMaxConcurrentConsumers() {
		return maxConcurrentConsumers;
	}

	public void setMaxConcurrentConsumers(int maxConcurrentConsumers) {
		this.maxConcurrentConsumers = maxConcurrentConsumers;
	}
	
}

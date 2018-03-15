package com.deppon.esb.management.mq.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

public class QueueManagerBean extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6289862752791040431L;

	private String[] ip;
	
	private int[] port;
	
	private String[] channel;
	
	private String[] queueNameReg;

	public String[] getIp() {
		return ip;
	}

	public int[] getPort() {
		return port;
	}

	public String[] getChannel() {
		return channel;
	}

	public String[] getQueueNameReg() {
		return queueNameReg;
	}

	public void setIp(String[] ip) {
		this.ip = ip;
	}

	public void setPort(int[] port) {
		this.port = port;
	}

	public void setChannel(String[] channel) {
		this.channel = channel;
	}

	public void setQueueNameReg(String[] queueNameReg) {
		this.queueNameReg = queueNameReg;
	}
	
}

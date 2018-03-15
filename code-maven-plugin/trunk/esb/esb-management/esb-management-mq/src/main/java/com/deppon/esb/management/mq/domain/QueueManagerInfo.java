package com.deppon.esb.management.mq.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

public class QueueManagerInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1600702883412317491L;

	private String ip;

	private int port;

	private String channel;

	private String queueNameReg;

	private String qmgr;

	public String getQmgr() {
		return qmgr;
	}

	public void setQmgr(String qmgr) {
		this.qmgr = qmgr;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getChannel() {
		return channel;
	}

	public String getQueueNameReg() {
		return queueNameReg;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(String port) {
		if (port.matches("^[1-9][0-9]*")) {
			this.port = Integer.parseInt(port);
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setQueueNameReg(String queueNameReg) {
		this.queueNameReg = queueNameReg;
	}

}

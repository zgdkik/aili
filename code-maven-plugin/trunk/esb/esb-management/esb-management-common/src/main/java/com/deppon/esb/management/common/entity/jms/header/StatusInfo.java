package com.deppon.esb.management.common.entity.jms.header;

import java.io.Serializable;

/**
 * The Class StatusInfo.
 */
public class StatusInfo implements Serializable {

	/** 常量定义 serialVersionUID. */
	private final static long serialVersionUID = 11082011L;
	
	/** The status id. */
	protected String statusId;
	
	/** The time stamp. */
	protected long timeStamp;

	/**
	 * 获取statusId属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * 设置statusId属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setStatusId(String value) {
		this.statusId = value;
	}

	/**
	 * 获取timeStamp属性的值。.
	 * 
	 * @return the time stamp
	 */
	public long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * 设置timeStamp属性的值。.
	 * 
	 * @param value
	 *            the new time stamp
	 */
	public void setTimeStamp(long value) {
		this.timeStamp = value;
	}

}

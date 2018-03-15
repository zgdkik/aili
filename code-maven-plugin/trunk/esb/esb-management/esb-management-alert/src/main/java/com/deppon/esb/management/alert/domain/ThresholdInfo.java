package com.deppon.esb.management.alert.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class ThresholdInfo.
 */
public class ThresholdInfo extends BaseEntity{
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 2342829414459679646L;
	
	/** The svc code. */
	private String svcCode;//服务编码
	
	/** The channel. */
	private String channel;//预警渠道
	
	/** The threshold. */
	private Integer threshold;//响应阀值
	
	/**
	 * 获取 svc code.
	 * 
	 * @return the svc code
	 */
	public String getSvcCode() {
		return svcCode;
	}
	
	/**
	 * 设置 svc code.
	 * 
	 * @param svcCode
	 *            the new svc code
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	
	/**
	 * 获取 channel.
	 * 
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	
	/**
	 * 设置 channel.
	 * 
	 * @param channel
	 *            the new channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	/**
	 * 获取 threshold.
	 * 
	 * @return the threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}
	
	/**
	 * 设置 threshold.
	 * 
	 * @param threshold
	 *            the new threshold
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

}

package com.deppon.esb.management.alert.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class InterfaceThresholdInfo.
 * 
 * @Description 服务预警阀值
 * @author HuangHua
 * @date 2012-03-08 14:25:39
 */
public class InterfaceThresholdInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 2234174878421828765L;

	/** 常量定义 PERSON_SEPARATOR. */
	public static final String PERSON_SEPARATOR = ",";
	
	/** 常量定义 THRESHOLD_TYPE_PERFORMANCE. */
	public static final Integer THRESHOLD_TYPE_PERFORMANCE = Integer.valueOf(0);
	
	/** 常量定义 THRESHOLD_TYPE_EXCEPTION. */
	public static final Integer THRESHOLD_TYPE_EXCEPTION = Integer.valueOf(1);
	/** 常量定义 THRESHOLD_TYPE_QUEUE. */
	public static final Integer THRESHOLD_TYPE_QUEUE = Integer.valueOf(2);
	/** 常量定义 THRESHOLD_TYPE_STATUS. */
	public static final Integer THRESHOLD_TYPE_STATUS = Integer.valueOf(3);

	/** 常量定义 CHANNEL_FETION. */
	public static final String CHANNEL_FETION = "Fetion";
	
	/** 常量定义 CHANNEL_SMS. */
	public static final String CHANNEL_SMS = "msg";
	
	/** 常量定义 CHANNEL_EMAIL. */
	public static final String CHANNEL_EMAIL = "Email";

	/** 服务编码. */
	private String svcCode;
	
	/** 阀值：性能--时间，异常--异常次数. */
	private Integer threshold;
	
	/** 发送方式id. */
	private String channelId;
	
	/** 预警人员. */
	private String personId;
	
	/** 类型：0-性能，1-异常. 3-队列.4-状态*/
	private Integer type;

	/**
	 * 获取 服务编码.
	 * 
	 * @return the 服务编码
	 */
	public String getSvcCode() {
		return svcCode;
	}

	/**
	 * 设置 服务编码.
	 * 
	 * @param svcCode
	 *            the new 服务编码
	 */
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}

	/**
	 * 获取 阀值：性能--时间，异常--异常次数.
	 * 
	 * @return the 阀值：性能--时间，异常--异常次数
	 */
	public Integer getThreshold() {
		return threshold;
	}

	/**
	 * 设置 阀值：性能--时间，异常--异常次数.
	 * 
	 * @param threshold
	 *            the new 阀值：性能--时间，异常--异常次数
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * 获取 发送方式id.
	 * 
	 * @return the 发送方式id
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * 设置 发送方式id.
	 * 
	 * @param channelId
	 *            the new 发送方式id
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * 获取 预警人员.
	 * 
	 * @return the 预警人员
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * 设置 预警人员.
	 * 
	 * @param personId
	 *            the new 预警人员
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * 获取 类型：0-性能，1-异常.
	 * 
	 * @return the 类型：0-性能，1-异常
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置 类型：0-性能，1-异常.
	 * 
	 * @param type
	 *            the new 类型：0-性能，1-异常
	 */
	public void setType(Integer type) {
		this.type = type;
	}
}

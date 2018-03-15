package com.deppon.esb.management.alert.domain.view;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 封装后端监控管理界面查询条件.
 * 
 * @author qiancheng
 */
public class InterfaceThresholdQueryBean extends BaseEntity{
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -9132542604684759376L;
	
	/** The svc name. */
	private String svcName;//服务名称
	
	/** The svc provd id. */
	private String svcProvdId;//服务提供方
	
	/** The svc code. */
	private String svcCode;
	
	/** The channel. */
	private String channel;
	
	/** The threshold. */
	private Integer threshold;
	
	/** The start. */
	private Integer start;
	
	/** The limit. */
	private Integer limit;
	
	/** The frntorbck. */
	private String frntorbck;//前端/后端
	
	/** The person id. */
	private String personId;//预警人员id
	
	/** The type. */
	private Integer type;	// 性能或者异常

	/**
	 * 获取 person id.
	 * 
	 * @return the person id
	 */
	public String getPersonId() {
		return personId;
	}
	
	/**
	 * 设置 person id.
	 * 
	 * @param personId
	 *            the new person id
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	/**
	 * 获取 frntorbck.
	 * 
	 * @return the frntorbck
	 */
	public String getFrntorbck() {
		return frntorbck;
	}
	
	/**
	 * 设置 frntorbck.
	 * 
	 * @param frntorbck
	 *            the new frntorbck
	 */
	public void setFrntorbck(String frntorbck) {
		this.frntorbck = frntorbck;
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
	 * 获取 start.
	 * 
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	
	/**
	 * 设置 start.
	 * 
	 * @param start
	 *            the new start
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	
	/**
	 * 获取 limit.
	 * 
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}
	
	/**
	 * 设置 limit.
	 * 
	 * @param limit
	 *            the new limit
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	/**
	 * 获取 svc name.
	 * 
	 * @return the svc name
	 */
	public String getSvcName() {
		return svcName;
	}
	
	/**
	 * 设置 svc name.
	 * 
	 * @param svcName
	 *            the new svc name
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	/**
	 * 获取 svc provd id.
	 * 
	 * @return the svc provd id
	 */
	public String getSvcProvdId() {
		return svcProvdId;
	}
	
	/**
	 * 设置 svc provd id.
	 * 
	 * @param svcProvdId
	 *            the new svc provd id
	 */
	public void setSvcProvdId(String svcProvdId) {
		this.svcProvdId = svcProvdId;
	}
	
	/**
	 * 获取 type.
	 * 
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * 设置 type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
}

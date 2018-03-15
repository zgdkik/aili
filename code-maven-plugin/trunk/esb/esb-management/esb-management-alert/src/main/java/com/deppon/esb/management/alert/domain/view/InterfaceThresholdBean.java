package com.deppon.esb.management.alert.domain.view;

import java.util.List;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * 封装查询结果.
 * 
 * @author qiancheng
 */
public class InterfaceThresholdBean extends BaseEntity{

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -9132542604684759376L;
	
	/** The svc name. */
	private String svcName;//服务名称
	
	/** The svc code. */
	private String svcCode;//服务编码
	
	/** The svc provd id. */
	private String svcProvdId;//服务提供方
	
	/** The threshold. */
	private Integer threshold;//阀值
	
	/** The channel. */
	private String channel;//发送方式id
	
	/** The list. */
	private List<NoticUserInfo> list ;//预警人员
	
	/** The person id. */
	private String personId;//预警人员id
	
	/** The person name. */
	private String personName;//预警人员名单
	
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
	 * 获取 person name.
	 * 
	 * @return the person name
	 */
	public String getPersonName() {
		return personName;
	}
	
	/**
	 * 设置 person name.
	 * 
	 * @param personName
	 *            the new person name
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
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
	 * 获取 list.
	 * 
	 * @return the list
	 */
	public List<NoticUserInfo> getList() {
		return list;
	}
	
	/**
	 * 设置 list.
	 * 
	 * @param list
	 *            the new list
	 */
	public void setList(List<NoticUserInfo> list) {
		this.list = list;
	}
	
}

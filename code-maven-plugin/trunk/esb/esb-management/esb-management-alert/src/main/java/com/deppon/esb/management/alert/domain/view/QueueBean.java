package com.deppon.esb.management.alert.domain.view;


import java.io.Serializable;
import java.util.List;

import com.deppon.esb.management.user.domain.NoticUserInfo;

/**
 * 封装查询返回对象.
 * 
 * @author Administrator
 */
public class QueueBean implements Serializable{
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 6703320855894701746L;
	
	/** The id. */
	private String id;
	
	/** 队列管理器. */
	private String qmgr;
	
	/** 队列. */
	private String queue;
	
	/** 队列阈值. */
	private Integer threshold;
	
	/** 告警方式. */
	private String channelId;
	
	/** 告警人员id. */
	private String personId;
	
	/** 告警人员名称. */
	private String personName;
	
	/** 告警人员信息列表. */
	private List<NoticUserInfo> personList;
	
	/** 是否集群. */
	private boolean clusters;
	
	/** 次数。 */
	private Integer comparetime;
	
	/** 浮动值。 */
	private Integer volatility;
	
	/** 最大深度。 */
	private Integer maxDepth;
	
	/** 项目版本。 */
	private String pjVersion;
	
	public boolean isClusters() {
		return clusters;
	}

	public void setClusters(boolean clusters) {
		this.clusters = clusters;
	}

	public Integer getComparetime() {
		return comparetime;
	}

	public void setComparetime(Integer comparetime) {
		this.comparetime = comparetime;
	}

	public Integer getVolatility() {
		return volatility;
	}

	public void setVolatility(Integer volatility) {
		this.volatility = volatility;
	}

	public Integer getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(Integer maxDepth) {
		this.maxDepth = maxDepth;
	}

	public String getPjVersion() {
		return pjVersion;
	}

	public void setPjVersion(String pjVersion) {
		this.pjVersion = pjVersion;
	}

	/**
	 * 获取 id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置 id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取 队列.
	 * 
	 * @return the 队列
	 */
	public String getQueue() {
		return queue;
	}
	
	/**
	 * 设置 队列.
	 * 
	 * @param queue
	 *            the new 队列
	 */
	public void setQueue(String queue) {
		this.queue = queue;
	}
	
	/**
	 * 获取 队列阈值.
	 * 
	 * @return the 队列阈值
	 */
	public Integer getThreshold() {
		return threshold;
	}
	
	/**
	 * 设置 队列阈值.
	 * 
	 * @param threshold
	 *            the new 队列阈值
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	
	/**
	 * 获取 告警方式.
	 * 
	 * @return the 告警方式
	 */
	public String getChannelId() {
		return channelId;
	}
	
	/**
	 * 设置 告警方式.
	 * 
	 * @param channelId
	 *            the new 告警方式
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	/**
	 * 获取 告警人员id.
	 * 
	 * @return the 告警人员id
	 */
	public String getPersonId() {
		return personId;
	}
	
	/**
	 * 设置 告警人员id.
	 * 
	 * @param personId
	 *            the new 告警人员id
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	/**
	 * 获取 告警人员名称.
	 * 
	 * @return the 告警人员名称
	 */
	public String getPersonName() {
		return personName;
	}
	
	/**
	 * 设置 告警人员名称.
	 * 
	 * @param personName
	 *            the new 告警人员名称
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	/**
	 * 获取 告警人员信息列表.
	 * 
	 * @return the 告警人员信息列表
	 */
	public List<NoticUserInfo> getPersonList() {
		return personList;
	}
	
	/**
	 * 设置 告警人员信息列表.
	 * 
	 * @param personList
	 *            the new 告警人员信息列表
	 */
	public void setPersonList(List<NoticUserInfo> personList) {
		this.personList = personList;
	}
	
	/**
	 * 获取 队列管理器.
	 * 
	 * @return the 队列管理器
	 */
	public String getQmgr() {
		return qmgr;
	}
	
	/**
	 * 设置 队列管理器.
	 * 
	 * @param qmgr
	 *            the new 队列管理器
	 */
	public void setQmgr(String qmgr) {
		this.qmgr = qmgr;
	}
	
}

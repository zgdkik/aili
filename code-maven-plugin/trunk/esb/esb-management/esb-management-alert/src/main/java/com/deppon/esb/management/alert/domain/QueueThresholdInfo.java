package com.deppon.esb.management.alert.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class QueueThresholdInfo.
 * 
 * @Description 队列预警阀值
 * @author HuangHua
 * @date 2012-03-08 14:27:34
 */
public class QueueThresholdInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 3463292015697570175L;
	
	/** 主键id. */
	private String fid;
	
	/** 队列管理器. */
	private String qmgr;

	/** 队列. */
	private String queue;

	/** 阀值. */
	private Integer threshold;

	/** 发送方式. */
	private String channelId;

	/** 预警人员. */
	private String personId;

	/** 服务编码. */
	private String svcCode;

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
	 * Checks if is 是否集群.
	 * 
	 * @return the 是否集群
	 */
	public boolean isClusters() {
		return clusters;
	}

	/**
	 * 设置 是否集群.
	 * 
	 * @param clusters
	 *            the new 是否集群
	 */
	public void setClusters(boolean clusters) {
		this.clusters = clusters;
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
	 * 获取 阀值.
	 * 
	 * @return the 阀值
	 */
	public Integer getThreshold() {
		return threshold;
	}

	/**
	 * 设置 阀值.
	 * 
	 * @param threshold
	 *            the new 阀值
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * 获取 发送方式.
	 * 
	 * @return the 发送方式
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * 设置 发送方式.
	 * 
	 * @param channelId
	 *            the new 发送方式
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

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
}

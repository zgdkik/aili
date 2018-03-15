package com.deppon.esb.management.alert.domain.view;

/**
 * 封装查询条件.
 * 
 * @author qiancheng
 */
public class QueueQueryBean {
	
	/** The id. */
	private String id;
	
	/** 队列阈值. */
	private Integer threshold;
	
	/** The start. */
	private Integer start;
	
	/** The limit. */
	private Integer limit;
	
	/** 预警渠道. */
	private String channel;
	
	/**
	 * 获取 预警渠道.
	 * 
	 * @return the 预警渠道
	 */
	public String getChannel() {
		return channel;
	}
	
	/**
	 * 设置 预警渠道.
	 * 
	 * @param channel
	 *            the new 预警渠道
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	/** 队列管理器. */
	private String qmgr;
	
	/** 队列. */
	private String queue;
	
	/** 是否集群. */
	private boolean clusters;
	
	/** 版本号. */
	private String pjVersion;

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
	 * 获取 是否集群.
	 * 
	 * @return the 是否集群
	 */
	public boolean getClusters() {
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

	public String getPjVersion() {
		return pjVersion;
	}

	public void setPjVersion(String pjVersion) {
		this.pjVersion = pjVersion;
	}
}

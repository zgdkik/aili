package com.deppon.esb.management.excptn.domain;

import java.util.Date;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class ExcptnStatisticsInfo.
 * 
 * @Description 异常统计信息
 * @author HuangHua
 * @date 2012-03-08 14:42:39
 */
public class ExcptnStatisticsInfo extends BaseEntity {
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 6349247883546506445L;
	
	/** 服务编码. */
	private String svcCode;
	
	/** 调用次数. */
	private Integer calledCount;
	
	/** 异常次数. */
	private Integer excptnCount;
	
	/** 开始时间. */
	private Date startTime;
	
	/** 结束时间. */
	private Date endTime;
	
	/** 是否已经发送预警通知. */
	private Boolean isSend; 

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
	 * 获取 调用次数.
	 * 
	 * @return the 调用次数
	 */
	public Integer getCalledCount() {
		return calledCount;
	}

	/**
	 * 设置 调用次数.
	 * 
	 * @param calledCount
	 *            the new 调用次数
	 */
	public void setCalledCount(Integer calledCount) {
		this.calledCount = calledCount;
	}

	/**
	 * 获取 异常次数.
	 * 
	 * @return the 异常次数
	 */
	public Integer getExcptnCount() {
		return excptnCount;
	}

	/**
	 * 设置 异常次数.
	 * 
	 * @param excptnCount
	 *            the new 异常次数
	 */
	public void setExcptnCount(Integer excptnCount) {
		this.excptnCount = excptnCount;
	}

	/**
	 * 获取 开始时间.
	 * 
	 * @return the 开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 设置 开始时间.
	 * 
	 * @param startTime
	 *            the new 开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取 结束时间.
	 * 
	 * @return the 结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置 结束时间.
	 * 
	 * @param endTime
	 *            the new 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取 是否已经发送预警通知.
	 * 
	 * @return the 是否已经发送预警通知
	 */
	public Boolean getIsSend() {
		return isSend;
	}

	/**
	 * 设置 是否已经发送预警通知.
	 * 
	 * @param isSend
	 *            the new 是否已经发送预警通知
	 */
	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}
}

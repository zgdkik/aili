package com.deppon.esb.management.pfmc.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class PfmcStatisticsInfo.
 * 
 * @Description 性能统计信息
 * @author HuangHua
 * @date 2012-03-08 14:34:06
 * @author HuangHua:add property isSend;2012-05-10 15:31:48
 */
public class PfmcStatisticsInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -6713782974367218661L;
	
	/** 服务编码. */
	private String svcCode;
	
	/** 调用次数. */
	private Integer calledCount;
	
	/** 最小响应时间. */
	private Long minRspTime;
	
	/** 最大响应时间. */
	private Long maxRspTime;
	
	/** 平均响应时间. */
	private Long avgRspTime;
	
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
	 * 获取 最小响应时间.
	 * 
	 * @return the 最小响应时间
	 */
	public Long getMinRspTime() {
		return minRspTime;
	}

	/**
	 * 设置 最小响应时间.
	 * 
	 * @param minRspTime
	 *            the new 最小响应时间
	 */
	public void setMinRspTime(Long minRspTime) {
		this.minRspTime = minRspTime;
	}

	/**
	 * 获取 最大响应时间.
	 * 
	 * @return the 最大响应时间
	 */
	public Long getMaxRspTime() {
		return maxRspTime;
	}

	/**
	 * 设置 最大响应时间.
	 * 
	 * @param maxRspTime
	 *            the new 最大响应时间
	 */
	public void setMaxRspTime(Long maxRspTime) {
		this.maxRspTime = maxRspTime;
	}

	/**
	 * 获取 平均响应时间.
	 * 
	 * @return the 平均响应时间
	 */
	public Long getAvgRspTime() {
		return avgRspTime;
	}

	/**
	 * 设置 平均响应时间.
	 * 
	 * @param avgRspTime
	 *            the new 平均响应时间
	 */
	public void setAvgRspTime(Long avgRspTime) {
		this.avgRspTime = avgRspTime;
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

	/** 
	 * 重写toString构造方法
	 * @author HuangHua
	 * @date 2012-12-28 下午3:48:50
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("svcCode", svcCode).append("calledCount", calledCount)
				.append("minRspTime", minRspTime)
				.append("maxRspTime", maxRspTime)
				.append("avgRspTime", avgRspTime)
				.append("startTime", startTime).append("endTime", endTime)
				.append("isSend", isSend).toString();
	}

}

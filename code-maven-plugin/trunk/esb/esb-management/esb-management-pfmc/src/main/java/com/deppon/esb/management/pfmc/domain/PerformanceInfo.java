package com.deppon.esb.management.pfmc.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class PerformanceInfo.
 * 
 * @Description 性能日志信息
 * @author HuangHua
 * @date 2012-03-08 11:44:09
 */
public class PerformanceInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -2827065049719674013L;
	
	/** The camel context id. */
	private String camelContextId;// camel上下文ID
	
	/** The route id. */
	private String routeId;// 路由ID
	
	/** The svc code. */
	private String svcCode;// 服务编码
	
	/** The exchange id. */
	private String exchangeId;
	
	/** The from end point. */
	private String fromEndPoint;// 起始节点
	
	/** The timecosts. */
	private Long timecosts;// 耗费时间
	
	/** The to end point. */
	private String toEndPoint;// 达到节点
	
	/** The req msg. */
	private byte[] reqMsg;// 请求报文
	
	/** The request time. */
	private Date requestTime;
	
	/** The response time. */
	private Date responseTime;
	
	/** The rsp msg. */
	private byte[] rspMsg;// 响应报文
	
	/** The statistics flg. */
	private Integer statisticsFlg;// 统计标记

	/**
	 * 获取 camel context id.
	 * 
	 * @return the camel context id
	 */
	public String getCamelContextId() {
		return camelContextId;
	}

	/**
	 * 设置 camel context id.
	 * 
	 * @param camelContextId
	 *            the new camel context id
	 */
	public void setCamelContextId(String camelContextId) {
		this.camelContextId = camelContextId;
	}

	/**
	 * 获取 route id.
	 * 
	 * @return the route id
	 */
	public String getRouteId() {
		return routeId;
	}

	/**
	 * 设置 route id.
	 * 
	 * @param routeId
	 *            the new route id
	 */
	public void setRouteId(String routeId) {
		this.routeId = routeId;
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
	 * 获取 from end point.
	 * 
	 * @return the from end point
	 */
	public String getFromEndPoint() {
		return fromEndPoint;
	}

	/**
	 * 设置 from end point.
	 * 
	 * @param fromEndPoint
	 *            the new from end point
	 */
	public void setFromEndPoint(String fromEndPoint) {
		this.fromEndPoint = fromEndPoint;
	}

	/**
	 * 获取 timecosts.
	 * 
	 * @return the timecosts
	 */
	public Long getTimecosts() {
		return timecosts;
	}

	/**
	 * 设置 timecosts.
	 * 
	 * @param timecosts
	 *            the new timecosts
	 */
	public void setTimecosts(Long timecosts) {
		this.timecosts = timecosts;
	}

	/**
	 * 获取 to end point.
	 * 
	 * @return the to end point
	 */
	public String getToEndPoint() {
		return toEndPoint;
	}

	/**
	 * 设置 to end point.
	 * 
	 * @param toEndPoint
	 *            the new to end point
	 */
	public void setToEndPoint(String toEndPoint) {
		this.toEndPoint = toEndPoint;
	}

	/**
	 * 获取 req msg.
	 * 
	 * @return the req msg
	 */
	public byte[] getReqMsg() {
		return reqMsg;
	}

	/**
	 * 设置 req msg.
	 * 
	 * @param reqMsg
	 *            the new req msg
	 */
	public void setReqMsg(byte[] reqMsg) {
		this.reqMsg = reqMsg;
	}

	/**
	 * 获取 rsp msg.
	 * 
	 * @return the rsp msg
	 */
	public byte[] getRspMsg() {
		return rspMsg;
	}

	/**
	 * 设置 rsp msg.
	 * 
	 * @param rspMsg
	 *            the new rsp msg
	 */
	public void setRspMsg(byte[] rspMsg) {
		this.rspMsg = rspMsg;
	}

	/**
	 * 获取 statistics flg.
	 * 
	 * @return the statistics flg
	 */
	public Integer getStatisticsFlg() {
		return statisticsFlg;
	}

	/**
	 * 设置 statistics flg.
	 * 
	 * @param statisticsFlg
	 *            the new statistics flg
	 */
	public void setStatisticsFlg(Integer statisticsFlg) {
		this.statisticsFlg = statisticsFlg;
	}
	
	/**
	 * 获取 exchange id.
	 * 
	 * @return the exchange id
	 */
	public String getExchangeId() {
		return exchangeId;
	}

	/**
	 * 设置 exchange id.
	 * 
	 * @param exchangeId
	 *            the new exchange id
	 */
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	/**
	 * 获取 request time.
	 * 
	 * @return the request time
	 */
	public Date getRequestTime() {
		return requestTime;
	}

	/**
	 * 设置 request time.
	 * 
	 * @param requestTime
	 *            the new request time
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * 获取 response time.
	 * 
	 * @return the response time
	 */
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * 设置 response time.
	 * 
	 * @param responseTime
	 *            the new response time
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	/** 
	 * 重写toString方法
	 * @author HuangHua
	 * @date 2012-12-28 下午2:46:30
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this,
                ToStringStyle.MULTI_LINE_STYLE)
				.append("camelContextId", camelContextId)
				.append("routeId", routeId)
				.append("exchangeId", exchangeId)
                .append("fromEndPoint", fromEndPoint)
                .append("toEndPoint", toEndPoint)
                .append("svcCode", svcCode)
                .append("requestTime", requestTime)
                .append("responseTime", responseTime)
                .append("timecosts", timecosts)
                .toString();
	}

}

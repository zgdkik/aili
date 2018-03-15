package com.deppon.esb.management.excptn.domain.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装异常查询2.0条件.
 */
public class ExceptionQueryBean2 implements Serializable{

	private static final long serialVersionUID = -3592112761130545017L;
	
	/** The id. */
	private String id;
	
	/** The start. */
	private Integer start;
	
	/** The limit. */
	private Integer limit;
	
	/** The svc name. */
	private String svcName;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/** 
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 业务关键信息 （业务唯一Id）
	 */
	private String biz;
	/** 
	 * 异常信息 
	 */
	private String exception;
	/**
	 * 服务编码	 
	 */
	private String svcCode;
	/**
	 * 相关系统
	 */
	private String sourceSystem;
	/**
	 * 协议 （消息格式）
	 */
	private String messageFormat;
	/**
	 * 业务辅助字段1
	 */
	private String businessDesc1;
	/**
	 * 业务辅助字段2
	 */
	private String businessDesc2;
	/**
	 * 业务辅助字段3
	 */
	private String businessDesc3;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSvcName() {
		return svcName;
	}
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getBiz() {
		return biz;
	}
	public void setBiz(String biz) {
		this.biz = biz;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getSvcCode() {
		return svcCode;
	}
	public void setSvcCode(String svcCode) {
		this.svcCode = svcCode;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	public String getMessageFormat() {
		return messageFormat;
	}
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}
	public String getBusinessDesc1() {
		return businessDesc1;
	}
	public void setBusinessDesc1(String businessDesc1) {
		this.businessDesc1 = businessDesc1;
	}
	public String getBusinessDesc2() {
		return businessDesc2;
	}
	public void setBusinessDesc2(String businessDesc2) {
		this.businessDesc2 = businessDesc2;
	}
	public String getBusinessDesc3() {
		return businessDesc3;
	}
	public void setBusinessDesc3(String businessDesc3) {
		this.businessDesc3 = businessDesc3;
	}
	
}

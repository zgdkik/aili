package com.deppon.esb.management.excptn.domain.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装异常查询条件.
 * 
 * @author qiancheng
 */
public class ExceptionQueryBean implements Serializable{

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -2652209680488930812L;
	
	/** The id. */
	private String id;
	
	/** The start. */
	private Integer start;
	
	/** The limit. */
	private Integer limit;
	
	/** The svc name. */
	private String svcName;
	
	/** The start time. */
	private Date startTime;
	
	/** The end time. */
	private Date endTime;
	
	/** The biz. */
	private String biz;
	
	/** The exception. */
	private String exception;
	
	/** The svc code. */
	private String svcCode;
	
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
	 * 获取 start time.
	 * 
	 * @return the start time
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * 设置 start time.
	 * 
	 * @param startTime
	 *            the new start time
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取 end time.
	 * 
	 * @return the end time
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * 设置 end time.
	 * 
	 * @param endTime
	 *            the new end time
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取 biz.
	 * 
	 * @return the biz
	 */
	public String getBiz() {
		return biz;
	}
	
	/**
	 * 设置 biz.
	 * 
	 * @param biz
	 *            the new biz
	 */
	public void setBiz(String biz) {
		this.biz = biz;
	}
	
	/**
	 * 获取 exception.
	 * 
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}
	
	/**
	 * 设置 exception.
	 * 
	 * @param exception
	 *            the new exception
	 */
	public void setException(String exception) {
		this.exception = exception;
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
	
}

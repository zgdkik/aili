package com.deppon.esb.management.excptn.domain.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装异常查询结果.
 * 
 * @author qiancheng
 */
public class ExceptionBean implements Serializable{

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -5564743954770922625L;
	
	/** The id. */
	private String id;
	
	/** 唯一消息编码. */
	private String msgId;
	
	/** 服务名称. */
	private String svcName;
	
	/** 服务编码. */
	private String svcCode;
	
	/** * 异常发生时间. */
	private Date createTime;
	
	/** 关键业务信息. */
	private String biz;//关键业务信息
	
	/** 异常编码. */
	private String exceptionCode;
	
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
	 * 获取 唯一消息编码.
	 * 
	 * @return the 唯一消息编码
	 */
	public String getMsgId() {
		return msgId;
	}
	
	/**
	 * 设置 唯一消息编码.
	 * 
	 * @param msgId
	 *            the new 唯一消息编码
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	/**
	 * 获取 服务名称.
	 * 
	 * @return the 服务名称
	 */
	public String getSvcName() {
		return svcName;
	}
	
	/**
	 * 设置 服务名称.
	 * 
	 * @param svcName
	 *            the new 服务名称
	 */
	public void setSvcName(String svcName) {
		this.svcName = svcName;
	}
	
	/**
	 * 获取 * 异常发生时间.
	 * 
	 * @return the * 异常发生时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置 * 异常发生时间.
	 * 
	 * @param createTime
	 *            the new * 异常发生时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取 关键业务信息.
	 * 
	 * @return the 关键业务信息
	 */
	public String getBiz() {
		return biz;
	}
	
	/**
	 * 设置 关键业务信息.
	 * 
	 * @param biz
	 *            the new 关键业务信息
	 */
	public void setBiz(String biz) {
		this.biz = biz;
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
	
	/**
	 * 获取 异常编码.
	 * 
	 * @return the 异常编码
	 */
	public String getExceptionCode() {
		return exceptionCode;
			  
	}
	
	/**
	 * 设置 异常编码.
	 * 
	 * @param exceptionCode
	 *            the new 异常编码
	 */
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}

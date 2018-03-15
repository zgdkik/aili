package com.deppon.esb.management.excptn.domain.view;

import java.io.Serializable;
import java.util.Date;

/**
 *  页面显示查询结果
 */
public class ExceptionBean2 implements Serializable{

	private static final long serialVersionUID = -9007810818579418356L;
	
	/** The id. */
	private String id;
	
/*	*//** 唯一消息编码. *//*
	private String msgId;*/
	
	/** * 异常发生时间. */
	private Date createTime;
	/**
	 * 接口版本
	 */
	private String version ;
	/**
	 * 关键业务信息
	 */
	private String businessId ;
	/**
	 * 业务辅助信息1
	 */
	private String businessDesc1 ;
	/**
	 * 业务辅助信息2
	 */
	private String businessDesc2;
	/**
	 * 业务辅助信息3
	 */
	private String businessDesc3;
	/**
	 * 请求唯一ID
	 */
	private String requestId ;
	/**
	 * 响应唯一ID
	 */
	private String responseId ;
	/**
	 * ESB服务编码
	 */
	private String esbServiceCode ;
	/**
	 * 后端服务编码 
	 */
	private String backServiceCode ;
	/**
	 * 消息格式  xml soap
	 */
	private String messageFormat ;
	/**
	 * 交互模式  请求/回调   单向 
	 */
	private String exchangePattern ;
	/**
	 * 异常信息
	 */
	private String message ;
	
//	/**
//	 * 异常详细信息
//	 */
//	private String detailedInfo ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	public String getEsbServiceCode() {
		return esbServiceCode;
	}
	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}
	public String getBackServiceCode() {
		return backServiceCode;
	}
	public void setBackServiceCode(String backServiceCode) {
		this.backServiceCode = backServiceCode;
	}
	public String getMessageFormat() {
		return messageFormat;
	}
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}
	public String getExchangePattern() {
		return exchangePattern;
	}
	public void setExchangePattern(String exchangePattern) {
		this.exchangePattern = exchangePattern;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
/*	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}*/
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

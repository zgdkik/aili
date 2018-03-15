package com.deppon.esb.management.common.entity.jms.header;

import java.io.Serializable;

/**
 * The Class ESBHeader.
 */
public class EsbHeader implements Serializable {

	/** 常量定义 serialVersionUID. */
	private final static long serialVersionUID = 11082011L;
	
	/** The version. */
	protected String version;
	
	/** The business id. */
	protected String businessId;
	
	/** The business desc1. */
	protected String businessDesc1;
	
	/** The business desc2. */
	protected String businessDesc2;
	
	/** The business desc3. */
	protected String businessDesc3;
	
	/** The request id. */
	protected String requestId;
	
	/** The response id. */
	protected String responseId;
	
	/** The source system. */
	protected String sourceSystem;
	
	/** The target system. */
	protected String targetSystem;
	
	/** The esb service code. */
	protected String esbServiceCode;
	
	/** The back service code. */
	protected String backServiceCode;
	
	/** The message format. */
	protected String messageFormat;
	
	/** The exchange pattern. */
	protected Integer exchangePattern;
	
	/** The sent sequence. */
	protected Integer sentSequence;
	
	/** The result code. */
	protected Integer resultCode;
	
	/** The authentication. */
	protected AuthInfo authentication;
	
	/**
	 * 获取version属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置version属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setVersion(String value) {
		this.version = value;
	}

	/**
	 * 获取businessId属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置businessId属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setBusinessId(String value) {
		this.businessId = value;
	}

	/**
	 * 获取businessDesc1属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getBusinessDesc1() {
		return businessDesc1;
	}

	/**
	 * 设置businessDesc1属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setBusinessDesc1(String value) {
		this.businessDesc1 = value;
	}

	/**
	 * 获取businessDesc2属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getBusinessDesc2() {
		return businessDesc2;
	}

	/**
	 * 设置businessDesc2属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setBusinessDesc2(String value) {
		this.businessDesc2 = value;
	}

	/**
	 * 获取businessDesc3属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getBusinessDesc3() {
		return businessDesc3;
	}

	/**
	 * 设置businessDesc3属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setBusinessDesc3(String value) {
		this.businessDesc3 = value;
	}

	/**
	 * 获取requestId属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * 设置requestId属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setRequestId(String value) {
		this.requestId = value;
	}

	/**
	 * 获取responseId属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getResponseId() {
		return responseId;
	}

	/**
	 * 设置responseId属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setResponseId(String value) {
		this.responseId = value;
	}

	/**
	 * 获取sourceSystem属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * 设置sourceSystem属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setSourceSystem(String value) {
		this.sourceSystem = value;
	}

	/**
	 * 获取targetSystem属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getTargetSystem() {
		return targetSystem;
	}

	/**
	 * 设置targetSystem属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setTargetSystem(String value) {
		this.targetSystem = value;
	}

	/**
	 * 获取esbServiceCode属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getEsbServiceCode() {
		return esbServiceCode;
	}

	/**
	 * 设置esbServiceCode属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setEsbServiceCode(String value) {
		this.esbServiceCode = value;
	}

	/**
	 * 获取backServiceCode属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getBackServiceCode() {
		return backServiceCode;
	}

	/**
	 * 设置backServiceCode属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setBackServiceCode(String value) {
		this.backServiceCode = value;
	}

	/**
	 * 获取messageFormat属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getMessageFormat() {
		return messageFormat;
	}

	/**
	 * 设置messageFormat属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setMessageFormat(String value) {
		this.messageFormat = value;
	}

	/**
	 * 获取exchangePattern属性的值。.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getExchangePattern() {
		return exchangePattern;
	}

	/**
	 * 设置exchangePattern属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 */
	public void setExchangePattern(Integer value) {
		this.exchangePattern = value;
	}

	/**
	 * 获取sentSequence属性的值。.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getSentSequence() {
		return sentSequence;
	}

	/**
	 * 设置sentSequence属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 */
	public void setSentSequence(Integer value) {
		this.sentSequence = value;
	}

	/**
	 * 获取resultCode属性的值。.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getResultCode() {
		return resultCode;
	}

	/**
	 * 设置resultCode属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 */
	public void setResultCode(Integer value) {
		this.resultCode = value;
	}

	/**
	 * 获取authentication属性的值。.
	 * 
	 * @return possible object is {@link AuthInfo }
	 */
	public AuthInfo getAuthentication() {
		return authentication;
	}

	/**
	 * 设置authentication属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link AuthInfo }
	 */
	public void setAuthentication(AuthInfo value) {
		this.authentication = value;
	}

}

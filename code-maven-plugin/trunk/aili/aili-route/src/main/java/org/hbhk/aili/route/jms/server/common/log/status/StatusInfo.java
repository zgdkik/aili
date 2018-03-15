package org.hbhk.aili.route.jms.server.common.log.status;

import java.io.Serializable;


/**
 * 审计日志.
 * 
 * @author HuangHua
 */
public class StatusInfo implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6412735680100926223L;

	/** The service code. */
	private String serviceCode;
	
	/** The request id. */
	private String requestId;
	
	/** The response id. */
	private String responseId;
	
	/** The source system. */
	private String sourceSystem;
	
	/** The target system. */
	private String targetSystem;
	
	/** The biz id. */
	private String bizId;
	
	/** The biz desc1. */
	private String bizDesc1;
	
	/** The biz desc2. */
	private String bizDesc2;
	
	/** The biz desc3. */
	private String bizDesc3;
	
	/** The body. */
	private String body;

	/**
	 * Gets the service code.
	 * 
	 * @return the service code
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * Sets the service code.
	 * 
	 * @param serviceCode
	 *            the new service code
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	/**
	 * Gets the request id.
	 * 
	 * @return the request id
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Sets the request id.
	 * 
	 * @param requestId
	 *            the new request id
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * Gets the response id.
	 * 
	 * @return the response id
	 */
	public String getResponseId() {
		return responseId;
	}

	/**
	 * Sets the response id.
	 * 
	 * @param responseId
	 *            the new response id
	 */
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	/**
	 * Gets the source system.
	 * 
	 * @return the source system
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Sets the source system.
	 * 
	 * @param sourceSystem
	 *            the new source system
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Gets the target system.
	 * 
	 * @return the target system
	 */
	public String getTargetSystem() {
		return targetSystem;
	}

	/**
	 * Sets the target system.
	 * 
	 * @param targetSystem
	 *            the new target system
	 */
	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	/**
	 * Gets the biz id.
	 * 
	 * @return the biz id
	 */
	public String getBizId() {
		return bizId;
	}

	/**
	 * Sets the biz id.
	 * 
	 * @param bizId
	 *            the new biz id
	 */
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	/**
	 * Gets the biz desc1.
	 * 
	 * @return the biz desc1
	 */
	public String getBizDesc1() {
		return bizDesc1;
	}

	/**
	 * Sets the biz desc1.
	 * 
	 * @param bizDesc1
	 *            the new biz desc1
	 */
	public void setBizDesc1(String bizDesc1) {
		this.bizDesc1 = bizDesc1;
	}

	/**
	 * Gets the biz desc2.
	 * 
	 * @return the biz desc2
	 */
	public String getBizDesc2() {
		return bizDesc2;
	}

	/**
	 * Sets the biz desc2.
	 * 
	 * @param bizDesc2
	 *            the new biz desc2
	 */
	public void setBizDesc2(String bizDesc2) {
		this.bizDesc2 = bizDesc2;
	}

	/**
	 * Gets the biz desc3.
	 * 
	 * @return the biz desc3
	 */
	public String getBizDesc3() {
		return bizDesc3;
	}

	/**
	 * Sets the biz desc3.
	 * 
	 * @param bizDesc3
	 *            the new biz desc3
	 */
	public void setBizDesc3(String bizDesc3) {
		this.bizDesc3 = bizDesc3;
	}

	/**
	 * Gets the body.
	 * 
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Sets the body.
	 * 
	 * @param body
	 *            the new body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
}

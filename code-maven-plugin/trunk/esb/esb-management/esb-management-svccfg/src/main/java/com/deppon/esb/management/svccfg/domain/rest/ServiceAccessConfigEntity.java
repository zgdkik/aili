package com.deppon.esb.management.svccfg.domain.rest;

public class ServiceAccessConfigEntity {
	
	/**
     * 服务名称
     */
    private String serviceName;
    
    /**
     * 服务方步方系统CODE
     */
	private String sysCode ;
	
	/**
     * 申请方系统CODE
     */
    private String applySysCode;
    
    /**
     * ESB前端编码
     */
    private String esbServiceCode;
    /**
     * ESB后端编码
     */
    private String backServiceCode;
    
    /**
     * 客戶端发送请求队列
     */
    private String clientSendQueue;
    /**
     * 客戶端接收响应对列
     */
    private String clientReceiveQueue;
    /**
     * 服务端发送请求队列
     */
    private String serviceSendQueue;
    /**
     * 服务端接收响应队列
     */
    private String serviceReceiveQueue;
    
    /**
     * 交互协议
     */
    private Integer serviceAgreement;
    
    /**
     * 交互协议Value
     */
    private String serviceAgreementValue;
    
    /**
     * 服务模式
     */
    private Integer servicePattern;
    
    /**
     * 消息格式
     */
    private String messageFormatValue;
    
    /**
     * 是否分发
     */
    private Integer distribute;
    
    /**
     * 服务地址
     */
    private String serviceUrl;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getApplySysCode() {
		return applySysCode;
	}

	public void setApplySysCode(String applySysCode) {
		this.applySysCode = applySysCode;
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

	public String getClientSendQueue() {
		return clientSendQueue;
	}

	public void setClientSendQueue(String clientSendQueue) {
		this.clientSendQueue = clientSendQueue;
	}

	public String getClientReceiveQueue() {
		return clientReceiveQueue;
	}

	public void setClientReceiveQueue(String clientReceiveQueue) {
		this.clientReceiveQueue = clientReceiveQueue;
	}

	public String getServiceSendQueue() {
		return serviceSendQueue;
	}

	public void setServiceSendQueue(String serviceSendQueue) {
		this.serviceSendQueue = serviceSendQueue;
	}

	public String getServiceReceiveQueue() {
		return serviceReceiveQueue;
	}

	public void setServiceReceiveQueue(String serviceReceiveQueue) {
		this.serviceReceiveQueue = serviceReceiveQueue;
	}

	public Integer getServiceAgreement() {
		return serviceAgreement;
	}

	public void setServiceAgreement(Integer serviceAgreement) {
		this.serviceAgreement = serviceAgreement;
	}

	public String getServiceAgreementValue() {
		return serviceAgreementValue;
	}

	public void setServiceAgreementValue(String serviceAgreementValue) {
		this.serviceAgreementValue = serviceAgreementValue;
	}

	public Integer getServicePattern() {
		return servicePattern;
	}

	public void setServicePattern(Integer servicePattern) {
		this.servicePattern = servicePattern;
	}

	public String getMessageFormatValue() {
		return messageFormatValue;
	}

	public void setMessageFormatValue(String messageFormatValue) {
		this.messageFormatValue = messageFormatValue;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public Integer getDistribute() {
		return distribute;
	}

	public void setDistribute(Integer distribute) {
		this.distribute = distribute;
	}
	
}

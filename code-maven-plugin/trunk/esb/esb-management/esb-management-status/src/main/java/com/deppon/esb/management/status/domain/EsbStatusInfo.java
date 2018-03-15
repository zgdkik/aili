/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.domain
 * FILE    NAME: EsbStatus.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package com.deppon.esb.management.status.domain;

import com.deppon.esb.management.common.entity.BaseEntity;
import com.deppon.esb.management.common.entity.jms.header.StatusInfo;

/**
 * ESB状态实体.
 * 
 * @author HuangHua
 * @date 2013-1-9 下午8:39:10
 */
public class EsbStatusInfo extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -3399016362442920181L;

	/**
	 * ESB服务编码
	 */
	private String esbServiceCode;

	/**
	 * 后端服务编码
	 */
	private String backServiceCode;

	/**
	 * 业务ID
	 */
	private String businessId;

	/**
	 * 请求唯一编码
	 */
	private String requestId;

	/**
	 * 响应唯一编码
	 */
	private String responseId;

	/**
	 * 源系统
	 */
	private String sourceSystem;

	/**
	 * 目标系统
	 */
	private String targetSystem;

	/** 状态信息. */
	private StatusInfo statusInfo;

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

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
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

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getTargetSystem() {
		return targetSystem;
	}

	public void setTargetSystem(String targetSystem) {
		this.targetSystem = targetSystem;
	}

	public StatusInfo getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(StatusInfo statusInfo) {
		this.statusInfo = statusInfo;
	}

}

package com.deppon.esb.management.svccfg.domain.view;

import com.deppon.esb.management.common.entity.BaseEntity;

public class EsbRuntimeConfigAddrBean extends BaseEntity{

	private static final long serialVersionUID = 4833509210164306468L;
	private String serviceName;
	private String serviceCode;
	private String serviceAddr;
	private String status;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceAddr() {
		return serviceAddr;
	}

	public void setServiceAddr(String serviceAddr) {
		this.serviceAddr = serviceAddr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}



}

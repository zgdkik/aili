package com.deppon.esb.management.route.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

public class RouteInfo extends BaseEntity {

	private static final long serialVersionUID = -978663368595989021L;

	/**
	 * ESB服务编码
	 */
	private String esbServiceCode;

	/**
	 * 目标服务编码
	 */
	private String targetServiceCode;

	public String getEsbServiceCode() {
		return esbServiceCode;
	}

	public void setEsbServiceCode(String esbServiceCode) {
		this.esbServiceCode = esbServiceCode;
	}

	public String getTargetServiceCode() {
		return targetServiceCode;
	}

	public void setTargetServiceCode(String targetServiceCode) {
		this.targetServiceCode = targetServiceCode;
	}
}

/*
 * PROJECT NAME: esb-management-status
 * PACKAGE NAME: com.deppon.esb.management.status.domain
 * FILE    NAME: EsbRouteInfo.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */ 
package com.deppon.esb.management.status.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 路由信息(时间紧迫,临时放在这个模块下)
 * @author HuangHua
 * @date 2013-5-15 上午10:08:12
 */
public class EsbRouteInfo extends BaseEntity{
	
	private static final long serialVersionUID = -6769787171522722718L;
	/**
	 * 服务编码
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

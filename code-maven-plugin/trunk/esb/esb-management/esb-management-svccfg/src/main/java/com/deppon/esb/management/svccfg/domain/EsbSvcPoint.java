package com.deppon.esb.management.svccfg.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

public class EsbSvcPoint extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8346007535694223618L;

	private String id;

	private String code;
	
	private String appRequestAddr;
	
	private String appResponseAddr;
	
	private String esbRequestAddr;

	private String esbResponseAddr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEsbRequestAddr() {
		return esbRequestAddr;
	}

	public void setEsbRequestAddr(String esbRequestAddr) {
		this.esbRequestAddr = esbRequestAddr;
	}

	public String getEsbResponseAddr() {
		return esbResponseAddr;
	}

	public void setEsbResponseAddr(String esbResponseAddr) {
		this.esbResponseAddr = esbResponseAddr;
	}

	public String getAppRequestAddr() {
		return appRequestAddr;
	}

	public String getAppResponseAddr() {
		return appResponseAddr;
	}

	public void setAppRequestAddr(String appRequestAddr) {
		this.appRequestAddr = appRequestAddr;
	}

	public void setAppResponseAddr(String appResponseAddr) {
		this.appResponseAddr = appResponseAddr;
	}

}

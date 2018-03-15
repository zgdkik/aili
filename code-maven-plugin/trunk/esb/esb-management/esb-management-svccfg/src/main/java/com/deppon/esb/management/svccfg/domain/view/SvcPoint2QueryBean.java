package com.deppon.esb.management.svccfg.domain.view;

import com.deppon.esb.management.common.entity.FormEntity;

/**
 * 封装查询条件
 * 
 */
public class SvcPoint2QueryBean extends FormEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -591541223516578458L;
	
	/** 服务名. */
	private String name;
	
	/** 服务编码. */
	private String code;
	
	/** 系统编码 */
	private String sysid;
	
	/** 服务协议. */
	private String agrmt;
	
	/** 是属于前端还是后端. */
	private String frontOrBack;
	/**
	 * 
	 */
	private String esbRequestAddr;
	/**
	 * 
	 */
	private String esbResponseAddr;
	/**
	 * 交互模式
	 */
	private Integer expattern;
	
	private String appRequestAddr;
	
	private String appResponseAddr;

	public String getAppRequestAddr() {
		return appRequestAddr;
	}
	public void setAppRequestAddr(String appRequestAddr) {
		this.appRequestAddr = appRequestAddr;
	}
	public String getAppResponseAddr() {
		return appResponseAddr;
	}
	public void setAppResponseAddr(String appResponseAddr) {
		this.appResponseAddr = appResponseAddr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getAgrmt() {
		return agrmt;
	}
	public void setAgrmt(String agrmt) {
		this.agrmt = agrmt;
	}


	public String getFrontOrBack() {
		return frontOrBack;
	}
	public void setFrontOrBack(String frontOrBack) {
		this.frontOrBack = frontOrBack;
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
	public Integer getExpattern() {
		return expattern;
	}
	public void setExpattern(Integer expattern) {
		this.expattern = expattern;
	}
}

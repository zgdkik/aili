package com.deppon.esb.management.svccfg.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * 服务信息.
 * 
 * @author HuangHua
 * @date 2012-12-28 下午2:50:53
 */
public class SvcPoint2Info extends BaseEntity {

	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = -591541223516578458L;
	
	/** 服务名. */
	private String name;
	
	/** 服务编码. */
	private String code;
	
	/** 服务提供者ID. */
	private String sysid;
	
	/** 服务协议. */
	private String agrmt;
	
	/** 是属于前端还是后端. */
	private String frontOrBack;
	
	private String messageFormat;
	
	/**  */
	private String appRequestAddr;
	/**
	 * 
	 */
	private String appResponseAddr;
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
	/**
	 *
	 */
	private Integer timeout;
	/**
	 *请求转换类 
	 */
	private String reqConvertorClass;
	/**
	 * 响应转换类
	 */
	private String resConvertorClass;
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
	public String getMessageFormat() {
		return messageFormat;
	}
	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
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
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public String getReqConvertorClass() {
		return reqConvertorClass;
	}
	public void setReqConvertorClass(String reqConvertorClass) {
		this.reqConvertorClass = reqConvertorClass;
	}
	public String getResConvertorClass() {
		return resConvertorClass;
	}
	public void setResConvertorClass(String resConvertorClass) {
		this.resConvertorClass = resConvertorClass;
	}
}

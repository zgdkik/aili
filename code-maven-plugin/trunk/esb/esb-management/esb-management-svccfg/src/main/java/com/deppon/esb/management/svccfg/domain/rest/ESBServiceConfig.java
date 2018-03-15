package com.deppon.esb.management.svccfg.domain.rest;

import java.io.Serializable;

/**
 * 操蛋的代码，操蛋的类.
 * 
 * @author k
 */
public class ESBServiceConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** fid */
	private String fid;
	
	/** 服务名称 */
	private String name;
	
	/** 服务Code */
	private String code;
	
	/** 服务系统 */
	private String sysId;
	
	/** 交互方式 */
	private String agrmt;
	
	/** 前端Or后端 */
	private String frntOrback;
	
	/** 客户端请求地址 */
	private String appRequestAddr;
	
	/** 客户端响应地址 */
	private String appResponseAddr;
	
	/** esb请求地址 */
	private String esbRequestAddr;
	
	/** esb响应地址 */
	private String esbResponseAddr;
	
	/** 交互方式 */
	private int expattern;
	
	/** 消息格式 */
	private String messageFormat;
	
	/** 超时时间 */
	private int timeOut;
	
	/** 扩展相关,已与FOSS解耦,不填 */
	private String reqConvertorClass;
	
	/** 扩展相关,已与FOSS解耦,不填 */
	private String resConvertorClass;
	
	private String wsPortName;
	
	private String wsServiceName;
	
	private String wsTargetNameSpace;
	
	/** 是否需要记录日志 */
	private int needAudit;
	
	/** 是否需要记录异常 */
	private int needexpn;
	
	/** 是否需要记录状态 */
	private int needStatus;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
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

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getAgrmt() {
		return agrmt;
	}

	public void setAgrmt(String agrmt) {
		this.agrmt = agrmt;
	}

	public String getFrntOrback() {
		return frntOrback;
	}

	public void setFrntOrback(String frntOrback) {
		this.frntOrback = frntOrback;
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

	public int getExpattern() {
		return expattern;
	}

	public void setExpattern(int expattern) {
		this.expattern = expattern;
	}

	public String getMessageFormat() {
		return messageFormat;
	}

	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
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

	public String getWsPortName() {
		return wsPortName;
	}

	public void setWsPortName(String wsPortName) {
		this.wsPortName = wsPortName;
	}

	public String getWsServiceName() {
		return wsServiceName;
	}

	public void setWsServiceName(String wsServiceName) {
		this.wsServiceName = wsServiceName;
	}

	public String getWsTargetNameSpace() {
		return wsTargetNameSpace;
	}

	public void setWsTargetNameSpace(String wsTargetNameSpace) {
		this.wsTargetNameSpace = wsTargetNameSpace;
	}

	public int getNeedAudit() {
		return needAudit;
	}

	public void setNeedAudit(int needAudit) {
		this.needAudit = needAudit;
	}

	public int getNeedexpn() {
		return needexpn;
	}

	public void setNeedexpn(int needexpn) {
		this.needexpn = needexpn;
	}

	public int getNeedStatus() {
		return needStatus;
	}

	public void setNeedStatus(int needStatus) {
		this.needStatus = needStatus;
	}
	
}

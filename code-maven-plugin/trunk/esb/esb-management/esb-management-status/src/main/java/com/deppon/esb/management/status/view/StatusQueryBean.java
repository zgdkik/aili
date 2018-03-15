package com.deppon.esb.management.status.view;

import java.util.Date;

import com.deppon.esb.management.common.entity.FormEntity;

public class StatusQueryBean extends FormEntity {
	private static final long serialVersionUID = 3332790711986927542L;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 截止时间
	 */
	private Date endTime;
	/**
	 * ESB服务编码
	 */
	private String esbServiceCode;

	/**
	 * 后端服务编码
	 */
	private String targetServiceCode;

	/**
	 * 业务ID
	 */
	private String bizid;

	/**
	 * 请求唯一编码
	 */
	private String reqid;

	/**
	 * 响应唯一编码
	 */
	private String resid;

	/**
	 * 源系统
	 */
	private String sourceSys;

	/**
	 * 目标系统
	 */
	private String targetSys;
	
	private Long  longValueOfStartTime;
	private Long longValueOfEndTime;


	public Long getLongValueOfStartTime() {
		return longValueOfStartTime;
	}

	public void setLongValueOfStartTime(Long longValueOfStartTime) {
		this.longValueOfStartTime = longValueOfStartTime;
	}

	public Long getLongValueOfEndTime() {
		return longValueOfEndTime;
	}

	public void setLongValueOfEndTime(Long longValueOfEndTime) {
		this.longValueOfEndTime = longValueOfEndTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

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


	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}


	public String getReqid() {
		return reqid;
	}

	public void setReqid(String reqid) {
		this.reqid = reqid;
	}

	public String getResid() {
		return resid;
	}

	public void setResid(String resid) {
		this.resid = resid;
	}

	public String getSourceSys() {
		return sourceSys;
	}

	public void setSourceSys(String sourceSys) {
		this.sourceSys = sourceSys;
	}

	public String getTargetSys() {
		return targetSys;
	}

	public void setTargetSys(String targetSys) {
		this.targetSys = targetSys;
	}

}

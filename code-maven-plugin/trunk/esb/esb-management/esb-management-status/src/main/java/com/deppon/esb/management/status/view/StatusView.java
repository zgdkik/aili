package com.deppon.esb.management.status.view;

import java.io.Serializable;
import java.util.Date;

public class StatusView implements Serializable {
	private static final long serialVersionUID = 4917904339759203255L;
	private String fid;
	private String esbServiceCode;
	private String targetServiceCode;
	private String bizid;
	private String reqid;
	private String resid;
	private String sourceSys;
	private String targetsys;
	private Date st102;
	private Date st105;
	private Date st108;
	private Date st202;
	private Date st205;
	private Date st208;
	private Date st302;
	private Date st305;
	private Date st308;
	private Date st402;
	private Date st405;
	private Date st408;
	private Date st502;
	private Date st505;
	private Date st508;
	private Date st999;
	private Date createTime ; 
	private long esbReqTimecost;
	private long backReqTimecost;
	private long esbResTimecost;
	private long backResTimecost;
	
	public Date getSt302() {
		return st302;
	}
	public void setSt302(Date st302) {
		this.st302 = st302;
	}
	public Date getSt305() {
		return st305;
	}
	public void setSt305(Date st305) {
		this.st305 = st305;
	}
	public Date getSt308() {
		return st308;
	}
	public void setSt308(Date st308) {
		this.st308 = st308;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
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
	public String getTargetsys() {
		return targetsys;
	}
	public void setTargetsys(String targetsys) {
		this.targetsys = targetsys;
	}
	public Date getSt102() {
		return st102;
	}
	public void setSt102(Date st102) {
		this.st102 = st102;
	}
	public Date getSt105() {
		return st105;
	}
	public void setSt105(Date st105) {
		this.st105 = st105;
	}
	public Date getSt108() {
		return st108;
	}
	public void setSt108(Date st108) {
		this.st108 = st108;
	}
	public Date getSt202() {
		return st202;
	}
	public void setSt202(Date st202) {
		this.st202 = st202;
	}
	public Date getSt205() {
		return st205;
	}
	public void setSt205(Date st205) {
		this.st205 = st205;
	}
	public Date getSt208() {
		return st208;
	}
	public void setSt208(Date st208) {
		this.st208 = st208;
	}
	public Date getSt402() {
		return st402;
	}
	public void setSt402(Date st402) {
		this.st402 = st402;
	}
	public Date getSt405() {
		return st405;
	}
	public void setSt405(Date st405) {
		this.st405 = st405;
	}
	public Date getSt408() {
		return st408;
	}
	public void setSt408(Date st408) {
		this.st408 = st408;
	}
	public Date getSt502() {
		return st502;
	}
	public void setSt502(Date st502) {
		this.st502 = st502;
	}
	public Date getSt505() {
		return st505;
	}
	public void setSt505(Date st505) {
		this.st505 = st505;
	}
	public Date getSt508() {
		return st508;
	}
	public void setSt508(Date st508) {
		this.st508 = st508;
	}
	public Date getSt999() {
		return st999;
	}
	public void setSt999(Date st999) {
		this.st999 = st999;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public long getEsbReqTimecost() {
		return esbReqTimecost;
	}
	public void setEsbReqTimecost(long esbReqTimecost) {
		this.esbReqTimecost = esbReqTimecost;
	}
	public long getBackReqTimecost() {
		return backReqTimecost;
	}
	public void setBackReqTimecost(long backReqTimecost) {
		this.backReqTimecost = backReqTimecost;
	}
	public long getEsbResTimecost() {
		return esbResTimecost;
	}
	public void setEsbResTimecost(long esbResTimecost) {
		this.esbResTimecost = esbResTimecost;
	}
	public long getBackResTimecost() {
		return backResTimecost;
	}
	public void setBackResTimecost(long backResTimecost) {
		this.backResTimecost = backResTimecost;
	}

}

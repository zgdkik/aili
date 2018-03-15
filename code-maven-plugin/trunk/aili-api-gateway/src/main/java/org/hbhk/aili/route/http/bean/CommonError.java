package org.hbhk.aili.route.http.bean;

import java.util.Date;

public class CommonError {
	
	private String exceptionType;
	private String exceptionCode;
	private Date createdTime;
	private String message;
	private String detailedInfo;
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetailedInfo() {
		return detailedInfo;
	}
	public void setDetailedInfo(String detailedInfo) {
		this.detailedInfo = detailedInfo;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}

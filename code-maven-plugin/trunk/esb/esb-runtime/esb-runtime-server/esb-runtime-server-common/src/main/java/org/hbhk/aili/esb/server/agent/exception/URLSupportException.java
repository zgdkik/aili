package org.hbhk.aili.esb.server.agent.exception;

import java.util.Date;

import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

public class URLSupportException extends ESBRunTimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public Throwable throwable = new RuntimeException(message);
	
	private Date createTime;
	
	private String detailedInfo;
	
	public String getDetailedInfo() {
		return detailedInfo;
	}

	public void setDetailedInfo(String detailedInfo) {
		this.detailedInfo = detailedInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMessage() {
		return message;
	}
	
	public void setCode(String code){
		this.code = code;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public URLSupportException() {
		super();
	}

	public URLSupportException(String message) {
		super(message);
		this.message = message;
	}

	public URLSupportException(String message, Throwable throwable) {
		super(message,throwable);
		this.message = message;
		this.throwable = throwable;
	}
	
}

package org.hbhk.module.framework.shared.util;

import java.io.Serializable;

public class MessageInfo  implements  Serializable {
	
	private static final long serialVersionUID = 4804552479727037598L;
	
	private  boolean  success = true;
	private  boolean  exception;
	private  String msg;
	private  String exceptionMsg;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public boolean isException() {
		return exception;
	}
	public void setException(boolean exception) {
		this.exception = exception;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	
	

}

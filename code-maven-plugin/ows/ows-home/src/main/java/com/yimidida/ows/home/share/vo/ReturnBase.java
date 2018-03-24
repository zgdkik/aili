package com.yimidida.ows.home.share.vo;


public class ReturnBase {
	private String error;
	private boolean success;
	private String msg;
	private String stackTrace;
	private String code;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	

}

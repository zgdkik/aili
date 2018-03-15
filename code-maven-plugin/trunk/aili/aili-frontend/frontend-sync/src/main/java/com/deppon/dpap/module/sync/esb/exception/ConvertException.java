package com.deppon.dpap.module.sync.esb.exception;

public class ConvertException extends Exception {

	/**
	 * TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L; 
	
	private String message;
	
	public ConvertException(){
		super();
	}

	public ConvertException(String message){
		super(message);
		this.message = message;
	}
	
	public ConvertException(String message, Throwable cause){
		super(message, cause);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

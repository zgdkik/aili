/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2012-11-30
 *******************************************************************************/


package org.hbhk.aili.esb.server.foss.dip;

/**
 * TODO 上报无标签多货差错返回javabean.
 * 
 * @author yourname (mailto:yourname@primeton.com)
 */
/*
 * Modify history
 * $Log$
 */
public class ReportNolabelResultBean {
	//是否成功 false代码正常 true代表发生异常
	/** The is exception. */
	private boolean isException;
	//异常编码
	/** The code. */
	private String code;
	//异常信息
	/** The message. */
	private String message;
	//差错编号
	/** The errors no. */
	private String errorsNo;
	
	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 * 
	 * @param code
	 *            the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the errors no.
	 * 
	 * @return the errors no
	 */
	public String getErrorsNo() {
		return errorsNo;
	}
	
	/**
	 * Sets the errors no.
	 * 
	 * @param errorsNo
	 *            the new errors no
	 */
	public void setErrorsNo(String errorsNo) {
		this.errorsNo = errorsNo;
	}
	
	/**
	 * Checks if is exception.
	 * 
	 * @return true, if is exception
	 */
	public boolean isException() {
		return isException;
	}
	
	/**
	 * Sets the exception.
	 * 
	 * @param isException
	 *            the new exception
	 */
	public void setException(boolean isException) {
		this.isException = isException;
	}
	
	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 * 
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Instantiates a new report nolabel result bean.
	 */
	public ReportNolabelResultBean(){
		
		
	}
	
	/**
	 * Instantiates a new report nolabel result bean.
	 * 
	 * @param isException
	 *            the is exception
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 * @param errorsNo
	 *            the errors no
	 */
	public ReportNolabelResultBean(boolean isException, String code, String message, String errorsNo) {
		super();
		this.isException = isException;
		this.code = code;
		this.message = message;
		this.errorsNo = errorsNo;
	}
}

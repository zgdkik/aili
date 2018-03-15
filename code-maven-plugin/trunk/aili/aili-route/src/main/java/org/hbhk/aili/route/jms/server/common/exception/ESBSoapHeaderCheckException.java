package org.hbhk.aili.route.jms.server.common.exception;

import org.hbhk.aili.route.jms.common.exception.ESBRunTimeException;


/**
 * The Class ESBSoapHeaderCheckException.
 */
public class ESBSoapHeaderCheckException extends ESBRunTimeException{	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2434357488892896835L;
	
	/** The error code. */
	private String errorCode;
	
	/**
	 * Instantiates a new eSB soap header check exception.
	 */
	public ESBSoapHeaderCheckException(){}
	
	/**
	 * Instantiates a new eSB soap header check exception.
	 * 
	 * @param msg
	 *            the msg
	 */
	public ESBSoapHeaderCheckException(String msg){
		super(msg);
	}
	
	/**
	 * Instantiates a new eSB soap header check exception.
	 * 
	 * @param msg
	 *            the msg
	 * @param ex
	 *            the ex
	 */
	public ESBSoapHeaderCheckException(String msg,Throwable ex){
		super(msg,ex);
	}

	/**
	 * Gets the error code.
	 * 
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 * 
	 * @param errorCode
	 *            the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}

package com.deppon.dpap.module.sync.esb.exception;
public class ESBIllegalArgumentException extends IllegalArgumentException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6217678969689527929L;

	/**
	 * Instantiates a new eSB illegal argument exception.
	 */
	public ESBIllegalArgumentException() {
		super();
	}

	/**
	 * Instantiates a new eSB illegal argument exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ESBIllegalArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new eSB illegal argument exception.
	 * 
	 * @param s
	 *            the s
	 */
	public ESBIllegalArgumentException(String s) {
		super(s);
	}

	/**
	 * Instantiates a new eSB illegal argument exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ESBIllegalArgumentException(Throwable cause) {
		super(cause);
	}

	
	
}
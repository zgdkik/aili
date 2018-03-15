package com.deppon.esb.management.common.exception;

import com.deppon.esb.management.common.constant.ESBServiceConstant;


/**
 * ESB参数异常
 * @author HuangHua
 * @date 2012-12-27 上午9:54:35
 */
public class ESBStatusIllegalPatternException extends ESBRunTimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6251205304926889694L;
	
	/** The code. */
	protected String code = "ESBStatusIllegalPatternException";
	
	/** The type. */
	protected String type = ESBServiceConstant.SYSTEM_EXCEPTION;

	/**
	 * Instantiates a new eSB run time exception.
	 * 
	 * @param string
	 *            the string
	 */
	public ESBStatusIllegalPatternException(String string) {
		super(string);
	}

	/**
	 * Instantiates a new eSB run time exception.
	 */
	public ESBStatusIllegalPatternException() {
		super();
	}

	/**
	 * Instantiates a new eSB run time exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ESBStatusIllegalPatternException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new eSB run time exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ESBStatusIllegalPatternException(Throwable cause) {
		super(cause);
	}

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

}

package org.hbhk.aili.esb.server.common.exception;

import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.common.exception.ESBRunTimeException;

/**
 * 转换异常.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:50:38
 */
public class ESBConvertException extends ESBRunTimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5935863662682810137L;
	
	/** The error code. */
	private static String errorCode = "ESBConvertException";
	
	/** The code. */
	private static String code = errorCode;
	
	/** The type. */
	protected static String type = ESBServiceConstant.SYSTEM_EXCEPTION;

	/**
	 * Instantiates a new eSB convert exception.
	 */
	public ESBConvertException() {
		super();
	}

	/**
	 * Instantiates a new eSB convert exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public ESBConvertException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new eSB convert exception.
	 * 
	 * @param message
	 *            the message
	 */
	public ESBConvertException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new eSB convert exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ESBConvertException(Throwable cause) {
		super(cause);
	}

	/**
	 * Gets the exception code.
	 * 
	 * @return the exception code
	 */
	public String getExceptionCode() {
		return code;
	}

	/**
	 * Gets the exception type.
	 * 
	 * @return the exception type
	 */
	public String getExceptionType() {
		return type;
	}

}

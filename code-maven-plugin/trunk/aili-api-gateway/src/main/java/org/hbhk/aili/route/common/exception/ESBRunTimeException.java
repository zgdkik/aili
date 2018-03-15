package org.hbhk.aili.route.common.exception;


/**
 * The Class ESBRunTimeException.
 */
public class ESBRunTimeException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6251205304926889694L;
	
	/** The code. */
	protected String code = "ESBRunTimeException";
	
	/** The type. */
	protected String type = "system_exception";

	/**
	 * Instantiates a new eSB run time exception.
	 * 
	 * @param string
	 *            the string
	 */
	public ESBRunTimeException(String string) {
		super(string);
	}

	/**
	 * Instantiates a new eSB run time exception.
	 */
	public ESBRunTimeException() {
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
	public ESBRunTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new eSB run time exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ESBRunTimeException(Throwable cause) {
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
	public ESBRunTimeException(String msg,String type,String code) {
		super(msg);
		this.type=type;
		this.code=code;
	}

}

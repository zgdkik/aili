package org.hbhk.aili.client.core.commons.conversion;

/**
 * 
 *数据转换异常信息，当抛出此异常的时候就表示在转换的过程中发生不能转换的异常。
 */
@SuppressWarnings("serial")
public class ConversionException extends Exception {
	/**
	 * 
	 * <p>Title: ConversionException</p>
	 * <p>Description: 转换异常类构造方法</p>
	 */
	public ConversionException() {
		super();
	}

	/**
	 * 
	 * <p>Title: ConversionException</p>
	 * <p>Description: 转换异常类构造方法</p>
	 * @param message 异常信息
	 * @param cause 原因
	 */
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * <p>Title: ConversionException</p>
	 * <p>Description: 转换异常类构造方法</p>
	 * @param message 导常信息
	 */
	public ConversionException(String message) {
		super(message);
	}

	/**
	 * 
	 * <p>Title: ConversionException</p>
	 * <p>Description: 转换异常类构造方法</p>
	 * @param cause 原因
	 */
	public ConversionException(Throwable cause) {
		super(cause);
	}

}

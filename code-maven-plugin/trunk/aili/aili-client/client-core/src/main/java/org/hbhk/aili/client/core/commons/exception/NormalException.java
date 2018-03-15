package org.hbhk.aili.client.core.commons.exception;
/**
 * 
 *表示常规的异常
 */
@SuppressWarnings("serial")
public class NormalException extends RethrownException {
	/**
	 * 
	 * <p>Title: NormalException</p>
	 * <p>Description: 一般异常类构造函数</p>
	 */
	public NormalException() {
		super();
	}

	/**
	 * 
	 * <p>Title: NormalException</p>
	 * <p>Description: 一般异常类构造函数</p>
	 * @param message 异常信息
	 * @param cause 原因
	 */
	public NormalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * <p>Title: NormalException</p>
	 * <p>Description: 一般异常类构造函数</p>
	 * @param message 异常信息
	 */
	public NormalException(String message) {
		super(message);
	}

	/**
	 * 
	 * <p>Title: NormalException</p>
	 * <p>Description: 一般异常类构造函数</p>
	 * @param cause 原因
	 */
	public NormalException(Throwable cause) {
		super(cause);
	}
}
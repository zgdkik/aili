package org.hbhk.aili.client.core.commons.exception;

/**
 * 
 *表示轻微的异常，甚至可以忽略的异常
 */
@SuppressWarnings("serial")
public class TrivialException extends RethrownException {
	/**
	 * 
	 * <p>Title: TrivialException</p>
	 * <p>Description: 异常类构造方法</p>
	 */
	public TrivialException() {
		super();
	}

	/**
	 * 
	 * <p>Title: TrivialException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param message 异常信息
	 * @param cause 原因
	 */
	public TrivialException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 异常类构造方法
	 * <p>Title: TrivialException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param message 异常信息
	 */
	public TrivialException(String message) {
		super(message);
	}

	/**
	 * 
	 * <p>Title: TrivialException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param cause 原因
	 */
	public TrivialException(Throwable cause) {
		super(cause);
	}

}

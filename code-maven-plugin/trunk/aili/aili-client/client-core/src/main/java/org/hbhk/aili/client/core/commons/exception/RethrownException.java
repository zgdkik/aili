package org.hbhk.aili.client.core.commons.exception;
/**
 * 
 *这个类型的异常表示重新抛出的异常，此异常的定义还为了包转那些未检查异常。
 *方便在代码中进行必须捕捉的步骤。
 */
@SuppressWarnings("serial")
public class RethrownException extends RuntimeException {
	/**
	 * 
	 * <p>Title: RethrownException</p>
	 * <p>Description: 异常类构造方法</p>
	 */
	public RethrownException() {
		super();
	}

	/**
	 * 
	 * <p>Title: RethrownException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param message 异常信息
	 * @param cause 原因
	 */
	public RethrownException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * <p>Title: RethrownException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param message 异常信息
	 */
	public RethrownException(String message) {
		super(message);
	}

	/**
	 * 
	 * <p>Title: RethrownException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param cause 原因
	 */
	public RethrownException(Throwable cause) {
		super(cause);
	}
}
package org.hbhk.aili.client.core.commons.exception;
/**
 * 
 *表示严重的异常，可能需要重新启动应用
 */
@SuppressWarnings("serial")
public class SevereException extends RethrownException {
	/**
	 * 
	 * <p>Title: SevereException</p>
	 * <p>Description: 异常类构造方法</p>
	 */
	public SevereException() {
		super();
	}

	/**
	 * 
	 * <p>Title: SevereException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param message 异常信息
	 * @param cause 原因
	 */
	public SevereException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * <p>Title: SevereException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param message 异常信息
	 */
	public SevereException(String message) {
		super(message);
	}

	/**
	 * 
	 * <p>Title: SevereException</p>
	 * <p>Description: 异常类构造方法</p>
	 * @param cause 原因
	 */
	public SevereException(Throwable cause) {
		super(cause);
	}
}
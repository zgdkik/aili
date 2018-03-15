package org.hbhk.aili.client.core.widget.print.exception;

/**
 * Description: 打印异常
 */
@SuppressWarnings("serial")
public class PrintException extends RuntimeException {
	/**
	 * 
	 * <p>Title: PrintException</p>
	 * <p>Description: 构造函数</p>
	 */
	public PrintException() {
		super();
	}

	/**
	 * 
	 * <p>Title: PrintException</p>
	 * <p>Description: 构造函数</p>
	 * @param message 异常信息
	 */
	public PrintException(String message,Throwable e) {
		super(message,e);
	}
	
	
	public PrintException(String message) {
		super(message);
	}

}
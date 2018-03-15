package org.hbhk.aili.client.core.commons.exception;

/**
 * 
 *异常处理接口
 */
public interface IErrorHandler {
	/**
	 * 实现了此接口可以根据传入的Throwable类型进行选择性的处理
	 * handle
	 * @param t 异常
	 * @return void
	 * @since:0.6
	 */
	void handle(Throwable t);
}

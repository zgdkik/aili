package org.hbhk.aili.client.core.core.exception;

import org.hbhk.aili.client.core.core.application.IApplication;

/**
 * 
 *	异常处理接口，通常情况下，此接口用于处理框架抛出的异常。
 *其中对捕获的异常要求为异常必须是Exception子类的异常。
 */
public interface IExceptionHandler<T extends Exception> {
	/**
	 * 处理异常方法
	 * handle
	 * @param exception
	 * 需要处理的异常
	 * @param application void
	 * 应用程序，处理过程中可能会与应用程序有交互。
	 * @since:0.6
	 */
	void handle(T exception, IApplication application);
}

package org.hbhk.aili.client.core.commons.conversion;

/**
 * 
 *错误消息提供接口。此接口比较抽象，它不指定你消息从任何地方获取。
 *实现着可以任意实现。
 */
public interface ErrorMessageProvider {
	/**
	 * 获取消息
	 * getMessage
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public String getMessage();
}
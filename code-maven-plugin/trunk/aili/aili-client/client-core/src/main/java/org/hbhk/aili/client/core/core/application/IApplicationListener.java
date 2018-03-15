package org.hbhk.aili.client.core.core.application;

/**
 *提供Application在启动及退出时候的监听功能
 */
public interface IApplicationListener {
	/**
	 * 
	 * <p>Title: onStarted</p>
	 * <p>Description: 启动处理</p>
	 * @param event 应用程序事件
	 */
	void onStarted(ApplicationEvent event);
	
	/**
	 * 
	 * <p>Title: onExiting</p>
	 * <p>Description: 退出处理</p>
	 * @param event 应用程序事件
	 */
	void onExiting(ApplicationEvent event);
}
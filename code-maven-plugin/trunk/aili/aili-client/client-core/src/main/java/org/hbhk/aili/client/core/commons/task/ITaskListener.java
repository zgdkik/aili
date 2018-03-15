package org.hbhk.aili.client.core.commons.task;

import java.util.EventListener;

/**
 * 任务事件监听
 */
public interface ITaskListener extends EventListener {
	/**
	 * 任务执行
	 * 
	 * @param event 事件信息
	 */
	void executing(TaskEvent event);

	/**
	 * 取消执行
	 * 
	 * @param event 事件信息
	 */
	void cancelling(TaskEvent event);

	/**
	 * 执行结束 (不管是成功，失败，或取消，此方法均被调用)
	 * 
	 * @param event 事件信息
	 */
	void executed(TaskEvent event);

	/**
	 * 执行成功
	 * 
	 * @param event 事件信息
	 */
	void succeeded(TaskEvent event);

	/**
	 * 执行失败
	 * 
	 * @param event 事件信息
	 */
	void failed(TaskEvent event);

	/**
	 * 已取消执行
	 * 
	 * @param event 事件信息
	 */
	void cancelled(TaskEvent event);
	
	/**
	 * 后台执行
	 * 
	 * @param event 事件信息
	 */
	void backgrounded(TaskEvent event);

	/**
	 * 前台执行
	 * 
	 * @param event 事件信息
	 */
	void foregrounded(TaskEvent event);

	/**
	 * 值变更
	 * 
	 * @param event 事件信息
	 */
	void valueChanged(TaskEvent event);

	/**
	 * 片断变化
	 * 
	 * @param event 事件信息
	 */
	void chunkChanged(TaskEvent event);

	/**
	 * 标题变化
	 * 
	 * @param event 标题信息
	 */
	void titleChanged(TaskEvent event);

	/**
	 * 信息变化
	 * 
	 * @param event 事件信息
	 */
	void messageChanged(TaskEvent event);

	/**
	 * 进度变化
	 * 
	 * @param event 事件信息
	 */
	void progressChanged(TaskEvent event);
}
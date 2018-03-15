package org.hbhk.aili.client.core.commons.task;

import java.util.List;

/**
 * 任务管理服务 (实现类需保证线程安全)
 */
public interface ITaskService extends ITaskExecutor {
	/**
	 * 获取正在运行的任务
	 * 
	 * @return 正在运行的任务
	 */
	List<ITaskContext> getTaskContexts();

	/**
	 * 获取前台运行的任务
	 * 
	 * @return 前台运行的任务
	 */
	List<ITaskContext> getForegroundTaskContexts();
	
	/**
	 * 获取前台任务
	 * 
	 * @return 前台任务
	 */
	ITaskContext getForegroundTaskContext();

	/**
	 * 获取后台运行的任务
	 * 
	 * @return 后台运行的任务
	 */
	List<ITaskContext> getBackgroundTaskContexts();

	/**
	 * 添加任务事件监听
	 * 
	 * @param taskListener 任务事件监听
	 */
	void addTaskListener(ITaskListener taskListener);

	/**
	 * 移除任务事件监听
	 * 
	 * @param taskListener 任务事件监听
	 */
	void removeTaskListener(ITaskListener taskListener);
}
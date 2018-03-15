package org.hbhk.aili.client.core.commons.task;

/**
 * 任务执行接口 (实现类需保证线程安全)
 */
public interface ITaskExecutor {
	/**
	 * 执行任务
	 * @param task 任务
	 * @return 任务上下文
	 */
	ITaskContext execute(ITask task);

	/**
	 * 执行任务
	 * @param task 任务
	 * @param cancellable 是否允许取消，缺省允许
	 * @return 任务上下文
	 */
	ITaskContext execute(ITask task, boolean cancellable);

	/**
	 * 执行任务
	 * 
	 * @param task 任务
	 * @param cancellable 是否允许取消，缺省允许
	 * @param backgroundable 是否允许转为后台执行，缺省允许
	 * @return 任务上下文
	 */
	ITaskContext execute(ITask task, boolean cancellable, boolean backgroundable);

	/**
	 * 在后台执行任务
	 * 
	 * @param task 任务
	 * @return 任务上下文
	 */
	ITaskContext executeInBackground(ITask task);

	/**
	 * 在后台执行任务
	 * 
	 * @param task 任务
	 * @param cancellable 是否允许取消，缺省允许
	 * @return 任务上下文
	 */
	ITaskContext executeInBackground(ITask task, boolean cancellable);
}
package org.hbhk.aili.client.core.commons.task;

/**
 * 任务事件监听器扩展
 */
public abstract class TaskAdapter implements ITaskListener {
	/**
	 * 任务执行
	 */
	@Override
	public void executing(TaskEvent event) {
	}

	/**
	 * 任务取消
	 */
	@Override
	public void cancelling(TaskEvent event) {
	}

	/**
	 * 任务执行结束
	 */
	@Override
	public void executed(TaskEvent event) {
	}

	/**
	 * 执行成功
	 */
	@Override
	public void succeeded(TaskEvent event) {
	}

	/**
	 * 执行失败
	 */
	@Override
	public void failed(TaskEvent event) {
	}

	/**
	 * 执行取消
	 */
	@Override
	public void cancelled(TaskEvent event) {
	}

	/**
	 * 前台执行
	 */
	@Override
	public void foregrounded(TaskEvent event) {
	}

	/**
	 * 后台执行
	 */
	@Override
	public void backgrounded(TaskEvent event) {
	}

	/**
	 * 值改变
	 */
	@Override
	public void valueChanged(TaskEvent event) {
	}

	/**
	 * 片段变化
	 */
	@Override
	public void chunkChanged(TaskEvent event) {
	}

	/**
	 * 标题改变
	 */
	@Override
	public void titleChanged(TaskEvent event) {
	}

	/**
	 * 消息改变
	 */
	@Override
	public void messageChanged(TaskEvent event) {
	}

	/**
	 * 进度变化
	 */
	@Override
	public void progressChanged(TaskEvent event) {
	}
}
package org.hbhk.aili.client.core.commons.task;

import java.util.EventObject;

/**
 * 任务事件信息
 */
public class TaskEvent extends EventObject {
	//任务事件信息版本
	private static final long serialVersionUID = -7251403985319158057L;

	//任务服务管理
	private final ITaskService taskService;

	//任务
	private final ITask task;
    
	//任务上下文
    private final ITaskContext taskContext;

    /**
     * 
     * @param taskService 任务服务
     * @param task 任务
     * @param taskContext 任务上下文
     */
    public TaskEvent(ITaskService taskService, ITask task, ITaskContext taskContext) {
		super(taskService);
		this.taskService = taskService;
		this.task = task;
		this.taskContext = taskContext;
	}

	/**
	 * 获取事件产生的任务执行服务
	 * 
	 * @return 任务执行服务
	 */
	public ITaskService getTaskService() {
		return taskService;
	}

    /**
     * 获取事件任务
     * 
     * @return 任务
     */
	public ITask getTask() {
		return task;
	}

	/**
     * 获取事件任务上下文
     * 
     * @return 任务上下文
     */
	public ITaskContext getTaskContext() {
		return taskContext;
	}
}
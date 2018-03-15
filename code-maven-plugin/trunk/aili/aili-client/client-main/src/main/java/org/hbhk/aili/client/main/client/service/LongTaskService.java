package org.hbhk.aili.client.main.client.service;

import org.hbhk.aili.client.core.commons.task.ITask;
import org.hbhk.aili.client.core.commons.task.impl.TaskService;
import org.hbhk.aili.client.main.client.ui.LongTaskDialog;


/**
 * 长任务操作
 */
public class LongTaskService {
	
	/**
	 * 
	 * 执行耗时任务
	 */
	public void execute(String title, ITask task) {
		//创建任务服务
		TaskService taskService = new TaskService();
		//创建超时操作等待面板对象
		//new LongTaskDialog(title, taskService);
		//调用任务服务执行任务
		taskService.execute(task);
	}
}
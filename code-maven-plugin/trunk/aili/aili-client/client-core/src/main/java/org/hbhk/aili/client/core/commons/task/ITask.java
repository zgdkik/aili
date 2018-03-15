package org.hbhk.aili.client.core.commons.task;


/**
 * 任务接口
 */
public interface ITask {

	/**
	 * 后台执行任务.
	 * 非UI的工作，全部放在该方法内做，包括远程调用，数据转换，数据等，
	 * 其它，UI相关的工作，放在TaskListner中处理
	 * 
	 * @param context 任务上下文
	 * @throws CancelledException 任务被取消时抛出
	 * @throws Exception 任务出错时抛出异常
	 */
	void execute(ITaskContext context) throws Exception;
}

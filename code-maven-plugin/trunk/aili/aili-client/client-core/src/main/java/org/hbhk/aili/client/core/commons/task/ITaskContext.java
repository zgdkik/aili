package org.hbhk.aili.client.core.commons.task;

import java.util.Date;
import java.util.List;

/**
 * 任务会话上下文（非线程安全，只在任务执行过程中使用）
 */
public interface ITaskContext {
	
	//标识任务进度为未知的
	int UNKNOWN_PROGRESS = -1;

	//标识任务进度为0
	int MIN_PROGRESS = 0;

	//标识任务进度为完成
	int MAX_PROGRESS = 100;

	/**
	 * 保存数据
	 * 
	 * @param value 数据对象
	 */
	void setValue(Object value);

	/**
	 * 获取数据
	 * 
	 * @param <T> 值类型
	 * @return T
	 */
	<T> T getValue();

	/**
	 * 保存片断数据
	 * 
	 * @param chunk 片断数据
	 */
	void addChunk(Object chunk);

	/**
	 * 获取片断数据
	 * 
	 * @param <T> 片断数据类型
	 * @return 片断数据 (只读)
	 */
	<T> List<T> getChunks();

	/**
	 * 设置标题
	 * 
	 * @param message 标题
	 */
	void setTitle(String title);

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	String getTitle();

	/**
	 * 设置当前运行信息
	 * 
	 * @param message 当前运行信息
	 */
	void setMessage(String message);

	/**
	 * 获取当前运行信息
	 * 
	 * @return 当前运行信息
	 */
	String getMessage();

	/**
	 * 设置进度
	 * 
	 * @param progress 进度
	 */
	void setProgress(int progress, int total);

	/**
	 * 设置进度
	 * 
	 * @param progress 进度
	 */
	void setProgress(int progress);

	/**
	 * 获取进度
	 * 
	 * @return 进度
	 */
	int getProgress();

	/**
	 * 执行时间
	 * 
	 * @return 执行时间
	 */
	long getDuration();
	
	/**
	 * 启动时间
	 * 
	 * @return 启动时间
	 */
	Date getStart();

	/**
	 * 是否正在执行
	 * 
	 * @return 是否正在执行
	 */
	boolean isExecuting();

	/**
	 * 是否执行结束
	 * 
	 * @return 是否执行结束
	 */
	boolean isExecuted();

	/**
	 * 是否成功
	 * 
	 * @return 是否成功
	 */
	boolean isSucceeded();

	/**
	 * 是否失败
	 * 
	 * @return 是否失败
	 */
	boolean isFailed();

	/**
	 * 是否已取
	 * 
	 * @return 是否已取
	 */
	boolean isCancelled();

	/**
	 * 是否正在取消
	 * 
	 * @return 是否正在取消
	 */
	boolean isCancelling();

	/**
	 * 用户是否取消
	 */
	void checkCancel() throws CancelledException;

	/**
	 * 任务是否可以取消
	 * 
	 * @return 是否可以取消
	 */
	boolean isCancelable();
	
	/**
	 * 取消
	 * @return
	 */
	void setCancelable(boolean cancelable);
	
	/**
	 * 取消执行
	 */
	void cancel();
	
	/**
	 * 是否强制取消执行
	 * zxy 20140423 MANA-2018 1.4
	 */
	boolean isForceCancel();
	
	/**
	 * 设置强制取消
	 * zxy 20140423 MANA-2018 1.4
	 */
	void setForceCancel(boolean isForceCancel);

	/**
	 * 是否为后台运行
	 * 
	 * @return 后台运行
	 */
	boolean isInBackground();

	/**
	 * 是否为前台运行
	 * 
	 * @return 前台运行
	 */
	boolean isInForeground();

	/**
	 * 是否可转为后台运行
	 * 
	 * @return 
	 */
	boolean isBackgroundable();

	/**
	 * 转为后台运行
	 */
	void toBackground();
	
	/**
	 * 获取任务执行服务
	 * 
	 * @return 任务执行服务
	 */
	ITaskService getTaskService();
	
	/**
	 * 获取当前任务
	 * 
	 * @return 当前任务
	 */
	ITask getTask();

}

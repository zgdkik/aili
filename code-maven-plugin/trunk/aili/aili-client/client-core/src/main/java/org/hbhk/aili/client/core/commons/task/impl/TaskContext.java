package org.hbhk.aili.client.core.commons.task.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hbhk.aili.client.core.commons.task.CancelledException;
import org.hbhk.aili.client.core.commons.task.ITask;
import org.hbhk.aili.client.core.commons.task.ITaskContext;
import org.hbhk.aili.client.core.commons.task.ITaskListener;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.TaskEvent;

/**
 * 任务上下文
 * 执行过程中获取任务信息
 */
public class TaskContext implements ITaskContext {
	
	//任务管理服务
	private final ITaskService taskService;
	
	//任务接口
	private final ITask task;
	
	//任务监听
	private ITaskListener taskListener;

	//任务名称，线程安全
	private volatile String title;

	//任务消息，线程安全
	private volatile String message;
	
	//进度条的位置，线程安全
	private volatile int progress;

	//标识任务状态：执行
	public static final int EXECUTING = 0;

	//标识任务状态：取消
	public static final int CANCELLING = 1;

	//标识任务状态：成功
	public static final int SUCCEEDED = 2;

	//标识任务状态：失败
	public static final int FAILED = 3;

	//标识任务状态：注销
	public static final int CANCELLED = 4;

	//状态：默认为执行
	private volatile int status = EXECUTING;
	
	//标识任务完成了
	private boolean done = false;

	//任务对象，线程安全
	private volatile Object value;

	//任务块列表
	private final List<Object> chunks = new ArrayList<Object>();

	//将任务块列表转换成只读试图，保证线程安全
	private final List<Object> readonlyChunks = Collections.unmodifiableList(chunks);
	
	//任务开始时间
	private final long start;
	
	//任务结束时间
	private long end;
	
	//任务是否能撤销
	private boolean cancellable;
	
	//能在后台运行的任务
	private boolean backgroundable;
	
	//后台任务
	private boolean background;
	
	//是否强制取消执行 zxy 20140423 MANA-2018 1.4
	private boolean isForceCancel = false;

	/**
	 * 初始化变量
	 * 
	 * @param taskService
	 * @param task
	 * @param taskListener
	 * @param cancellable
	 * @param backgroundable
	 * @param background
	 */
	public TaskContext(ITaskService taskService, ITask task, ITaskListener taskListener, boolean cancellable, boolean backgroundable, boolean background) {
		this.taskService = taskService;   //任务管理服务
		this.task = task;   //任务
		this.taskListener = taskListener;   //任务监听
		this.cancellable = cancellable;   //任务是否能撤销
		this.backgroundable = backgroundable;   //是否可以在后台执行的任务
		this.background = background;   //后台任务
		this.start = System.currentTimeMillis();   //记录任务开始时间
		//判断后台任务，执行相对应的方法
		if (background) {
			//后台任务执行方法
			taskListener.backgrounded(new TaskEvent(taskService, task, this));
		} else {
			//前台任务执行方法
			taskListener.foregrounded(new TaskEvent(taskService, task, this));
		}
	}

	/**
	 * 设置任务对象
	 */
	@Override
	public void setValue(Object value) {
		this.value = value;
		//调用值改变监听
		taskListener.valueChanged(new TaskEvent(taskService, task, this));
	}

	/**
	 * 获取任务对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue() {
		return (T) value;
	}

	/**
	 * 新增任务块
	 */
	@Override
	public void addChunk(Object chunk) {
		chunks.add(chunk);
		//调用任务块改变监听
		taskListener.chunkChanged(new TaskEvent(taskService, task, this));
	}

	/**
	 * 获得所有任务块
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public <T> List<T> getChunks() {
		return (List<T>) readonlyChunks;
	}

	/**
	 * 获得任务开始时间
	 */
	@Override
	public Date getStart() {
		return new Date(start);
	}

	/**
	 * 设置任务名称
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;	
		//调用任务名称改变监听
		taskListener.titleChanged(new TaskEvent(taskService, task, this));
	}

	/**
	 * 获得任务名称
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * 设置任务消息
	 */
	@Override
	public final void setMessage(String message) {
		this.message = message;
		//调用任务消息改变监听
		taskListener.messageChanged(new TaskEvent(taskService, task, this));
	}

	/**
	 * 获取任务消息
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * progress：进度
	 * total：进度总数
	 * 设置进度条
	 */
	@Override
	public void setProgress(int progress, int total) {
		//进度不能小于0
		if (progress < MIN_PROGRESS) {
			throw new IllegalArgumentException("Invalid progress progress " + progress);
		}
		//进度总数不能小于0
		if (total < MIN_PROGRESS) {
			throw new IllegalArgumentException("Invalid total progress " + total);
		}
		//进度不能大于进度总数
		if (progress > total) {
            throw new IllegalArgumentException("Invalid progress " + progress + " greater than total progress " + total);
        }
		//进度等于进度总数
		if (progress == total) {
			//设置进度设为100完成
			setProgress(MAX_PROGRESS);
		} else {
			//计算百分比，设置进度
	        setProgress(Math.round(((float) progress / (float) total) * 100.0f));
		}
	}

	/**
	 * progress：进度
	 * 设置进度条
	 */
	@Override
	public void setProgress(int progress) {
		//判断进度值是否合法，非法值为：不等于-1 与 小于最小或者大于最大值
		if (progress != UNKNOWN_PROGRESS && (progress < MIN_PROGRESS || progress > MAX_PROGRESS)) {
            throw new IllegalArgumentException("The progress should be from 0 to 100, or -1 is unknown progress");
        }
		//进度属性赋值
		this.progress = progress;
		//调用进度改变监听
		taskListener.progressChanged(new TaskEvent(taskService, task, this));		
	}

	/**
	 * 获得进度
	 */
	@Override
	public int getProgress() {
		return progress;
	}

	/**
	 * 获得任务执行周期：结束时间 - 开始时间
	 */
	@Override
	public long getDuration() {
		return (isExecuted() ? end : System.currentTimeMillis()) - start;
	}

	/**
	 * 获取任务状态：执行中
	 */
	@Override
	public final boolean isExecuting() {
		return status == EXECUTING;
	}

	/**
	 * 获取任务状态：执行完成
	 */
	@Override
	public final boolean isExecuted() {
		return status != EXECUTING;
	}

	/**
	 * 获取任务状态：执行失败
	 */
	@Override
	public boolean isFailed() {
		return status == FAILED;
	}

	/**
	 * 获取任务状态：执行成功
	 */
	@Override
	public boolean isSucceeded() {
		return status == SUCCEEDED;
	}

	/**
	 * 获取任务状态：被取消
	 */
	@Override
	public final boolean isCancelled() {
		return status == CANCELLED;
	}

	/**
	 * 获取任务状态：取消中
	 */
	@Override
	public boolean isCancelling() {
		return status == CANCELLING;
	}

	/**
	 * 检查任务取消
	 */
	@Override
	public void checkCancel() throws CancelledException {
		//如果任务正在取消中，跑出异常
		if (isCancelling()) {
			throw new CancelledException();
		}
	}

	/**
	 * 获得撤销任务状态
	 */
	@Override
	public boolean isCancelable() {
		return cancellable;
	}

	
	
	/**
	 * 取消任务
	 */
	@Override
	public void cancel() {
		//判断是否已经取消，已取消抛出异常
		if (! isCancelable()) {
			throw new IllegalStateException("Unsupported cancel!");
		}
		//状态改为取消中
		this.status = CANCELLING;
		//调用取消任务监听
		taskListener.cancelling(new TaskEvent(taskService, task, this));
	}

	/**
	 * 设置完成
	 * @param s：状态
	 */
	public void done(int s) {
		//如果已经为完成，直接跳出
		if (done) {
			return;
		}
		//设置为完成
		done = true;
		//设置任务状态
		this.status = s;
		//设置完成时间
		end = System.currentTimeMillis();
		//判断任务是否被取消
		if (isCancelled()) {
			//调用任务取消时间监听
			taskListener.cancelled(new TaskEvent(taskService, task, this));
		} 
		//判断任务是否失败
		else if (isFailed()) {
			//调用任务失败时间监听
			taskListener.failed(new TaskEvent(taskService, task, this));
		}
		//既不是被取消也不是失败
		else {
			//调用任务成功完成监听
			taskListener.succeeded(new TaskEvent(taskService, task, this));
		}
		//调用任务执行结束监听
		taskListener.executed(new TaskEvent(taskService, task, this));
	}

	/**
	 * 返回是否是可以在后台运行的任务
	 */
	@Override
	public boolean isBackgroundable() {
		return backgroundable;
	}

	/**
	 * 转为后台任务
	 */
	@Override
	public void toBackground() {
		//后台任务属性设置为true
		this.background = true;
		//调用后台运行监听
		taskListener.backgrounded(new TaskEvent(taskService, task, this));
	}

	/**
	 * 返回任务是否在后台
	 */
	@Override
	public boolean isInBackground() {
		return background;
	}

	/**
	 * 返回任务是否在前台
	 */
	@Override
	public boolean isInForeground() {
		return ! background;
	}

	/**
	 * 获得任务服务管理
	 */
	public ITaskService getTaskService() {
		return taskService;
	}

	/**
	 * 获得任务
	 */
	public ITask getTask() {
		return task;
	}
	
	/**
	 * 任务名称+开始时间
	 */
	@Override
	public String toString() {
		return getTitle() + " (" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(getStart()) + ")";
	}

	/**
	 * 是否可以删除
	 */
	public void setCancelable(boolean cancelable) {
		this.cancellable= cancelable;
		
	}

	@Override
	public boolean isForceCancel() {
		return isForceCancel;
	}

	public void setForceCancel(boolean isForceCancel) {
		this.isForceCancel = isForceCancel;
		if(this.isForceCancel)
			setCancelable(this.isForceCancel);
	}


}

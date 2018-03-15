package org.hbhk.aili.client.core.commons.task.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.task.CancelledException;
import org.hbhk.aili.client.core.commons.task.ITask;
import org.hbhk.aili.client.core.commons.task.ITaskContext;
import org.hbhk.aili.client.core.commons.task.ITaskListener;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.TaskEvent;

/**
 * 任务服务管理实现
 *
 */
public class TaskService implements ITaskService {
	
	public static final Log log = LogFactory.getLog(TaskService.class);

	//任务监听列表
	private final Set<ITaskListener> taskListeners = new CopyOnWriteArraySet<ITaskListener>();

	//任务上下文列表
	private final List<ITaskContext> taskContexts = new CopyOnWriteArrayList<ITaskContext>();

	//任务上下文列表
	private final List<ITaskContext> foregroundTaskContexts = new CopyOnWriteArrayList<ITaskContext>();

	//任务上下文列表
	private final List<ITaskContext> backgroundTaskContexts = new CopyOnWriteArrayList<ITaskContext>();
	
	//异步任务管理
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);

	/**
	 * 新增任务监听
	 */
	@Override
	public void addTaskListener(ITaskListener taskListener) {
		taskListeners.add(taskListener);
	}

	/**
	 * 删除任务监听
	 */
	@Override
	public void removeTaskListener(ITaskListener taskListener) {
		taskListeners.remove(taskListener);
	}

	/**
	 * 获取正在运行的任务列表
	 */
	@Override
	public List<ITaskContext> getTaskContexts() {
		//获得不可修改的任务列表
		return Collections.unmodifiableList(taskContexts);
	}

	/**
	 * 获得前台任务
	 */
	@Override
	public ITaskContext getForegroundTaskContext() {
		//获得正在运行任务的迭代器
		Iterator<ITaskContext> iterator = foregroundTaskContexts.iterator();
		//如果前台有任务，则返回任务
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	/**
	 * 获得前台运行的任务
	 */
	@Override
	public List<ITaskContext> getForegroundTaskContexts() {
		return foregroundTaskContexts;
	}

	/**
	 * 获得后台运行任务
	 */
	@Override
	public List<ITaskContext> getBackgroundTaskContexts() {
		return backgroundTaskContexts;
	}

	/**
	 * 执行任务
	 * 
	 * @param task 任务
	 * @return 任务上下文
	 */
	@Override
	public ITaskContext execute(ITask task) {
		return doExecute(task, true, true, false);
	}

	/**
	 * 运行任务
	 */
	@Override
	public ITaskContext execute(ITask task, boolean cancellable) {
		return doExecute(task, cancellable, true, false);
	}

	/**
	 * 执行任务
	 */
	@Override
	public ITaskContext execute(ITask task, boolean cancellable,
			boolean backgroundable) {
		return doExecute(task, cancellable, backgroundable, false);
	}

	/**
	 * 在后台执行任务
	 */
	@Override
	public ITaskContext executeInBackground(ITask task) {
		return doExecute(task, true, true, true);
	}

	/**
	 * 在后台执行任务
	 */
	@Override
	public ITaskContext executeInBackground(ITask task, boolean cancellable) {
		return doExecute(task, cancellable, true, true);
	}
	
	/**
	 * 执行任务
	 * @param task：任务
	 * @param cancellable：前台执行
	 * @param backgroundable：可在后台执行
	 * @param background：后台执行
	 * @return ITaskContext 任务上下文
	 */
	private ITaskContext doExecute(final ITask task, boolean cancellable,boolean backgroundable, boolean background) {
		//获取任务监听
		final ITaskListener taskListener = wrapTaskListener(task);
		//获得任务上下文
		final TaskContext context = new TaskContext(this, task, taskListener, cancellable, backgroundable, background);
		//执行异步任务
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				//调用任务执行事件
				taskListener.executing(new TaskEvent(TaskService.this, task, context));
				try {
					//执行任务
					task.execute(context);
					//任务状态设置完成
					context.done(TaskContext.SUCCEEDED);
				} catch (CancelledException e){
					//将取消异常设置给任务上下文的值
					context.setValue(e);
					//任务状态设置取消
					context.done(TaskContext.CANCELLED);
				} catch (Exception e){
					//将任务上下文的值设置为异常堆栈信息
					context.setValue(e);
					//任务状态设置失败
					context.done(TaskContext.FAILED);
				}
			}
		});
		//返回任务上下文
		return context;
	}

	/**
	 * 实现任务事件监听
	 * @param task 任务
	 * @return
	 */
	private ITaskListener wrapTaskListener(final ITask task) {
		//实现任务事件监听，并返回
		return new ITaskListener() {
			//任务执行
			@Override
			public void executing(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {					
					@Override
					public void run() {
						//获得任务上下文
						ITaskContext context = event.getTaskContext();
						//添加到任务上下文列表
						taskContexts.add(context);
						//判断是否在后台执行
						if (context.isInBackground()) {
							//添加至后台执行任务上下文列表
							backgroundTaskContexts.add(context);
						} else {
							//添加至前台执行任务上下文列表
							foregroundTaskContexts.add(context);
						}
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//转换为任务事件监听后调用任务执行事件
								((ITaskListener)task).executing(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
						//遍历任务监听
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用任务执行事件监听
								taskListener.executing(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}

			//取消任务事件
			@Override
			public void cancelling(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用任务取消事件
								((ITaskListener)task).cancelling(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用取消任务事件监听
								taskListener.cancelling(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}

			//任务执行结束
			@Override
			public void executed(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//从任务事件中获得任务上下文
						ITaskContext context = event.getTaskContext();
						//从任务上下文列表中删除该任务的上下文
						taskContexts.remove(context);
						//前台任务上下文列表中删除该任务的上下文
						foregroundTaskContexts.remove(context);
						//后台任务上下文列表中删除该任务的上下文
						backgroundTaskContexts.remove(context);
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用任务执行结束事件
								((ITaskListener)task).executed(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用任务结束事件监听
								taskListener.executed(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}

			//任务执行成功
			@Override
			public void succeeded(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用任务执行成功事件
								((ITaskListener)task).succeeded(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用任务执行成功事件监听
								taskListener.succeeded(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}

			//任务执行失败
			@Override
			public void failed(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用任务执行失败事件
								((ITaskListener)task).failed(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用任务执行失败事件监听
								taskListener.failed(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}

			//任务已取消执行
			@Override
			public void cancelled(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用任务已取消事件
								((ITaskListener)task).cancelled(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用任务已取消事件监听
								taskListener.cancelled(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}
			
			//任务后台执行
			@Override
			public void backgrounded(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//从任务消息中获取任务上下文
						ITaskContext context = event.getTaskContext();
						//从前台上下文列表中删除该任务的上下文
						foregroundTaskContexts.remove(context);
						//从后台上下文列表中删除该任务的上下文
						backgroundTaskContexts.add(context);
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用后台执行任务事件
								((ITaskListener)task).backgrounded(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用后台执行任务事件监听
								taskListener.backgrounded(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
					}
				});
			}

			//前台执行
			@Override
			public void foregrounded(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用前台执行事件监听
								((ITaskListener)task).foregrounded(event);
							} catch (Exception t) {
								log.error(t);
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用前台执行事件监听
								taskListener.foregrounded(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
					}
				});
			}

			//值变更
			@Override
			public void valueChanged(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用值改变事件监听
								((ITaskListener)task).valueChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用值改变事件监听
								taskListener.valueChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
					}
				});
			}

			//判断改变
			@Override
			public void chunkChanged(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用片段改变事件监听
								((ITaskListener)task).chunkChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用片段改变事件监听
								taskListener.chunkChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
					}
				});
			}

			//信息改变
			@Override
			public void messageChanged(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用信息改变事件监听
								((ITaskListener)task).messageChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用信息改变事件监听
								taskListener.messageChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
					}
				});
			}

			//进度变化
			@Override
			public void progressChanged(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用进度改变事件监听
								((ITaskListener)task).progressChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用进度改变事件监听
								taskListener.progressChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
					}
				});
			}

			//标题变化
			@Override
			public void titleChanged(final TaskEvent event) {
				//事件指派的异步执行
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						//判断任务类型是否为任务监听
						if (task instanceof ITaskListener) {
							try {
								//调用标题改变事件监听
								((ITaskListener)task).titleChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
						//遍历任务监听列表
						for (ITaskListener taskListener : taskListeners) {
							try {
								//调用标题改变事件监听
								taskListener.titleChanged(event);
							} catch (Exception t) {
								t.printStackTrace();
							}
						}
					}
				});
			}

		};
	}

}

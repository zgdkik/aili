package org.hbhk.aili.client.boot.autorun;

import java.util.List;

import javax.swing.JDialog;

import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.TaskAdapter;
import org.hbhk.aili.client.core.commons.task.TaskEvent;
import org.hbhk.aili.client.core.commons.task.impl.TaskService;
import org.hbhk.aili.client.core.component.task.TaskForegroundDialog;


/**
 * 顺序执行的自动运行任务执行者
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:dp-yangtong,date:2012-10-12 上午9:30:37, </p>
 * @author dp-yangtong
 * @date 2012-10-12 上午9:30:37
 * @since
 * @version
 */
public class SequentialAutoRunExecutor extends TaskAdapter {

    	/**
	 * 任务管理服务
	 */
	private ITaskService taskService;
	/**
	 * 自动运行任务对象集合
	 */
	private List<AutoRun> autoRuns;
	/**
	 * 自动运行任务阶段对象
	 */
	private AutoRunPhase phase;
	/**
	 * 当前执行的任务数
	 */
	private int current;
	/**
	 * 
	 */
	private boolean halt;
	
	private JDialog taskDialog;
	
	
	public SequentialAutoRunExecutor(List<AutoRun> autoRuns, AutoRunPhase phase) {
		this.taskService = new TaskService();
//		if(taskDialog == null){
//			taskDialog = new TaskForegroundDialog(taskService);
//			taskDialog.setAlwaysOnTop(false);
//		}
		this.autoRuns = autoRuns;
		this.phase = phase;
		current = -1;
		halt = false;
	}
	
	public void execute() {
	    	/**
		 * 执行下一个任务
		 */
		executeNextTask();
	}
	
	public boolean isHalt() {
		return halt;
	}

	private void executeNextTask() {
	    	/**
		 * current变量在原来的基础上加1
		 */
		current++;
		/**
		 * 判断current是否大于等于autoRuns的size，如果是，则使当前类处于同步状态，调用其唤醒方法notify()
		 */
		if (current >= autoRuns.size()) {
			synchronized (this) {
				notify();
			}
			return;
		}
		/**
		 * 获取AutoRun对象
		 */
		AutoRun autoRun = autoRuns.get(current);
		
//		if(!autoRun.hiddenDialog){
//			taskDialog.setAlwaysOnTop(true);
//		}else{
//			
//		}
		
		/**
		 * 判断autoRun.phase是否等于phase或者autoRun.order是否小于0
		 */
		if (autoRun.phase != phase || autoRun.order < 0) {
		    	/**
			 * 执行下一个任务并返回
			 */
			executeNextTask();
			return;
		}
		/**
		 * 调用新增任务监听方法
		 */
		taskService.addTaskListener(this);
		/**
		 * 判断phase是否等于AutoRunPhase.BEFORE_LOGIN，根据判断条件设置execute参数，调用execute方法
		 */
		if (phase == AutoRunPhase.BEFORE_LOGIN) {
			BeforeLoginTaskContext beforeLoginTaskContext = new BeforeLoginTaskContext();
			try {
				autoRun.autoRunner.execute(beforeLoginTaskContext);
			} catch (Exception e) {
				e.printStackTrace();
			}
			executeNextTask();
			//taskService.execute(autoRun.autoRunner, false, false);
		} else {
			
			if(taskDialog == null){
				taskDialog = new TaskForegroundDialog(taskService);
				taskDialog.setAlwaysOnTop(false);
			}
			taskService.execute(autoRun.autoRunner,/**autoRun.cancelable*/false,/** autoRun.canRunBackground*/false);
			
		}
	}
	
	public void succeeded(TaskEvent event) {
	    	/**
		 * 执行下一个任务
		 */
		executeNextTask();
	}
	
	public void failed(TaskEvent event) {
	    	/**
		 * 判断autoRuns是否包括haltOnError
		 */
		if (autoRuns.get(current).haltOnError) {
		    	/**
			 * 设置halt为true，使当前类出月同步状态，调用notify()方法唤醒
			 */
			halt = true;
			synchronized (this) {
				notify();
			}
			return;
		}
		/**
		 * 执行下一个任务
		 */
		executeNextTask();
	}
	
	public void cancelled(TaskEvent event) {
	    	/**
		 * 执行下一个任务
		 */
		executeNextTask();
	}
}
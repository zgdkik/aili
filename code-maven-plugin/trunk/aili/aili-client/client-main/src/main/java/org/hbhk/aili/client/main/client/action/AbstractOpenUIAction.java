package org.hbhk.aili.client.main.client.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import org.hbhk.aili.client.core.commons.exception.NormalException;
import org.hbhk.aili.client.core.commons.task.ITask;
import org.hbhk.aili.client.core.commons.task.ITaskContext;
import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.application.IApplicationAware;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.main.client.service.LongTaskService;
import org.hbhk.aili.client.main.client.utills.InitUIDialog;


public abstract class AbstractOpenUIAction<T> extends AbstractAction implements
		IApplicationAware {


	/**
	 * 
	 * 序列
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * open time
	 */
	private int opentime;

	/**
	 * 
	 *是否需展现
	 */
	private boolean isNeedShow = true;

	/**
	 * 
	 * 打开动作触发事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//创建耗时任务
		LongTaskService taskService = new LongTaskService();
		//执行耗时任务
		taskService.execute("打开菜单中...", new ITask() {
			@Override
			public void execute(ITaskContext context) throws Exception {
				//原处理逻辑开始
				if (isInstance(getOpentime())) {
					//出错提示，不能直接使用Msgbox.show...
					throw new NormalException("对不起，最多只能打开" + getOpentime() 
							+ "个相同窗口！");
				} else {
				    	/**
				    	 * 判断isNeedShow是否为true
				    	 */
					if (isNeedShow) {
					    	/**
					    	 * 创建一个InitUIDialog对象
					    	 */
						new InitUIDialog(getApplication()).execute();
					}
					/**
					 * 在工作进程中新开一个线程
					 */
					SwingUtilities.invokeLater(new Runnable() {
					    	/**
						 * 调用线程的run()方法，定义线程在运行时要调用openUIAction()方法
						 */
						@Override
						public void run() {
							openUIAction();
						}
					});
				}
				//原处理逻辑结束
			}
		});
	}

	/**
	 * ABSTRACT METHOD TO BE IMPLEMENTS BY DIFFERENT ACTION
	 */
	public abstract void openUIAction();

	/**
	 * 判断是否已经实例化 isInstence
	 * 
	 * @return boolean
	 * @since:1.6
	 */
	private boolean isInstance(int n) {
	    	/**
	    	 * 定义布尔型变量isInstenceThree，默认为false
	    	 */
		boolean isInstenceThree = false;
		/**
	    	 * 创建IEditor类型的List集合并获取
	    	 */
		List<IEditor> editors = getApplication().getWorkbench().getEditors();

		int i = 0;
		/**
	    	 * 循环遍历editors
	    	 */
		for (IEditor editor : editors) {
		    	/**
		    	 * 判断editor.getComponent().getClass()与getOpenUIType()是否相等
		    	 */
			if (editor.getComponent().getClass() == getOpenUIType()) {
			    	
				i++;	//i加1

			}
		}
		/**
	    	 * 判断editors的size是否大于等于1，如果是，则isNeedShow设置为false，否则，设置为true
	    	 */
		if (editors.size() >= 1) {
			isNeedShow = false;
		} else {
			isNeedShow = true;
		}
		/**
	    	 * 判断i是否大于等于n，如果是，则将isInstenceThree设置为true
	    	 */
		if (i >= n) {
			isInstenceThree = true;
		}

		return isInstenceThree;	//返回isInstenceThree
	}

	/**
	 * 
	 * 功能：getApplication
	 * 
	 * @param:
	 * @return IApplication
	 * @since:1.6
	 */
	public abstract IApplication getApplication();

	/**
	 * 
	 * 功能：getOpentime
	 * 
	 * @param:
	 * @return int
	 * @since:1.6
	 */
	public int getOpentime() {
		return opentime;
	}

	/**
	 * 
	 * 功能：setOpentime
	 * 
	 * @param:
	 * @return void
	 * @since:1.6
	 */
	public void setOpentime(int opentime) {
		this.opentime = opentime;
	}

	/**
	 * 
	 * 功能：getOpenUIType
	 * 
	 * @param:
	 * @return Class<T>
	 * @since:1.6
	 */
	public abstract Class<T> getOpenUIType();

}
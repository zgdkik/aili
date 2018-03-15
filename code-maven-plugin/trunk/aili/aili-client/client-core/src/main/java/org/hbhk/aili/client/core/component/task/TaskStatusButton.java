package org.hbhk.aili.client.core.component.task;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.hbhk.aili.client.core.commons.task.ITaskContext;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.TaskAdapter;
import org.hbhk.aili.client.core.commons.task.TaskEvent;
import org.hbhk.aili.client.core.commons.util.ImageUtil;

/**
 * 
 * <p>Description: 任务状态按钮</p>
 *
 */
public class TaskStatusButton extends JLabel {
	private static final long serialVersionUID = 1L;

	// 空闲图标
	private static final Icon IDLE_ICON = ImageUtil.getImageIcon(TaskForegroundDialog.class,"icon_message.png");

	// 激活图标
	private static final Icon ACTIVE_ICON = ImageUtil.getImageIcon(TaskForegroundDialog.class,"icon_message.png");

	// 错误图标
	private static final Icon ERROR_ICON = ImageUtil.getImageIcon(TaskForegroundDialog.class,"icon_message.png");

	/**
	 * 
	 * <p>Title: TaskStatusButton</p>
	 * <p>Description: 构造函数</p>
	 * @param taskService 任务服务接口
	 * @param taskBackgroundDialog 后台任务对话框
	 */
	public TaskStatusButton(final ITaskService taskService, final TaskBackgroundDialog taskBackgroundDialog) {
		super(IDLE_ICON);
		this.setPreferredSize(new Dimension(20, 14));
		
		/**
		 * 添加鼠标监听器
		 */
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent evt) {
				taskBackgroundDialog.setVisible(true);
			}
		});
		
		/**
		 * 添加任务监听器
		 */
		taskService.addTaskListener(new TaskAdapter(){
			@Override
			public void executing(TaskEvent event) {
				ITaskContext context = event.getTaskContext();
				if (context.isExecuting()) {
					TaskStatusButton.this.setIcon(ACTIVE_ICON);
				}
			}

			@Override
			public void executed(TaskEvent event) {
				if (taskService.getTaskContexts().size() == 0) {
					TaskStatusButton.this.setIcon(IDLE_ICON);
				}
			}

			@Override
			public void failed(TaskEvent event) {
				TaskStatusButton.this.setIcon(ERROR_ICON);
			}			
		});
	}
}
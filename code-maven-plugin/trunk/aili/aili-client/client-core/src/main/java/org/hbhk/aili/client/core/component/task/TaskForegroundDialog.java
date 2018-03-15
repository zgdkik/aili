package org.hbhk.aili.client.core.component.task;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.task.ITaskContext;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.TaskAdapter;
import org.hbhk.aili.client.core.commons.task.TaskEvent;
import org.hbhk.aili.client.core.commons.task.impl.TaskContext;
import org.hbhk.aili.client.core.commons.util.ImageUtil;

/**
 * <p>Description: 前台任务对话框</p>
 *
 */
public class TaskForegroundDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// 前台任务图片
	private static final Icon BACK_ICON = ImageUtil.getImageIcon(TaskForegroundDialog.class,"task/back.gif");

	// 取消按钮图片
	private static final Icon CANCEL_ICON = ImageUtil.getImageIcon(TaskForegroundDialog.class, "task/cancel.gif");

	// 任务内容
	private volatile ITaskContext taskContext;

	// 国际化对象
	private static final II18n i18n = I18nManager.getI18n(TaskForegroundDialog.class);

	/**
	 * 
	 * <p>Title: TaskForegroundDialog</p>
	 * <p>Description: 构造函数</p>
	 * @param taskService 任务服务接口
	 */
	public TaskForegroundDialog(final ITaskService taskService) {
		// 设置为模式对话框
		setModal(true);
		// 窗口关闭时的处理
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(450, 185);
		this.setResizable(false);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = getSize();
		setLocation((scr.width - fra.width) / 2, (scr.height - fra.height) / 2);
		getContentPane().setLayout(null);

		// 标题标签
		final JLabel titleLabel = new JLabel();
		titleLabel.setBounds(15, 15, 415, 20);
		getContentPane().add(titleLabel);

		// 进度条
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(15, 50, 415, 20);
		getContentPane().add(progressBar);

		// 消息标签
		final JLabel messageLabel = new JLabel();
		messageLabel.setBounds(15, 70, 375, 20);
		getContentPane().add(messageLabel);

		// 百分比标签
		final JLabel percentLabel = new JLabel();
		percentLabel.setBounds(390, 70, 40, 20);
		percentLabel.setHorizontalAlignment(JLabel.RIGHT);
		getContentPane().add(percentLabel);
		
		// 时间标签
		final JLabel timeLabel = new JLabel();
		timeLabel.setBounds(15, 110, 200, 20);
		getContentPane().add(timeLabel);

		// 转到后台按钮
		final JButton toBackgroundButton = new JButton(i18n.get("RunInBackground"), BACK_ICON);
		toBackgroundButton.setBounds(240, 110, 100, 24);
		getContentPane().add(toBackgroundButton);

		// 取消按钮
		final JButton cancelButton = new JButton(i18n.get("Cancel"), CANCEL_ICON);
		cancelButton.setBounds(350, 110, 80, 24);
		getContentPane().add(cancelButton);

		/**
		 * 添加任务监听器
		 */
		taskService.addTaskListener(new TaskAdapter() {
			private void checkForegroundTaskContext() {
				ITaskContext context = taskService.getForegroundTaskContext();
				if (context != null && context.isInForeground()) {
					showForegroundTaskContext(context);
				} else {
					TaskForegroundDialog.this.taskContext = null;
					TaskForegroundDialog.this.setVisible(false);
				}
			}
			
			/**
			 * 
			 * <p>Title: showForegroundTaskContext</p>
			 * <p>Description: 显示前台任务内容</p>
			 * @param context
			 */
			private void showForegroundTaskContext(ITaskContext context) {
				if (TaskForegroundDialog.this.taskContext == context || ! context.isInForeground()) {
					return;
				}
				TaskForegroundDialog.this.taskContext = context;
				
				String title = context.getTitle() == null ? "" : context.getTitle();
				TaskForegroundDialog.this.setTitle(title + " (" + new SimpleDateFormat("HH:mm:ss").format(context.getStart()) + ")");
				titleLabel.setText(title);
				messageLabel.setText("");
				percentLabel.setText("");
				timeLabel.setText("");
				progressBar.setValue(0);
				toBackgroundButton.setEnabled(context.isBackgroundable());
				cancelButton.setEnabled(context.isCancelable());
				TaskForegroundDialog.this.setVisible(true);
			}
			
			@Override
			public void foregrounded(TaskEvent event) {
				showForegroundTaskContext(event.getTaskContext());
			}
			
			@Override
			public void backgrounded(TaskEvent event) {
				checkForegroundTaskContext();
			}
			
			@Override
			public void executed(TaskEvent event) {
				checkForegroundTaskContext();
			}
			
			@Override
			public void titleChanged(TaskEvent event) {
				if (event.getTaskContext() != TaskForegroundDialog.this.taskContext){
					return;
				}
					
				String title = event.getTaskContext().getTitle() == null ? "" : event.getTaskContext().getTitle();
				TaskForegroundDialog.this.setTitle(title + " (" + new SimpleDateFormat("HH:mm:ss").format(event.getTaskContext().getStart()) + ")");
				titleLabel.setText(event.getTaskContext().getTitle());
			}
			
			@Override
			public void messageChanged(TaskEvent event) {
				if (event.getTaskContext() != TaskForegroundDialog.this.taskContext){
					return;
				}
					
				messageLabel.setText(event.getTaskContext().getMessage());
			}
			
			@Override
			public void progressChanged(TaskEvent event) {
				if (event.getTaskContext() != TaskForegroundDialog.this.taskContext){
					return;
				}
					
				int p = event.getTaskContext().getProgress();
				if (p == TaskContext.UNKNOWN_PROGRESS) {
					progressBar.setIndeterminate(true);
					percentLabel.setText("");
					timeLabel.setText("");
				} else {
					progressBar.setValue(p);
					percentLabel.setText(p + "%");
					timeLabel.setText(TaskUtils.getRemainTime(event.getTaskContext()));
				}
			}
		});

		/**
		 * 为转到后台按钮添加监听器
		 */
		toBackgroundButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final ITaskContext context = taskContext;
				if (context != null) {
					context.toBackground();
				}
			}
		});

		/**
		 * 为取消按钮添加监听器
		 */
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final ITaskContext context = taskContext;
				if (context != null) {
					context.cancel();
				}
			}
		});
	}
}
package org.hbhk.aili.client.core.component.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.task.ITaskContext;
import org.hbhk.aili.client.core.commons.task.ITaskService;
import org.hbhk.aili.client.core.commons.task.TaskAdapter;
import org.hbhk.aili.client.core.commons.task.TaskEvent;
import org.hbhk.aili.client.core.commons.task.impl.TaskContext;
import org.hbhk.aili.client.core.commons.util.ImageUtil;

/**
 * 
 *	<small>Description: 后台任务对话框
 */
public class TaskBackgroundDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	// 取消按钮图片
	private static final Icon CANCEL_ICON = ImageUtil.getImageIcon(TaskBackgroundDialog.class.getClassLoader(),"task/cancel.gif");

	// 任务列表模块
	private final DefaultListModel taskListModel;

	// 任务列表
	private final JList taskList;
	
	// 国际化对象
	private static final II18n i18n = I18nManager.getI18n(TaskBackgroundDialog.class);

	/**
	 * 
	 * <p>Title: TaskBackgroundDialog</p>
	 * <p>Description: 构造函数</p>
	 * @param taskService 
	 */
	public TaskBackgroundDialog(final ITaskService taskService) {
		// 设置标题
		setTitle(i18n.get("TaskList"));
		
		// 为窗口关闭按钮操作添加处理
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setSize(400, 500);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = getSize();
		setLocation((scr.width - fra.width) / 2, (scr.height - fra.height) / 2);
		setLayout(new BorderLayout());
		
		/**
		 * 为窗口添加焦点监听器
		 */
		this.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				TaskBackgroundDialog.this.setVisible(false);
			}
			@Override
			public void windowGainedFocus(WindowEvent e) {
			}
		});

		// 工具栏
		JToolBar buttonBar= new JToolBar();
		buttonBar.setFloatable(false);
		this.add(BorderLayout.NORTH, buttonBar);

		// 取消按钮
		final JButton cancelButton = new JButton(i18n.get("Cancel"));
		cancelButton.setIcon(CANCEL_ICON);
		cancelButton.setEnabled(false);
		buttonBar.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TaskContext context = (TaskContext)taskList.getSelectedValue();
				if (context != null) {
					context.cancel();
				}
			}
		});
		
		// 任务列表模块
		taskListModel = new DefaultListModel();
		taskList = new JList(taskListModel);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.setCellRenderer(new TaskListCellRenderer());
		taskList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				TaskContext context = (TaskContext)taskList.getSelectedValue();
				cancelButton.setEnabled(context != null && context.isCancelable());
			}
		});

		// 任务列表面板
		final JScrollPane taskListPane = new JScrollPane();
		taskListPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		taskListPane.getViewport().setView(taskList);
		this.add(BorderLayout.CENTER, taskListPane);
		
		/**
		 * 添加任务监听器
		 */
		taskService.addTaskListener(new TaskAdapter(){
			@Override
			public void executing(TaskEvent event) {
				ITaskContext context = event.getTaskContext();
				if (context.isExecuting()) {
					taskListModel.addElement(context);
				}
			}
			@Override
			public void executed(TaskEvent event) {
				taskListModel.removeElement(event.getTaskContext());
			}
			@Override
			public void titleChanged(TaskEvent event) {
				taskList.repaint();
			}
			@Override
			public void messageChanged(TaskEvent event) {
				taskList.repaint();
			}
			@Override
			public void progressChanged(TaskEvent event) {
				taskList.repaint();
			}
		});
	}

	/**
	 * 
	 * <p>Title: TaskListCellRenderer</p>
	 * <p>Description: 任务列表单元格渲染类</p>
	 * <p>Company: DEPPON</p>
	 * @author Polo Yuan
	 * @date 2011-6-14
	 *
	 */
	private static class TaskListCellRenderer extends JPanel implements ListCellRenderer {
		private static final long serialVersionUID = 1L;
		
		private static final Border UNSLECTED_BORDER = BorderFactory.createLineBorder(Color.WHITE, 2);

		private static final Border SLECTED_BORDER = BorderFactory.createLineBorder(Color.RED, 2);
		
		// 标题标签
		private JLabel titleLabel;

		// 时间标签
		private JLabel timeLabel;
		
		// 进度条
		private JProgressBar progressBar;
		
		// 消息标签
		private JLabel messageLabel;

		// 百分比标签
		private JLabel percentLabel;
		
		/**
		 * 
		 * <p>Title: TaskListCellRenderer</p>
		 * <p>Description: 构造函数</p>
		 */
		public TaskListCellRenderer() {
			this.setLayout(new BorderLayout());
			
			JPanel topPane = new JPanel();
			topPane.setLayout(new BorderLayout());
			this.add(BorderLayout.NORTH, topPane);

			titleLabel = new JLabel();
			topPane.add(BorderLayout.CENTER, titleLabel);
			
			timeLabel = new JLabel();
			timeLabel.setHorizontalAlignment(JLabel.RIGHT);
			topPane.add(BorderLayout.EAST, timeLabel);
			
			progressBar = new JProgressBar();
			this.add(BorderLayout.CENTER, progressBar);

			JPanel bottomPane = new JPanel();
			bottomPane.setLayout(new BorderLayout());
			this.add(BorderLayout.SOUTH, bottomPane);

			messageLabel = new JLabel();
			bottomPane.add(BorderLayout.CENTER, messageLabel);

			percentLabel = new JLabel();
			percentLabel.setHorizontalAlignment(JLabel.RIGHT);
			bottomPane.add(BorderLayout.EAST, percentLabel);
		}

		/*
		 * (non-Javadoc)
		 * <p>Title: getListCellRendererComponent</p>
		 * <p>Description: 获取列表单元格渲染组件</p>
		 * @param list
		 * @param value
		 * @param index
		 * @param isSelected
		 * @param cellHasFocus
		 * @return
		 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
		 */
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			TaskContext context = (TaskContext)value;
			titleLabel.setText(context.getTitle() + (context.isCancelled() ? "(Cancelling)" : ""));
			timeLabel.setText(TaskUtils.getRemainTime(context));
			progressBar.setValue(context.getProgress());
			messageLabel.setText(context.getMessage());
			percentLabel.setText(context.getProgress() + "%");
			if(isSelected) {
				this.setBorder(SLECTED_BORDER);
			} else {
				this.setBorder(UNSLECTED_BORDER);
			}
			return this;
		}
	}
}
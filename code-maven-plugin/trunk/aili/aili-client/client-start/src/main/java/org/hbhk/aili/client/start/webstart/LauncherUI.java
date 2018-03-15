package org.hbhk.aili.client.start.webstart;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

public class LauncherUI {

	private final JFrame frame;

	private final JLabel guideMessage;

	private final JLabel progressMessage;

	private final JProgressBar progressBar;


	private String[] buttons_1 = new String[] {
			Messages.getString("dialog.button.retry"),
			Messages.getString("dialog.button.ignore") };

	private String[] buttons_2 = new String[] {
			Messages.getString("dialog.button.ok"),
			Messages.getString("dialog.button.no") };

	static {
		try {
			// 设置swing样式为当前系统风格
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LauncherUI() {
		// 创建窗体
		frame = new JFrame(Messages.getString("title"));
		// frame.setDefaultCloseOperation ( JFrame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(488, 183);
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension fra = frame.getSize();
		frame.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2); // 在屏幕居中显示
		// frame.getRootPane().setFocusable(true);
		// frame.getRootPane().setFocusCycleRoot(true);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = showWarn("WARNING", Messages
						.getString("guide.before.interrupt"), buttons_2);
				if (result == 0) {
					close();
					System.exit(0);
				}
				return;
			}
		});

		// 创建向导信息栏
		guideMessage = new JLabel();
		guideMessage.setSize(439, 30);
		guideMessage.setLocation(20, 10);
		frame.getContentPane().add(guideMessage);

		// 创建进度信息栏
		progressMessage = new JLabel();
		progressMessage.setSize(439, 30);
		progressMessage.setLocation(20, 66);
		frame.getContentPane().add(progressMessage);

		// 创建进度条
		progressBar = new JProgressBar();
		progressBar.setSize(439, 24);
		progressBar.setLocation(20, 119);
		frame.getContentPane().add(progressBar);

		// 显示窗体
		frame.setVisible(true);
	}


	/**
	 * @return the buttons_1
	 */
	public String[] getButtons_1() {
		return buttons_1;
	}


	/**
	 * @return the buttons_2
	 */
	public String[] getButtons_2() {
		return buttons_2;
	}


	/**
	 * @param buttons_1 the buttons_1 to set
	 */
	public void setButtons_1(String[] buttons_1) {
		this.buttons_1 = buttons_1;
	}


	/**
	 * @param buttons_2 the buttons_2 to set
	 */
	public void setButtons_2(String[] buttons_2) {
		this.buttons_2 = buttons_2;
	}


	/**
	 * 设置向导信息
	 *
	 * @param message
	 *            向导信息
	 */
	public void setGuideMessage(String message) {
		guideMessage.setText(message);
	}

	/**
	 * 设置进度信息
	 *
	 * @param message
	 *            进度信息
	 */
	public void setProgressMessage(String message) {
		progressMessage.setText(message);
	}

	/**
	 * 设置进度条百分比
	 *
	 * @param percent
	 *            进度条百分比
	 */
	public void setProgressPercent(int percent) {
		progressBar.setValue(percent);
	}

	/**
	 * 弹出询问信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            询问信息
	 * @param buttons
	 *            按钮数组
	 * @return 用户所点击的按钮数组下标, 如果用户直接点关闭对话框则返回-1
	 */
	public int showAsk(String title, String message, String[] buttons) {
		return showDialog(JOptionPane.QUESTION_MESSAGE, title, message, buttons);
	}

	/**
	 * 弹出普通信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            普通信息
	 * @param buttons
	 *            按钮数组
	 * @return 用户所点击的按钮数组下标, 如果用户直接点关闭对话框则返回-1
	 */
	public int showInfo(String title, String message, String[] buttons) {
		return showDialog(JOptionPane.INFORMATION_MESSAGE, title, message,
				buttons);
	}

	/**
	 * 弹出警告信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            警告信息
	 * @param buttons
	 *            按钮数组
	 * @return 用户所点击的按钮数组下标, 如果用户直接点关闭对话框则返回-1
	 */
	public int showWarn(String title, String message, String[] buttons) {
		return showDialog(JOptionPane.WARNING_MESSAGE, title, message, buttons);
	}

	/**
	 * 弹出错误信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            错误信息
	 * @param buttons
	 *            按钮数组
	 * @return 用户所点击的按钮数组下标, 如果用户直接点关闭对话框则返回-1
	 */
	public int showError(String title, String message, String[] buttons) {
		return showDialog(JOptionPane.ERROR_MESSAGE, title, message, buttons);
	}

	private int showDialog(int type, String title, String message,
			String[] buttons) {
		return JOptionPane.showOptionDialog(frame, message, title,
				JOptionPane.DEFAULT_OPTION, type, null, buttons, buttons[0]);
	}

	/**
	 * 弹出询问信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            询问信息
	 */
	public void showAsk(String title, String message) {
		showDialog(JOptionPane.QUESTION_MESSAGE, title, message);
	}

	/**
	 * 弹出普通信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            普通信息
	 */
	public void showInfo(String title, String message) {
		showDialog(JOptionPane.INFORMATION_MESSAGE, title, message);
	}

	/**
	 * 弹出警告信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            警告信息
	 */
	public void showWarn(String title, String message) {
		showDialog(JOptionPane.WARNING_MESSAGE, title, message);
	}

	/**
	 * 弹出错误信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param exception
	 *            错误信息
	 */
	public void showError(String title, Exception exception) {
		StringWriter out = new StringWriter();
		exception.printStackTrace(new PrintWriter(out));
		String message = out.getBuffer().toString();
		showDialog(JOptionPane.ERROR_MESSAGE, title, message);
	}

	/**
	 * 弹出错误信息对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            错误信息
	 */
	public void showError(String title, String message) {
		showDialog(JOptionPane.ERROR_MESSAGE, title, message);
	}

	private void showDialog(int type, String title, String message) {
		JOptionPane.showMessageDialog(frame, message, title, type);
	}

	/**
	 * 显示输入对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            输入提示
	 * @return 输入的内容
	 */
	public String showInput(String title, String message) {
		return JOptionPane.showInputDialog(frame, message, title,
				JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * 显示文件夹选择对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            选择提示
	 * @param button
	 *            选择按钮
	 * @param initDir
	 *            初始选中目录
	 * @return 选择的文件
	 */
	public File showDir(String title, final String message, String button,
			File initDir) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return true;
			}

			public String getDescription() {
				return message;
			}
		});
		if (initDir != null) {
			chooser.setCurrentDirectory(initDir.getParentFile());
			chooser.setCurrentDirectory(initDir);
		}
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle(title);
		chooser.setApproveButtonText(button);
		chooser.showOpenDialog(frame);
		return chooser.getSelectedFile();
	}

	/**
	 * 显示文件选择对话框
	 *
	 * @param title
	 *            对话框标题
	 * @param message
	 *            文件类型提示
	 * @param button
	 *            按钮
	 * @param initFile
	 *            初始选中文件
	 * @param suffix
	 *            允许选择的文件后缀
	 * @return 选择的文件
	 */
	public File showFile(String title, final String message, String button,
			File initFile, final String suffix) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				if (suffix != null)
					return f.getName().toLowerCase().endsWith(suffix);
				return true;
			}

			public String getDescription() {
				if (suffix != null)
					return message + "(*" + suffix + ")";
				return message;
			}
		});
		if (initFile != null) {
			chooser.setCurrentDirectory(initFile.getParentFile());
			chooser.setSelectedFile(initFile);
		}
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle(title);
		chooser.setApproveButtonText(button);
		chooser.showOpenDialog(frame);
		return chooser.getSelectedFile();
	}

	/**
	 * 关闭UI窗体
	 */
	public void close() {
		frame.dispose();
	}

}
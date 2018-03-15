package org.hbhk.aili.client.core.widget.statusbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * 状态栏控件
 * 功能：显示消息、时间、进度条、图片
 * 可设置：消息、进度条、图片
 * 时间显示可供系统计时器，方便用户看时间
 *
 */
public class JStatusBar extends JPanel implements EventListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5406422327462313328L;
	private JLabel message;   //消息
	private JLabel message1;   //消息
	private JLabel timerJLabel;   //时间
	private JProgressBar progressBar;   //进度条
	private JLabel progressPercent;   //进度条百分比
	private JLabel s3 ;
	private JLabel imageIcon;   //图片
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:</p>
	 *
	 */
	public JStatusBar() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(15, 23));
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(new JLabel(new AngledLinesWindowsCornerIcon()),BorderLayout.SOUTH);
		rightPanel.setOpaque(false);
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		message = new JLabel();   //消息
		message.setSize(new Dimension(15, 10));
		message1 = new JLabel();   //消息
		message1.setSize(new Dimension(15, 10));
		JLabel s1 = new JLabel(new SeperatorIcon());
		
		timerJLabel = new JLabel();   //时间
		JLabel s2 = new JLabel(new SeperatorIcon());
		
		progressBar = new JProgressBar();   //进度条
		progressBar.setVisible(false);
		progressPercent = new JLabel();   //进度条百分比			
		progressPercent.setVisible(false);
		s3 = new JLabel(new SeperatorIcon());
		s3.setVisible(false);
		
		imageIcon = new JLabel();   //图片
		imageIcon.setVisible(false);
		
		//放入控件
		
		
		leftPanel.add(settingTimer());
		leftPanel.add(s2);
		leftPanel.add(progressBar);
		leftPanel.add(progressPercent);
		leftPanel.add(s3);
		leftPanel.add(imageIcon);
		leftPanel.add(s1);
		leftPanel.add(message);
		leftPanel.add(s1);
		leftPanel.add(message1);

		add(leftPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		setBackground(SystemColor.control);
		
	}
	
	/**
	 * 设置消息
	 * @param message
	 */
	public void setMessage(int index, String message) {
		if (index==1) {
			this.message.setText(message);
		}else{
			this.message1.setText(message);
		}
	}
	
	/**
	 * 得到消息
	 * @return
	 */
	public String getMessage(int index) {
		if (index==1) {
			return message.getText();
		}else{
			return message1.getText();
		}
		
	}
	
	/**
	 * 设置时间
	 * @return
	 */
	private JLabel settingTimer() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//实时获取时间
		TimerTask task = new TimerTask() {
             public void run() {              
	             String sdate;
	             sdate = sdf.format(new Date());
	             timerJLabel.setText(sdate);
             }
        };
        Timer timer = new Timer();
        try {
			timer.scheduleAtFixedRate(task, sdf.parse("1970-01-02 00:00:00"), 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timerJLabel;
	}
	
	/**
	 * 设置进度条
	 * @param progressBar
	 */
	public void setProgress(int n) {
		if(n>-1&&n<101){
			s3.setVisible(true);
			progressBar.setVisible(true);
			progressPercent.setVisible(true);
			progressBar.setValue(n);
			progressPercent.setText(n+"%");
		}
	}
	
	/**
	 * 设置图片
	 * @param imageIcon
	 */
	public void setImageIcon(Icon icon) {
		if(icon != null){
			imageIcon.setVisible(true);
			imageIcon.setIcon(icon);
		}
	}
	
	/**
	 * 设置图片显示
	 * @param aFlag
	 */
	public void setImageIconVisible(boolean aFlag) {		
		imageIcon.setVisible(aFlag);
	}
	
}
/**
 * 
 * @Classname:AngledLinesWindowsCornerIcon
 * @Description:
 * 内部类，定义了图标
 * @date 2011-6-11  下午03:04:18
 *
 */
class AngledLinesWindowsCornerIcon implements Icon {
	private static final Color WHITE_LINE_COLOR = new Color(255, 255, 255);

	private static final Color GRAY_LINE_COLOR = new Color(172, 168, 153);
	private static final int WIDTH = 13;

	private static final int HEIGHT = 13;
	/**
	 * 
	 * <p>Title:getIconHeight
	 * <p>Description:</p>
	 * @return
	 * @see javax.swing.Icon#getIconHeight()
	 */
	public int getIconHeight() {
		return WIDTH;
	}
	/**
	 * 
	 * <p>Title:getIconWidth
	 * <p>Description:</p>
	 * @return
	 * @see javax.swing.Icon#getIconWidth()
	 */
	public int getIconWidth() {
		return HEIGHT;
	}
	/**
	 * 
	 * <p>Title:paintIcon
	 * <p>Description:画图标</p>
	 * @param c
	 * @param g
	 * @param x
	 * @param y
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {

		g.setColor(WHITE_LINE_COLOR);
		g.drawLine(0, 12, 12, 0);
		g.drawLine(5, 12, 12, 5);
		g.drawLine(10, 12, 12, 10);

		g.setColor(GRAY_LINE_COLOR);
		g.drawLine(1, 12, 12, 1);
		g.drawLine(2, 12, 12, 2);
		g.drawLine(3, 12, 12, 3);

		g.drawLine(6, 12, 12, 6);
		g.drawLine(7, 12, 12, 7);
		g.drawLine(8, 12, 12, 8);

		g.drawLine(11, 12, 12, 11);
		g.drawLine(12, 12, 12, 12);

	}
}

class SeperatorIcon implements Icon {

	private static final Color GRAY_LINE_COLOR = new Color(172, 168, 153);
	private static final int WIDTH = 16;

	private static final int HEIGHT = 16;

	public int getIconHeight() {
		return WIDTH;
	}

	public int getIconWidth() {
		return HEIGHT;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(GRAY_LINE_COLOR);		
		g.draw3DRect(0, 0, 0, WIDTH, true);
	}
}


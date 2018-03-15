package org.hbhk.test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AiliJDialog extends JDialog {
	private static final long serialVersionUID = 8974931001893875649L;
	private Point pressedPoint;
	// 顶部部分
	private JPanel topPanel;
	// 内容部分
	private Component container;

	public AiliJDialog(Frame owner, String title, boolean modal) {
	    super(owner,title, modal);
	    topPanel = new JPanel();
		setUndecorated(true);
		topPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pressedPoint = e.getPoint();
				System.out.println("11111");
			}
		});
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// 获取当前坐标
				Point point = e.getPoint();
				// 获取窗体坐标
				Point locationPoint = getLocation();
				// 计算移动后的新坐标
				int x = locationPoint.x + point.x - pressedPoint.x;
				int y = locationPoint.y + point.y - pressedPoint.y;
				// 改变窗体位置
				setLocation(x, y);
			}
		});
		//this.setLayout(new FlowLayout());
		topPanel.add(new JLabel(title));
		topPanel.add(new JButton("最小化"));
		topPanel.add(new JButton("关闭"));
		topPanel.setBackground(Color.blue);
		topPanel.setVisible(true);
		// 添加到对话框中
		this.add(topPanel);
		this.add(container);
	    
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public void addContainer(Container container) {
		this.container = container;
	}

	

}

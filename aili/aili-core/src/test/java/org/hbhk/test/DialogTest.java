package org.hbhk.test;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DialogTest extends JFrame implements ActionListener {

	private AiliJDialog d1;
	private Point pressedPoint;
	public DialogTest() {
		init();
	}

	private void init() {
		this.getContentPane().add(initPanel());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 400);
	}

	private Component initPanel() {
		JPanel panel = new JPanel();
		JButton b = new JButton("Dialog1");
		b.addActionListener(this);
		panel.add(b);
		return panel;
	}

	public static void main(String[] args) {
		new DialogTest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Dialog1")) {
			JButton b = new JButton("Dialog2");
			b.setLocation(10, 10);
			b.setSize(100, 30);
			b.addActionListener(this);
			d1 = new AiliJDialog(this, "Im dialog one!", true);
			// 获取按下的时的坐标点
			b.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					pressedPoint = e.getPoint();
				}
			});
			b.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Point point = e.getPoint();// 获取当前坐标
					Point locationPoint = d1.getLocation();// 获取窗体坐标
					int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
					int y = locationPoint.y + point.y - pressedPoint.y;
					d1.setLocation(x, y);// 改变窗体位置
				}
			});
			d1.setLocationRelativeTo(this);
			d1.addContainer(b);
			d1.setSize(200, 100);
			d1.setVisible(true);
		} 
	}

}

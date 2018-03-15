package org.hbhk.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ControlFormStatus extends JFrame {

	/**

     * 

     */

	private static final long serialVersionUID = 3916932450920717576L;

	private JPanel contentPane;

	private Point pressedPoint;

	/**
	 * 
	 * Launch the application.
	 */

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					ControlFormStatus frame = new ControlFormStatus();

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	/**
	 * 
	 * Create the frame.
	 * 
	 * @throws MalformedURLException
	 */

	public ControlFormStatus() throws MalformedURLException {

		// ----this is the foundmental frame creat of the project----//

		setUndecorated(true); // 阻止窗体采用系统本身的修饰

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 800, 600); // location and size

		contentPane = new JPanel();

		contentPane.setBorder(null);

		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		// ----this is the foundmental frame creat of the project----//

		BackgroundPanel topPanel = new BackgroundPanel(); //

		topPanel.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {

				do_topPanel_mouseDragged(e);

			}

		}); // 移动功能

		topPanel.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {

				do_topPanel_mousePressed(e);

			}

		}); // 获取按下的时的坐标点

		// /新建一个panel
		File file = new File(
				"E:/hbhk/svn/aili-core/src/test/resources/frameTop.png");

		Image centerImage = new ImageIcon(file.toURL()).getImage(); // 背景图片路径的设置

		Dimension dimension = new Dimension(centerImage.getWidth(this),

		centerImage.getHeight(this));

		System.out.println(dimension.height + "---" + dimension.width);

		// / int getHeight(ImageObserver observer)

		// / 确定图像的高度。

		topPanel.setPreferredSize(dimension);

		topPanel.setImage(centerImage);

		contentPane.add(topPanel, BorderLayout.NORTH); // 添加到底层模版上

		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5)); // 标题栏的大小随着frame变化

		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(60, 22));

		panel.setOpaque(true);

		/*
		 * 
		 * 如果为 true，则该组件绘制其边界内的所有像素。否则该组件可能不绘制部分或所有像素，从而允许其底层像素透视出来。
		 */

		topPanel.add(panel);

		panel.setLayout(new GridLayout(1, 0, 0, 0)); // 布局管理器

		/*
		 * 
		 * GridLayout(int rows, int cols, int hgap, int vgap)
		 * 
		 * 创建具有指定行数和列数的网格布局。
		 */

		JButton button = new JButton("");

		// button.setRolloverIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/minBH.jpg")));

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				do_button_itemStateChanged(e);

			}

		});

		button.setFocusPainted(false);// 取消焦点绘制

		button.setBorderPainted(false);// 取消边框绘制

		button.setContentAreaFilled(false);// 取消内容绘制

		// button.setIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/minB.jpg")));

		panel.add(button);

		JToggleButton button_1 = new JToggleButton("");

		button_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				do_button_1_itemStateChanged(e);

			}

		});

		// button_1.setRolloverIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/maxBH.jpg")));
		//
		// button_1.setSelectedIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/maxBH.jpg"))); //是否已经选过maxBH
		//
		// button_1.setIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/maxB.jpg")));

		button_1.setContentAreaFilled(false);

		button_1.setBorderPainted(false);

		button_1.setFocusPainted(false);

		panel.add(button_1);

		JButton button_2 = new JButton("");

		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				do_button_2_actionPerformed(e);

			}

		});
		//
		// button_2.setRolloverIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/closeBH.jpg")));

		button_2.setFocusPainted(false);

		button_2.setContentAreaFilled(false);

		button_2.setBorderPainted(false);

		// button_2.setIcon(new ImageIcon(ControlFormStatus.class
		//
		// .getResource("/edu/test/swing/lzw/closeB.jpg")));

		panel.add(button_2);

		BackgroundPanel backgroundPanel_1 = new BackgroundPanel();

		// Image topImage = new ImageIcon(getClass()
		//
		// .getResource("frameCenter.png")).getImage();
		//
		// backgroundPanel_1.setImage(topImage);

		contentPane.add(backgroundPanel_1, BorderLayout.CENTER);

	}

	protected void do_button_itemStateChanged(ActionEvent e) {

		setExtendedState(JFrame.ICONIFIED);// min

	}

	protected void do_button_2_actionPerformed(ActionEvent e) {

		dispose();// 销毁窗体

	}

	protected void do_button_1_itemStateChanged(ItemEvent e) {

		if (e.getStateChange() == ItemEvent.SELECTED) {

			setExtendedState(JFrame.MAXIMIZED_BOTH);

		} else {

			setExtendedState(JFrame.NORMAL);

		}

	}

	protected void do_topPanel_mousePressed(MouseEvent e) {

		pressedPoint = e.getPoint();

	}

	protected void do_topPanel_mouseDragged(MouseEvent e) {

		Point point = e.getPoint();// 获取当前坐标

		Point locationPoint = getLocation();// 获取窗体坐标

		int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标

		int y = locationPoint.y + point.y - pressedPoint.y;

		setLocation(x, y);// 改变窗体位置

	}
}

package org.hbhk.aili.jms.server.main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.hbhk.aili.jms.server.listener.BusinessListener;
import org.hbhk.aili.jms.server.listener.MessageListenerContainer;

public class JmsLogUi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513077621719301773L;

	private GridBagLayout gridbag = new GridBagLayout();
	private JTextArea text = new JTextArea(5, 25);;
	JScrollPane sp = new JScrollPane(text);
	private GridBagConstraints c = new GridBagConstraints();
	public static Map<String, Queue<String>> logMap = new ConcurrentHashMap<String, Queue<String>>();

	private BusinessListener listener;

	private ExecutorService service = Executors.newFixedThreadPool(2);
	int height = 10;
	Point p = new Point();

	public JmsLogUi() {
		this.setVisible(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(1, 1, 5, 5));
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory.createTitledBorder("MQ处理信息"));
		text.setTabSize(10);
		text.setFont(new Font("标楷体", Font.BOLD, 10));
		text.setLineWrap(true);// 激活自动换行功能
		text.setWrapStyleWord(true);// 激活断行不断字功能
		p1.add(sp);// 将JTextArea放入JScrollPane中，这样就能利用滚动的效果看到输入超过JTextArea高度的
		this.add(p1);
		this.setResizable(false);
		service.execute(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if(listener!=null){
						Queue<String> logs = logMap.get(listener.toString());
						try {
							if (logs != null && logs.size() > 0) {
								text.append(logs.remove());
								p.setLocation(0, text.getLineCount() * height);
								sp.getViewport().setViewPosition(p);
								TimeUnit.MILLISECONDS.sleep(10);
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
						JOptionPane.YES_NO_OPTION);
				if (a ==JOptionPane.OK_OPTION) {
					//System.exit(0); // 关闭
					dispose();
					MessageListenerContainer.listMap.get(listener.toString()).stop();
				}
			}
		});

	}

	public void addComp(JComponent cont, JComponent comp, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		c.gridheight = 1;
		c.gridwidth = 1;
		gridbag.setConstraints(comp, c);
		c.fill = GridBagConstraints.WEST;
		c.anchor = GridBagConstraints.WEST;
		cont.add(comp);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JmsLogUi();
			}
		});

	}

	public BusinessListener getListener() {
		return listener;
	}

	public void setListener(BusinessListener listener) {
		this.listener = listener;
	}

}

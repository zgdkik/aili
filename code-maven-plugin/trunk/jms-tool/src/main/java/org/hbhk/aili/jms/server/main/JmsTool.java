package org.hbhk.aili.jms.server.main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import javax.jms.ConnectionFactory;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.jms.server.factory.MQConnectionFactory;
import org.hbhk.aili.jms.server.listener.BusinessListener;
import org.hbhk.aili.jms.server.listener.MessageListenerContainer;
import org.hbhk.aili.jms.server.ui.AiliTextField;

public class JmsTool extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513077621719301773L;
	// 接收消息
	private JPanel receivePanel = new JPanel();

	// 发送消息
	private JPanel sendPanel = new JPanel();
	// 操作区域
	private JPanel optPanel = new JPanel();

	private AiliTextField tf1 = new AiliTextField("地址:", 20);
	private AiliTextField tf2 = new AiliTextField("端口:", 20);
	private AiliTextField tf3 = new AiliTextField("队列管理器:", 18);
	private AiliTextField tf4 = new AiliTextField("队列:", 20);
	private AiliTextField tf5 = new AiliTextField("CCSID:", 21);

	private AiliTextField tf6 = new AiliTextField("通道:", 20);

	private AiliTextField tf7 = new AiliTextField("消息选择器:", 20, false);

	private AiliTextField stf1 = new AiliTextField("地址:", 20);
	private AiliTextField stf2 = new AiliTextField("端口:", 20);
	private AiliTextField stf3 = new AiliTextField("队列管理器:", 16);
	private AiliTextField stf4 = new AiliTextField("队列:", 20);
	private AiliTextField stf5 = new AiliTextField("CCSID:", 19);
	private AiliTextField stf6 = new AiliTextField("通道:", 20);
	private JButton btn1 = new JButton("确定");

	private GridBagLayout gridbag = new GridBagLayout();

	private GridBagConstraints c = new GridBagConstraints();

	private Log log = LogFactory.getLog(JmsTool.class);

	JRadioButton radioButton1 = new JRadioButton("IBM MQ");
	JRadioButton radioButton2 = new JRadioButton("ActiveMQ");

	JRadioButton sradioButton1 = new JRadioButton("IBM MQ");
	JRadioButton sradioButton2 = new JRadioButton("ActiveMQ");
	ButtonGroup group = new ButtonGroup();
	ButtonGroup group1 = new ButtonGroup();

	private JPanel p1 = new JPanel();
	//发送JMS消息
	private JPanel p2 = new JPanel();

	private AiliTextField sstf1 = new AiliTextField("地址:", 20);
	private AiliTextField sstf2 = new AiliTextField("端口:", 20);
	private AiliTextField sstf3 = new AiliTextField("队列管理器:", 16);
	private AiliTextField sstf4 = new AiliTextField("队列:", 20);
	private AiliTextField sstf5 = new AiliTextField("CCSID:", 19);
	private AiliTextField sstf6 = new AiliTextField("通道:", 20);
	private JButton sbtn1 = new JButton("发送");

	public JmsTool() {
		this.setVisible(true);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1.setLayout(new GridLayout(3, 3, 5, 5));
		p1.setSize(800, 600);
		
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP);
		// 加入到窗口中
		this.add(tabPane);
		tabPane.add("转发JMS消息", p1);
		tabPane.add("发送JMS消息", p2);
		receivePanel.setLayout(gridbag);
		receivePanel.setBorder(BorderFactory.createTitledBorder("接收MQ信息"));
		addComp(receivePanel, tf1, 0, 1);
		addComp(receivePanel, tf2, 1, 1);
		addComp(receivePanel, tf3, 0, 2);
		addComp(receivePanel, tf4, 1, 2);
		addComp(receivePanel, tf5, 0, 3);
		addComp(receivePanel, tf6, 1, 3);
		addComp(receivePanel, tf7, 0, 4);

		p1.add(receivePanel);
		sendPanel.setLayout(gridbag);
		sendPanel.setBorder(BorderFactory.createTitledBorder("发送MQ信息"));
		addComp(sendPanel, stf1, 0, 1);
		addComp(sendPanel, stf2, 1, 1);
		addComp(sendPanel, stf3, 0, 2);
		addComp(sendPanel, stf4, 1, 2);
		addComp(sendPanel, stf5, 0, 3);
		addComp(sendPanel, stf6, 1, 3);
		p1.add(sendPanel);
		optPanel.setLayout(gridbag);
		addComp(optPanel, btn1, 0, 0);
		p1.add(optPanel);
		this.setResizable(false);
		p2.setLayout(new GridLayout(2, 3, 5, 5));
		p2.setSize(800, 600);
		p2.setLayout(gridbag);
		addComp(p2, sstf1, 0, 1);
		addComp(p2, sstf2, 1, 1);
		addComp(p2, sstf3, 0, 2);
		addComp(p2, sstf4, 1, 2);
		addComp(p2, sstf5, 0, 3);
		addComp(p2, sstf6, 1, 3);
		initButtonGroup();
		intiListener();
		this.setTitle("JMS消息推送工具(hbhk)");
	}

	public void intiListener() {
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<AiliTextField> list = AiliTextField.validateList;
					for (AiliTextField atf : list) {
						try {
							atf.validateError();
						} catch (Exception e2) {
							return;
						}
					}
					log.info("开始监听接收中...");
					ConnectionFactory rcf = null;
					ConnectionFactory scf = null;
					String type = "1";
					String stype = "1";
					String messageSelector = "";
					String rdestinationName = tf4.getText();
					String sdestinationName = stf4.getText();
					if (group.getSelection().getActionCommand().equals("2")) {
						type = "2";
					}
					if (group1.getSelection().getActionCommand().equals("2")) {
						stype = "2";
					}
					String address = tf1.getText();
					String saddress = stf1.getText();
					if ("2".equals(type)) {
						rcf = MQConnectionFactory
								.getActiveMQConnectionFactory(address);
					} else {
						String qmn = tf3.getText();
						String hostName = tf1.getText();
						int port = Integer.parseInt(tf2.getText());
						int ccsid = Integer.parseInt(tf5.getText());
						String channelName = tf6.getText();
						rcf = MQConnectionFactory.getIbmMQConnectionFactory(
								qmn, hostName, port, ccsid, channelName);
					}
					if ("2".equals(stype)) {
						scf = MQConnectionFactory
								.getActiveMQConnectionFactory(saddress);
					} else {
						String sqmn = stf3.getText();
						String shostName = stf1.getText();
						int sport = Integer.parseInt(stf2.getText());
						int sccsid = Integer.parseInt(stf5.getText());
						String schannelName = stf6.getText();
						scf = MQConnectionFactory.getIbmMQConnectionFactory(
								sqmn, shostName, sport, sccsid, schannelName);
					}
					BusinessListener listener = new BusinessListener();
					JmsLogUi.logMap.put(listener.toString(),
							new LinkedBlockingDeque<String>());

					listener.setCf(scf);
					listener.setSendDestinationName(sdestinationName);
					MessageListenerContainer lc = new MessageListenerContainer();
					lc.setListener(listener);
					lc.startMessageListener(rcf, listener, messageSelector,
							rdestinationName);
					JmsLogUi jmsLogUi = new JmsLogUi();
					jmsLogUi.setListener(listener);
				} catch (Exception e2) {
					String str = ExceptionUtils.getFullStackTrace(e2);
					if (str.length() > 200) {
						str = str.substring(0, 199);
					}
					JOptionPane.showMessageDialog(null, str, "错误信息",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		radioButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tf1.setVisible(true);
				tf2.setVisible(true);
				tf3.setVisible(true);
				tf4.setVisible(true);
				tf5.setVisible(true);
				tf6.setVisible(true);
			}
		});
		radioButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tf1.setVisible(true);
				tf2.setVisible(false);
				tf3.setVisible(false);
				tf5.setVisible(false);
				tf4.setVisible(true);
				tf6.setVisible(false);
			}
		});

		sradioButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stf1.setVisible(true);
				stf2.setVisible(true);
				stf3.setVisible(true);
				stf4.setVisible(true);
				stf5.setVisible(true);
				stf6.setVisible(true);
			}
		});
		sradioButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stf1.setVisible(true);
				stf2.setVisible(false);
				stf3.setVisible(false);
				stf4.setVisible(true);
				stf5.setVisible(false);
				stf6.setVisible(false);
			}
		});
	}

	public void initButtonGroup() {
		radioButton1.setSelected(true);
		radioButton1.setActionCommand("1");
		radioButton2.setActionCommand("2");
		group.add(radioButton1);
		group.add(radioButton2);

		addComp(receivePanel, radioButton1, 0, 0);
		addComp(receivePanel, radioButton2, 1, 0);
		radioButton1.setSelected(true);

		group1.add(sradioButton1);
		group1.add(sradioButton2);
		sradioButton1.setSelected(true);
		sradioButton1.setActionCommand("1");
		sradioButton2.setActionCommand("2");
		addComp(sendPanel, sradioButton1, 0, 0);
		addComp(sendPanel, sradioButton2, 1, 0);
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
				new JmsTool();
			}
		});

	}

}

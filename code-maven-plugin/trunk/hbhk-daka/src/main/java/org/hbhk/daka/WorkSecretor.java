package org.hbhk.daka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class WorkSecretor {

	private ExecutorService executorService = Executors.newFixedThreadPool(5);
	JButton btn1 = new JButton("开始");
	JCheckBox cb1 = new JCheckBox("是否关机");
	ButtonGroup group = new ButtonGroup();
	DateChooserJButton textField;
	Thread  runnable = null;
	public void start(final String url) {
		final String title = "工作小秘书";
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		textField = new DateChooserJButton();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JPanel webBrowserPanel = new JPanel(new BorderLayout());
				final JWebBrowser webBrowser = new JWebBrowser();
				webBrowser.navigate(url);
				webBrowser.setButtonBarVisible(false);
				webBrowser.setMenuBarVisible(false);
				webBrowser.setBarsVisible(false);
				webBrowser.setStatusBarVisible(false);
				webBrowser.setJavascriptEnabled(true);
				webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
				frame.getContentPane()
						.add(webBrowserPanel, BorderLayout.CENTER);
				final JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				panel.setSize(600, 100);
				JRadioButton rb1 = new JRadioButton("上班");
				rb1.setActionCommand("S");
				JRadioButton rb2 = new JRadioButton("下班");
				rb2.setActionCommand("X");
				rb2.setSelected(true);
				group.add(rb1);
				group.add(rb2);
				panel.add(rb1);
				panel.add(rb2);
				panel.add(btn1);
				panel.add(cb1);
				panel.add(textField);
				frame.getContentPane().add(panel, BorderLayout.SOUTH);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.setSize(600, 500);
				frame.setVisible(true);

				btn1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(runnable!=null){
							runnable.interrupt();
						};
						runnable = new Thread() {
							@Override
							public void run() {
								SwingWorker<JWebBrowser, Void> worker = new SwingWorker<JWebBrowser, Void>() {
									@Override
									protected JWebBrowser doInBackground()
											throws Exception {
										System.out.println("开始执行后台方法...");
										return webBrowser;// 返回取到的数据
									}

									@Override
									protected void done() {
										try {
											String str = "document.getElementById('btn2').click();";
											if(group.getSelection().getActionCommand().equals("S")){
												 str = "document.getElementById('btn1').click();";
											}
											System.out.println(str + ":"+ cb1.isSelected());
											get().executeJavascript(str);
											// 关机
											if (cb1.isSelected()) {
												Runtime.getRuntime().exec(
														"shutdown -s");
											}
											System.out.println("后台方法运行结束");
										} catch (Exception ex) {
											ex.printStackTrace();
										}
									}
								};
								boolean flag = true ;
								Long date =  textField.getDate().getTime();
								while(flag){
									try {
										TimeUnit.SECONDS.sleep(1);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									if((System.currentTimeMillis()-5000)>date 
											&& date < (System.currentTimeMillis()+5000)){
										worker.execute();
										flag = false;
									}
								}
							}
						};
						executorService.execute(runnable);
					}
				});

			}
		});
		NativeInterface.runEventPump();

	}

	public static void main(String[] args) throws InterruptedException,
			InvocationTargetException {
		String url = "http://hr.deppon.com:9080/eos-default/dip.integrateorg.oaAttence.oaAttence.flow";
		url ="http://localhost:8085/metertick-map-web/map/searchNetwork?provinceCode=shandongsheng&dictCode=530&address=%E5%B1%B1%E4%B8%9C%E7%9C%81";
		WorkSecretor workSecretor = new WorkSecretor();
		workSecretor.start(url);
	}
}
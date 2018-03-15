package org.hbhk.daka;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationParameters;

public class MapTest {

	JButton btn1 = new JButton("确定");
	
	//DateChooserJButton textField;
	public void start(final String url) {
		final String title = "网点地图";
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		//textField = new DateChooserJButton();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JPanel webBrowserPanel = new JPanel(new BorderLayout());
				final JWebBrowser webBrowser = new JWebBrowser();
				WebBrowserNavigationParameters parameters = new WebBrowserNavigationParameters();
			    Map<String, String> headers = new HashMap<String, String>(); 
			    String access_token = Base64Util.encode("dadaowl,006907");
			    headers.put("access_token", access_token);
				parameters.setHeaders(headers);
				webBrowser.navigate(url, parameters);
//				URL ssoUrl = null;
//				try {
//					ssoUrl = new URL(url);
//				} catch (MalformedURLException e1) {
//				}
//				System.out.println("host:"+ssoUrl.getHost().replace("www.", "."));
//				String access_token = Base64Util.encode("dadaowl,006907");
//				String cookie = "access_token" + "="+access_token+"; path=/; domain="+ssoUrl.getHost().replace("www.", ".");
//				System.out.println("cookie:"+cookie);
//			    JWebBrowser.setCookie(ssoUrl.getHost(),cookie);
//				webBrowser.navigate(url);
				webBrowser.setButtonBarVisible(false);
				webBrowser.setMenuBarVisible(false);
				webBrowser.setBarsVisible(false);
				webBrowser.setStatusBarVisible(false);
				webBrowser.setJavascriptEnabled(true);
				webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
					
				      public void commandReceived(WebBrowserCommandEvent e) {   
				        String command = e.getCommand(); 
				        System.out.println(command);
				      }
				    });  
				webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
				frame.getContentPane()
						.add(webBrowserPanel, BorderLayout.CENTER);
				final JPanel panel = new JPanel();
				panel.setLayout(new FlowLayout());
				panel.setSize(600, 100);
				panel.add(btn1);
				frame.getContentPane().add(panel, BorderLayout.SOUTH);
				frame.setLocationRelativeTo(null);
				//frame.setResizable(false);
				frame.setSize(600, 500);
				frame.setVisible(true);

				btn1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
										 str = "document.getElementById('btn1').click();";
										 str=" return strtest();";
									//get().executeJavascript(str);
									String result = (String) get().executeJavascriptWithResult(str);
									// 关机
									System.out.println("后台方法运行结束:"+result);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
						};
						worker.execute();
					}
				});

			}
		});
		NativeInterface.runEventPump();

	}

	public static void main(String[] args) throws InterruptedException,
			InvocationTargetException, UnsupportedEncodingException {
		String url = "http://192.168.50.3:8081/platform";
		//url ="https://www.baidu.com";
		System.out.println(url);
		MapTest workSecretor = new MapTest();
		workSecretor.start(url);
	}
}
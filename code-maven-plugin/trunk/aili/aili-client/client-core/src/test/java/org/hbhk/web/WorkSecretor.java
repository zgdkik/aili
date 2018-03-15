package org.hbhk.web;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class WorkSecretor extends JPanel {
	private static final long serialVersionUID = 1L;

	private JPanel webBrowserPanel;
	  

	private static JWebBrowser webBrowser;

	public WorkSecretor(String url) {
		super(new BorderLayout());
		webBrowserPanel = new JPanel(new BorderLayout());
		webBrowserPanel.setSize(400, 500);
		webBrowser = new JWebBrowser();
		webBrowser.navigate(url);
		webBrowser.setButtonBarVisible(false);
		webBrowser.setMenuBarVisible(false);
		webBrowser.setBarsVisible(false);
		webBrowser.setStatusBarVisible(false);
		webBrowser.setJavascriptEnabled(true);
		webBrowser.addWebBrowserListener(new WebBrowserListener() {

			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				System.out.println("windowWillOpen");
			}

			@Override
			public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
				System.out.println("windowOpening");
			}

			@Override
			public void windowClosing(WebBrowserEvent arg0) {
				System.out.println("windowClosing");
			}

			@Override
			public void titleChanged(WebBrowserEvent arg0) {
				System.out.println("titleChanged");
			}

			@Override
			public void statusChanged(WebBrowserEvent arg0) {
				System.out.println("statusChanged");
			}

			@Override
			public void locationChanging(WebBrowserNavigationEvent arg0) {
				System.out.println("locationChanging");
			}

			@Override
			public void locationChanged(WebBrowserNavigationEvent arg0) {
				System.out.println("locationChanged");
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				File file = new File(
						"F:/java-dev/workspaces/hbhkws00/aili/aili-client/client-core/src/test/resources/js/daka.js");
				try {
					String str = FileUtils.readFileToString(file);
					System.out.println(str);
					webBrowser.executeJavascript(str);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				System.out.println("locationChangeCanceled");
			}

			@Override
			public void loadingProgressChanged(WebBrowserEvent arg0) {
				System.out.println("loadingProgressChanged");
			}

			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				System.out.println("commandReceived");
			}
		});
		webBrowser.setSize(400, 500);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		this.setSize(400, 500);
		add(webBrowserPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws InterruptedException,
			InvocationTargetException {
		final String url = "http://hr.deppon.com:9080/eos-default/dip.integrateorg.oaAttence.oaAttence.flow";
		final String title = "工作小秘书";
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new WorkSecretor(url),
						BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.setLocationByPlatform(true);
				frame.setSize(400, 500);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();

	}
}
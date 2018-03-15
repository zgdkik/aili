package org.hbhk.aili.client.core.widget.browser.ui;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.widget.browser.action.EmbedWebBrowserListener;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
/**
 * 嵌入式浏览器主面板
 */
public class BrowserFrame extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 857839606776857630L;
	private JWebBrowser browser;
	private EmbedWebBrowserListener listener;
	/**
	 * 根据URL实例化一个浏览器面板</p>
	 */
	public BrowserFrame(URL ssoURL){
		if (!NativeInterface.isOpen()) {
			NativeInterface.open();
		}
		this.setLayout(new BorderLayout());   //布局
		this.listener = new EmbedWebBrowserListener();
		browser = new JWebBrowser(JWebBrowser.destroyOnFinalization(),
				JWebBrowser.proxyComponentHierarchy(), 
				JWebBrowser.useWebkitRuntime(),
				JWebBrowser.constrainVisibility());
		
		//设置单点登陆Cookie信息
		String keyToken = SessionContext.KEY_TOKEN;
		String cookie = keyToken + "="+ SessionContext.get(keyToken)+"; path=/; domain="+ssoURL.getHost().replace("www.", ".");
		
		JWebBrowser.setCookie(ssoURL.getHost(),cookie);

		browser.addWebBrowserListener(listener);
		browser.setVisible(true);
		browser.navigate(ssoURL.toExternalForm());   //单点登录
		browser.setBarsVisible(false);
		this.add(browser,BorderLayout.CENTER);
//		NativeInterface.runEventPump();
	}
	
	
	
	/**
	 * 打开URL地址
	 * @param url
	 * @return
	 */
	public boolean gotoURL(String url){
    	if(url!=null&&!"".equals(url)){
    		try {				
				URL replacedUrl = null;
				if(!url.startsWith("http://")){
					replacedUrl = new URL("http://"+url) ;
				}else{
					replacedUrl = new URL(url);
				}
				browser.navigate(replacedUrl.toExternalForm());
				return true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
    		
    	}
    	return false;
    }

	public JWebBrowser getBrowser() {
		return browser;
	}

	public void setBrowser(JWebBrowser browser) {
		this.browser = browser;
	}

	public EmbedWebBrowserListener getListener() {
		return listener;
	}

	public void setListener(EmbedWebBrowserListener listener) {
		this.listener = listener;
	}
	
	public static void main(String[] args) throws MalformedURLException {
		JFrame f = new JFrame();
		f.setTitle("浏览器");
		f.setSize(800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL url = new URL("http://www.baidu.com");
		f.getContentPane().add(new BrowserFrame(url));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}

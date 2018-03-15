package org.hbhk.aili.client.main.client.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hbhk.aili.client.core.core.application.IApplication;
import org.hbhk.aili.client.core.core.application.IApplicationAware;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.core.core.workbench.EditorConfig;
import org.hbhk.aili.client.core.core.workbench.IEditor;
import org.hbhk.aili.client.core.core.workbench.IPluginAware;
import org.hbhk.aili.client.core.widget.browser.ui.BrowserFrame;
import org.hbhk.aili.client.core.widget.msgbox.MsgBox;
import org.hbhk.aili.client.main.client.IMainframe;
import org.hbhk.aili.client.main.client.MenuNodeInfo;
import org.java.plugin.Plugin;
import org.limewire.cef.CefChromeBrowserManager;
import org.limewire.cef.CefCookie;
import org.limewire.cef.ChromeBrowser;
/**
 * 菜单时间触发器
 *
 */
public class OpenMenuAction extends AbstractOpenUIAction<BrowserFrame> implements IApplicationAware,IPluginAware,IMainframe {

	public static List<IEditor> openMenuList = new ArrayList<IEditor>();
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单节点信息
	 */
	MenuNodeInfo menuNodeInfo;
	/**
	 * 应用接口
	 */
	IApplication application;
	/**
	 * 插件对象
	 */
	Plugin plugin;
	/**
	 * 编辑配置
	 */
	private EditorConfig editConfig;
	/**
	 * 统一资源标识
	 */
	private String uri;
	/**
	 * 统一资源标识名称
	 */
	private String uriName;

	/**
	 * @param uriName the uriName to set
	 */
	public void setUriName(String uriName) {
		this.uriName = uriName;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public void setApplication(IApplication application) {
		 this.application=application;

	}

	@Override
	public void setMenuNodeInfo(MenuNodeInfo menuNodeInfo) {
		this.menuNodeInfo=menuNodeInfo;
	}

	@Override
	public void setPlugin(Plugin plugin) {
		this.plugin=plugin;
		
	}

	@Override
	public void openUIAction() {
	    	/**
		 * 获取本地默认的语言
		 */
		String local_language = Locale.getDefault().getLanguage();
		/**
		 * 获取本地默认的国家
		 */
		String local_country = Locale.getDefault().getCountry();
		
		String ip= "";//RemotingInfoUtils.getRemotingWebInfo(IRemoteInfo.DEFAULT_NAME).getHostName()+":"+RemotingInfoUtils.getRemotingWebInfo(IRemoteInfo.DEFAULT_NAME).getPort();
		
		/**
		 * 获取FOSS URI 地址并追加语言和国家信息，将最终的字符串赋给urlValue
		 */
		String urlValue ="";// "http://"+ip+uri.trim()
				//+"?"+LocaleConst.KEY_LOCALE_LANGUAGE+"="+local_language+"&"+LocaleConst.KEY_LOCALE_COUNTRY+"="+local_country;
		
		try {

			/**
			 * 创建CefCookie对象
			 */
			CefCookie cookie = new CefCookie();
			/**
			 * 设置cookie的path
			 */
			cookie.setCookiepath("/");
			/**
			 * 设置cookie的url
			 */
			cookie.setCookieurl(urlValue);
			/**
			 * 设置cookie的value
			 */
			cookie.setCookievalue(SessionContext.KEY_TOKEN+"="+(String)SessionContext.get(SessionContext.KEY_TOKEN));

			/**
			 * 创建ChromeBrowser对象并获取ChromeBrowser的实例
			 */
			ChromeBrowser browser = CefChromeBrowserManager.getChromeBrowser(urlValue,false,cookie);
			/**
			 * 修改默认JS处理行为
			 */
			browser.setCefV8Handler(new OpenMenuHandler(application, browser, "GUI_Proxy", "addTab"));
			/**
			 * 创建EditorConfig对象editConfig
			 */
			editConfig = new EditorConfig();
			/**
			 * 设置editConfig标题
			 */
			editConfig.setTitle(uriName);
			/**
			 * 设置editConfig插件ID
			 */
			editConfig.setPluginId("org.hbhk.aili.client.mainframe");
			/**
			 * 调用打开界面方法openUI
			 */
			IEditor editor = application.openUI(editConfig, browser);
			
			openMenuList.add(editor);
		} catch (Exception e) {
			e.printStackTrace();
			MsgBox.showError("未找到相应页面");
		}	
	}

	@Override
	public IApplication getApplication() {
		return application;	//返回application
	}

	@Override
	public Class<BrowserFrame> getOpenUIType() {
		return BrowserFrame.class;	//返回BrowserFrame.class
	}
	
	


}
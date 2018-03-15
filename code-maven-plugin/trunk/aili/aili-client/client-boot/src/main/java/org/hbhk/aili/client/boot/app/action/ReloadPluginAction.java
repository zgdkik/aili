package org.hbhk.aili.client.boot.app.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import org.java.plugin.JpfException;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.boot.ApplicationPlugin;
import org.java.plugin.standard.StandardPluginLocation;

/**
 * 重新载入插件
 */
public class ReloadPluginAction implements ActionListener {

	/**
	 * 定义应用插件对象
	 */
	ApplicationPlugin plugin;

	public ReloadPluginAction(ApplicationPlugin plugin) {
		/**
		 * 将参数plugin的值赋给属性plugin
		 */
		this.plugin = plugin;
	}

	/**
	 * 执行重新载入
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * 获取插件管理对象
		 */
		org.java.plugin.PluginManager pluginManager = plugin.getManager();
		/**
		 * 定义插件路径
		 */
		PluginLocation pluginLocation;
		try {
			/**
			 * 获取插件路径
			 */
			pluginLocation = StandardPluginLocation
					.create(new File(
							"F://FOSS_2//FOSS//new-maven-client//client//appHome//newPlugins//newSamplewaybill"));
			/**
			 * 创建插件路径数组
			 */
			PluginLocation[] pluginLocations = new StandardPluginLocation[] { (StandardPluginLocation) pluginLocation };
			/**
			 * 发布插件
			 */
			pluginManager.publishPlugins(pluginLocations);
			/**
			 * 设置当前活动的插件
			 */
			pluginManager
					.activatePlugin("com.deppon.foss.module.newSamplewaybill");
			pluginManager.activatePlugin("com.deppon.foss.module.mainframe");

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (JpfException e4) {
			e4.printStackTrace();
		}

	}
}
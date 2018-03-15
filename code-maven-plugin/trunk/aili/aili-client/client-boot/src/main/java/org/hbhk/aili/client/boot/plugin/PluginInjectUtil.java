package org.hbhk.aili.client.boot.plugin;

import java.util.ArrayList;
import java.util.List;

import org.hbhk.aili.client.boot.app.Application;
import org.java.plugin.Plugin;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;

/**
 * plugin注入帮助类
 */
public class PluginInjectUtil {

	public static void inject(Application app) {
		/**
		 * 根据参数app获取插件对象appPlugin
		 */
		Plugin appPlugin = app.getApplicationPlugin();
		/**
		 * 根据插件对象获取扩展点对象
		 */
		ExtensionPoint autoRunExtPoint = appPlugin
				.getManager()
				.getRegistry()
				.getExtensionPoint(appPlugin.getDescriptor().getId(),
						"plugins-all");
		/**
		 * 创建一个Extension类型的List集合
		 */
		List<Extension> extensions = new ArrayList<Extension>(
				autoRunExtPoint.getConnectedExtensions());
		/**
		 * 判断extensions的size是否是0，如果是，则返回
		 */
		if (extensions.size() == 0) {
			return;
		}
	}
}
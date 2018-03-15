package org.hbhk.aili.client.core.core.workbench;

import org.java.plugin.Plugin;
/**
 *Plugin对象的注入接口。一帮用在某个扩点需要获取当前插件对象的时候就需要实现此类。
 *如果再某个插件扩展点上，实现了此接口，那么框架会把他的插件对象设置给实现类。
 */
public interface IPluginAware {
	/**
	 * 
	 * <p>Title: setPlugin</p>
	 * <p>Description: 向action注册一个所属的pluginID</p>
	 * @param plugin 插件
	 */
	void setPlugin(Plugin plugin);
}

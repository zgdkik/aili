package org.hbhk.aili.client.core.core.workbench;
public class EditorConfig extends WindowConfig {
	/**
	 * 
	 * <p>Title: EditorConfig</p>
	 * <p>Description: 默认构造方法</p>
	 */
	public EditorConfig() {
		super();
	}

	/**
	 * 
	 * <p>Title: EditorConfig</p>
	 * <p>Description: 构造方法</p>
	 * @param pluginId 插件ID
	 * @param title 标题
	 * @param icon 图标
	 */
	public EditorConfig(String pluginId, String title, String icon) {
		super(pluginId, title, icon);
	}

	/**
	 * 
	 * <p>Title: EditorConfig</p>
	 * <p>Description: 构造方法</p>
	 * @param pluginId 插件ID
	 * @param title 标题
	 */
	public EditorConfig(String pluginId, String title) {
		super(pluginId, title);
	}

	/**
	 * 
	 * <p>Title: EditorConfig</p>
	 * <p>Description: 构造方法</p>
	 * @param pluginId 插件ID
	 */
	public EditorConfig(String pluginId) {
		super(pluginId);
	}
}
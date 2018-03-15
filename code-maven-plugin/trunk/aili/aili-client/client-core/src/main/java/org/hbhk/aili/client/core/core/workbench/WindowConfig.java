package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *  窗口配置信息描述类。此类保存了对象的一些配置信息。
 *  比如窗口标题，窗口图标等。
 */
public class WindowConfig {
	// 标题
	private String title;
	
	// 图标
	private String icon;
	
	// 插件ID
	private String pluginId;
	
	// 窗口ID
	private String id;

	/**
	 * 
	 * <p>Title: WindowConfig</p>
	 * <p>Description: 默认构造函数</p>
	 */
	public WindowConfig() {
		this(null);
	}
	
	/**
	 * 
	 * <p>Title: WindowConfig</p>
	 * <p>Description: 构造函数</p>
	 * @param pluginId 插件ID
	 */
	public WindowConfig(String pluginId) {
		this(pluginId, null);
	}
	
	/**
	 * 
	 * <p>Title: WindowConfig</p>
	 * <p>Description: 构造函数</p>
	 * @param pluginId 插件ID
	 * @param title 标题
	 */
	public WindowConfig(String pluginId, String title) {
		this(pluginId, title, null);
	}
	
	/**
	 * 
	 * <p>Title: WindowConfig</p>
	 * <p>Description: 构造函数</p>
	 * @param pluginId 插件ID
	 * @param title 标题
	 * @param icon 图标
	 */
	public WindowConfig(String pluginId, String title, String icon) {
		this.pluginId = pluginId;
		this.title = title;
		this.icon = icon;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the pluginId
	 */
	public String getPluginId() {
		return pluginId;
	}

	/**
	 * @param pluginId the pluginId to set
	 */
	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
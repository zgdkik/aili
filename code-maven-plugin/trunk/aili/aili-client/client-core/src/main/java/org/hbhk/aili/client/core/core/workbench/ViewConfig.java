package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *视图域对象信息配置类。此类除了拥有WindowConfig对象的配置信息外，还保存了视图显示位置的信息。
 *位置信息通过枚举类com.deppon.foss.framework.client.core.workbench.ViewPosition表示。
 */
public class ViewConfig extends WindowConfig {
	// 视图位置
	private ViewPosition position;

	/**
	 * 
	 * <p>Title: ViewConfig</p>
	 * <p>Description: 默认构造方法</p>
	 */
	public ViewConfig() {
		super();
	}

	/**
	 * 
	 * <p>Title: ViewConfig</p>
	 * <p>Description: 构造方法</p>
	 * @param pluginId 插件ID
	 * @param title 标题
	 * @param icon 图标
	 */
	public ViewConfig(String pluginId, String title, String icon) {
		super(pluginId, title, icon);
	}

	/**
	 * 
	 * <p>Title: ViewConfig</p>
	 * <p>Description: 构造方法</p>
	 * @param pluginId 插件ID
	 * @param title 标题
	 */
	public ViewConfig(String pluginId, String title) {
		super(pluginId, title);
	}

	/**
	 * 
	 * <p>Title: ViewConfig</p>
	 * <p>Description: 构造方法</p>
	 * @param pluginId 插件ID
	 */
	public ViewConfig(String pluginId) {
		super(pluginId);
	}
	
	/**
	 * 获取当前视图窗口在工作空间的位置
	 * getPosition
	 * @return
	 * @return ViewPosition 视图位置
	 * @since:0.6
	 */
	public ViewPosition getPosition() {
		return position;
	}
	
	/**
	 * 
	 * <p>Title: setPosition</p>
	 * <p>Description: 设置视图位置</p>
	 * @param position 视图位置
	 */
	public void setPosition(ViewPosition position) {
		this.position = position;
	}
}
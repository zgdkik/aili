package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *视图或者编辑区的工厂类接口定义。此接口可以根据传入的窗口配置信息，创建相应的窗口对象。
 */
public interface IWindowFactory {
	/**
	 * 根据ViewConfig配置信息创建一个IView对象
	 * createView
	 * @param config 视图配置对象
	 * @return
	 * @return IView
	 * @since:0.6
	 */
	IView createView(ViewConfig config);
	
	/**
	 * 根据EditorConfig配置信息创建相应IEditor对象
	 * createEditor
	 * @param config 编辑器配置对象
	 * @return
	 * @return IEditor
	 * @since:0.6
	 */
	IEditor createEditor(EditorConfig config);
}
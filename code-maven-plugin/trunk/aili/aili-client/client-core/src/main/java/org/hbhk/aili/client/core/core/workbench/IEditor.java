package org.hbhk.aili.client.core.core.workbench;

/**
 *	此类对象创建之后将会固定显示在WorkBench的编辑区域
 */
public interface IEditor extends IWindow {
	/**
	 * 设置IEditor的配置信息
	 * setConfig
	 * @param config 编辑器设置对象
	 * @return void
	 * @since:0.6
	 */
	void setConfig(EditorConfig config);
	
	/**
	 * 获取IEditor对象的配置信息
	 * getConfig
	 * @return
	 * @return EditorConfig 编辑器设置对象
	 * @since:0.6
	 */
	EditorConfig getConfig();
	
	/**
	 * 设置此IEditor对象前段显示
	 * toFront
	 * @return void
	 * @since:0.6
	 */
	void toFront();
	
	/**
	 * 判断此Editor是否前端显示
	 * isFront
	 * @return
	 * @return boolean
	 * @since:0.6
	 */
	boolean isFront();
	
	/**
	 * 关闭此窗口
	 * close
	 * @return void
	 * @since:0.6
	 */
	void close();
	
	/**
	 * 判断此Editor窗口是否已经关闭
	 * isClosed
	 * @return
	 * @return boolean
	 * @since:0.6
	 */
	boolean isClosed();
}
package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *描述了窗口时间对象。此对象吃了IWindow的子对象应用，表示触发事件的源对象。
 */
public class WindowEvent {
	// 窗口
	private IWindow source;

	/**
	 * 
	 * <p>Title: WindowEvent</p>
	 * <p>Description: 构造函数</p>
	 * @param source 窗口
	 */
	public WindowEvent(IWindow source) {
		this.source = source;
	}
	
	/**
	 * 
	 * <p>Title: getSource</p>
	 * <p>Description: 获取窗口</p>
	 * @return
	 */
	public IWindow getSource() {
		return source;
	}

	/**
	 * 
	 * <p>Title: setSource</p>
	 * <p>Description: 设置窗口</p>
	 * @param source 窗口
	 */
	public void setSource(IWindow source) {
		this.source = source;
	}
}
package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *窗口监听器，当打开或者关闭某个窗口对象的时候会调用监听器的相应的方法。
 */
public interface IWindowListener {
	/**
	 * 打开某个窗口对象的时候触发监听的此方法
	 * windowOpened
	 * @param event 窗口事件
	 * @return void
	 * @since:0.6
	 */
	void windowOpened(WindowEvent event);
	
	/**
	 * 关闭某个窗口对象的时候触发此方法
	 * windowClosing
	 * @param event 窗口事件
	 * @return
	 * @return boolean
	 * @since:0.6
	 */
	boolean windowClosing(WindowEvent event);
	
	/**
	 * 关闭某个窗口对象之后触发此方法
	 * windowClosed
	 * @param event 窗口事件
	 * @return void
	 * @since:0.6
	 */
	void windowClosed(WindowEvent event);
}
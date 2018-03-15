package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *工作空间监听器。当创建或者关闭工作空间的时候，会触发此监听器相应的方法。
 */
public interface IWorkbenchListener {
	/**
	 * 打开工作空间之后触发此方法
	 * workbenchOpened
	 * @return void
	 * @since:0.6
	 */
	public void workbenchOpened();
	
	/**
	 * 关闭工作空间之后触发此方法
	 * workbenchClosed
	 * @return void
	 * @since:0.6
	 */
	public void workbenchClosed();
}
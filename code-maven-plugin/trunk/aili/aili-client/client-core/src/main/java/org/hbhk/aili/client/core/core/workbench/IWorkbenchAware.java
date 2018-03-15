package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *工作空间对象的注入类。可以用来向某个实现类注入IWorkBench对象。
 */
public interface IWorkbenchAware {
	/**
	 * 
	 * <p>Title: setWorkbench</p>
	 * <p>Description: 设置工作区</p>
	 * @param workbench 工作区
	 */
	void setWorkbench(IWorkbench workbench);
}

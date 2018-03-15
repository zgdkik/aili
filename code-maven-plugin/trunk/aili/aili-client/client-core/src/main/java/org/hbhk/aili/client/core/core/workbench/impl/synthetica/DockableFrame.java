package org.hbhk.aili.client.core.core.workbench.impl.synthetica;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import org.hbhk.aili.client.core.core.workbench.IWorkbench;

/**
 * 
 *	JFrame窗口子类，具有了JFrame的一切特性。并且，此类对象还持有了IWorkbench对象。
 */
@SuppressWarnings("serial")
public class DockableFrame extends JFrame {
	// 工作区
	private IWorkbench workbench;
	
	/**
	 * 
	 * <p>Title: DockableFrame</p>
	 * <p>Description: 构造函数</p>
	 * @throws HeadlessException
	 */
	public DockableFrame() throws HeadlessException {
		super();
	}

	/**
	 * 
	 * <p>Title: DockableFrame</p>
	 * <p>Description: 构造函数</p>
	 * @param gc 绘图配置
	 */
	public DockableFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	/**
	 * 
	 * <p>Title: DockableFrame</p>
	 * <p>Description: 构造函数</p>
	 * @param title 标题
	 * @param gc 绘图配置
	 */
	public DockableFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	/**
	 * 
	 * <p>Title: DockableFrame</p>
	 * <p>Description: 构造函数</p>
	 * @param title 标题
	 * @throws HeadlessException 
	 */
	public DockableFrame(String title) throws HeadlessException {
		super(title);
	}

	/**
	 * 
	 * <p>Title: setWorkbench</p>
	 * <p>Description: 设置工作区对象</p>
	 * @param workbench 工作区
	 */
	public void setWorkbench(IWorkbench workbench) {
		this.workbench = workbench;
	}
	
	/**
	 * 
	 * <p>Title: getWorkbench</p>
	 * <p>Description: 获取工作区对象</p>
	 * @return
	 */
	public IWorkbench getWorkbench() {
		return workbench;
	}
}
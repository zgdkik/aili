package org.hbhk.aili.client.core.core.workbench;

/**
 * 
 *	窗口监听类适配器类。
 */
public class WindowAdapter implements IWindowListener {
	@Override
	public void windowOpened(WindowEvent event) {}

	@Override
	public boolean windowClosing(WindowEvent event) {
		return true;
	}

	@Override
	public void windowClosed(WindowEvent event) {}

}

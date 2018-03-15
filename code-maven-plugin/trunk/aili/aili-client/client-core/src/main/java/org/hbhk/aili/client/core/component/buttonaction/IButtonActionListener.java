package org.hbhk.aili.client.core.component.buttonaction;

import java.awt.Container;
import java.awt.event.ActionListener;

/**
 * 
 * 
 * <b style="font-family:微软雅黑"><small>Description:按钮接口</small></b> </br> <b
 */
public interface IButtonActionListener<T extends Container> extends ActionListener {

	 
	/**
	 * 
	 * 功能：set Inject UI
	 * @param:
	 * @return void
	 * @since:1.6
	 */
	public void setInjectUI(T ui);
}

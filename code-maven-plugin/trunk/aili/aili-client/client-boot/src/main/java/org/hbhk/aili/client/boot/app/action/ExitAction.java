package org.hbhk.aili.client.boot.app.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hbhk.aili.client.core.core.context.ApplicationContext;

/**
 * 点击退出按钮退出系统
 */
public class ExitAction implements ActionListener {

	/**
	 * 退出系统执行事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * 调用系统退出事件
		 */
		ApplicationContext.getApplication().exit();
	}

}
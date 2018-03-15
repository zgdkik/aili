package org.hbhk.aili.client.login.action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.login.Login;
import org.hbhk.aili.client.login.ui.AppLoginFrame;

/**
 * 登陆界面按键输入回车执行类
 */
public class EnterKeyAdapter extends KeyAdapter {

	private AppLoginFrame loginFrame;
	private LoginType loginType;
	private Login login;

	public EnterKeyAdapter(LoginType loginType, AppLoginFrame loginFrame,
			Login login) {

		this.login = login;
		this.loginType = loginType;
		this.loginFrame = loginFrame;
	}

	/**
	 * 
	 * 按钮触发快捷键
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_ENTER) {
			Runnable loginRunnable = new Runnable() {
				@Override
				public void run() {
					/**
					 * 设置语言
					 */
					SessionContext.set(SessionContext.ACCEPT_LANGUAGE, "zh-cn");
					LoginProcesser processer = new LoginProcesser(loginType,
							loginFrame, login);
					processer.doLoginProcess();
				}
			};
			// 不阻塞EDT线程，登陆操作与UI控制并行
			new Thread(loginRunnable).start();
		}
	}

}
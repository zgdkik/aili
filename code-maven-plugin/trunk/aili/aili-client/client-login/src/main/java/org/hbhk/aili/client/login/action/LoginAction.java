package org.hbhk.aili.client.login.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.login.Login;
import org.hbhk.aili.client.login.ui.AppLoginFrame;

/**
 * GUI登陆动作统一处理类
 */

public class LoginAction extends AbstractAction {
	
	private static final long serialVersionUID = 1849842401847207366L;

	private II18n i18n = I18nManager.getI18n(LoginAction.class);
	private AppLoginFrame loginFrame;
	private LoginType loginType;
	private Login login;

	public LoginAction(LoginType loginType, AppLoginFrame loginFrame,
			Login login) {
		putValue(Action.NAME, i18n.get("offline.signin.button"));
		this.login = login;
		this.loginType = loginType;
		this.loginFrame = loginFrame;
		destoryResource();
	}

	/**
	 * 释放上次登录资源
	 */
	private void destoryResource() {
		loginType.count = 0;
		SessionContext.remove(SessionContext.KEY_USER);
		SessionContext.remove(SessionContext.KEY_USER_SERVER);
		SessionContext.remove("user_status");
		SessionContext.remove("login_info");
		SessionContext.remove("FOSS_KEY_CURRENT_DEPTCODE");
		SessionContext.remove("FRAMEWORK_KEY_EMPCODE");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Runnable loginRunnable = new Runnable() {
			
			@Override
			public void run() {
				/**
				 * 设置语言
				 */
				SessionContext.set(SessionContext.ACCEPT_LANGUAGE, "zh-cn");
				LoginProcesser processer = new LoginProcesser(loginType, loginFrame, login);
				processer.doLoginProcess();
			}
		};
		//不阻塞EDT线程，登陆操作与UI控制并行
		new Thread(loginRunnable).start();
	}
}
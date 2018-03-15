package org.hbhk.aili.client.login;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.boot.app.ILogin;
import org.hbhk.aili.client.login.action.EnterKeyAdapter;
import org.hbhk.aili.client.login.action.LoginAction;
import org.hbhk.aili.client.login.action.LoginType;
import org.hbhk.aili.client.login.ui.AppLoginFrame;

/**
 * 登陆功能统一接口对象类
 */
public class Login implements ILogin {

    /**
	 * 
	 */
    private static final long serialVersionUID = 8490706914663242368L;
    public static final Log LOG = LogFactory.getLog(Login.class);

    private AppLoginFrame loginFrame;
    private LoginAction onLineByButton;
    private EnterKeyAdapter onLineByKey;

    // private LoginAction offLine;
    private AtomicBoolean loginOk = new AtomicBoolean(false);
    private AtomicBoolean loginReturn = new AtomicBoolean(false);
    private CountDownLatch loginSignal = new CountDownLatch(1);

    public CountDownLatch getLoginSignal() {
        return loginSignal;
    }

    public AtomicBoolean getLoginOk() {
        return loginOk;
    }

    public AtomicBoolean getLoginReturn() {
        return loginReturn;
    }

    private boolean flag = true;

    @Override
    public boolean login() {

        loginFrame = new AppLoginFrame();
        loginFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {

                flag = false;
            }
        });

        onLineByButton = new LoginAction(LoginType.ONLINE_LOGIN, loginFrame, this);
        onLineByKey = new EnterKeyAdapter(LoginType.ONLINE_LOGIN, loginFrame, this);

        // offLine = new LoginAction(LoginType.OFFLINE_LOGIN,loginFrame,this);

        loginFrame.getBtnOnLineLogin().addActionListener(onLineByButton);
        loginFrame.getTextUsername().addKeyListener(onLineByKey);
        loginFrame.getTextPassword().addKeyListener(onLineByKey);
        // loginFrame.getBtnOffLineLogin().addActionListener(offLine);

        loginFrame.setVisible(true);

        // while (flag) {
        // if (this.loginOk.get()) {
        // return this.loginReturn.get();
        // }
        // }
        try {
            this.loginSignal.await();
        } catch (InterruptedException e1) {
        }
        return this.loginReturn.get();
    }

}
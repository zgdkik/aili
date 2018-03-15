package org.hbhk.aili.client.main.client.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.client.core.commons.i18n.I18nManager;
import org.hbhk.aili.client.core.commons.i18n.II18n;
import org.hbhk.aili.client.core.commons.util.WindowUtil;
import org.hbhk.aili.client.core.core.context.ApplicationContext;
import org.hbhk.aili.client.core.core.context.SessionContext;
import org.hbhk.aili.client.main.client.ui.PwdDialog;


/**
 * MainFrame的锁屏事件
 */
public class LockScreenActionListener implements ActionListener {

	/**
	 * 110 size
	 */
	private static final int HUNDREDTEN = 110;
	/**
	 * 300 size
	 */
	private static final int THREEHUNDRED = 300;
	
	/**
	 * PANEL 
	 */
	private JPanel jpanel;
	
	/**
	 * 国际化对象
	 */
	private II18n i18n = I18nManager.getI18n(LockScreenActionListener.class); 

	
	/**
	 * 构造方法
	 * @param jpanel
	 */
	public LockScreenActionListener(JPanel jpanel) {
		this.jpanel = jpanel;
	}
	
	/**
	 * 密码校验
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//密码输入Dialog
		PwdDialog pwdDialog = new PwdDialog(ApplicationContext.getApplication().getWorkbench().getFrame(),true);
		//设置pwdDialog对象的标题
		pwdDialog.setTitle(i18n.get("lockscreen.title"));
		//设置pwdDialog对象的大小
		pwdDialog.setSize(THREEHUNDRED, HUNDREDTEN);
		//将pwdDialog置于屏幕中间
		WindowUtil.center(pwdDialog);
		//设置pwdDialog关闭程序属性setDefaultCloseOperation
		pwdDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		//this is invisible panel 
		/**
		 * 设置父窗体为不可见的
		 */
		jpanel.getParent().setVisible(false);
		//声明布尔型变量isContinue，默认为true
		boolean isContinue = true;
		
		//WAIT FOR USE TO INPUT 当isContinue为true时一直循环
		while (isContinue) {
		    	//pwdDialog
		    	/**
			 * 设置为可见的
			 */
			pwdDialog.setVisible(true);
			//password 获取窗体的密码
			String password = pwdDialog.getPassword();
			/**
			 * 判断密码是否为空
			 */
			if(!StringUtils.isEmpty(password)){
				// 应用OA的加密方式
				//password = CryptoUtil.digestByMD5(password);
			}
			LoginInfo	loginInfo =(LoginInfo)SessionContext.get("login_info");
			/**
			 * 判断loginInfo是否为空并且password是否为空并且当前登录用户的密码是否等于password
			 */
			if(loginInfo!=null && StringUtils.isNotEmpty(password) && loginInfo.getPassword().equals(password)){
			    	//调用窗体的dispose()方法释放资源
				pwdDialog.dispose();
				/**
				 * 设置为不可见的
				 */
				pwdDialog.setVisible(false);
				//login successfully isContinue设置为false即跳出循环
				isContinue = false;
			}else{
				//TODO 提示密码不正确
			}
		}
		//this is visible panel final
		/**
		 * 设置父窗体为可见的
		 */
		jpanel.getParent().setVisible(true);
	}
}
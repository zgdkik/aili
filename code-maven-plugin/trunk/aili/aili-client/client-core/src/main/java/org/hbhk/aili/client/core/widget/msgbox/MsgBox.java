package org.hbhk.aili.client.core.widget.msgbox;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.hbhk.aili.client.core.commons.util.WindowUtil;
import org.hbhk.aili.client.core.core.context.ApplicationContext;

/**
 * <b style="font-family:微软雅黑"><small>Description:show出一个box 用于提示</small></b>
 */
public final class MsgBox {

	private static Component parentComponent = null;
			
	static{
		try {
			parentComponent = ApplicationContext.getApplication().getWorkbench().getFrame();
		} catch (Exception e) {
			parentComponent = new JFrame();
		}
	}

	private MsgBox(){
		
	}
			
	/**
	 * @功能：show出一个box 用于提示
	 * @时间：2011-5-27
	 * @void
	 */
	public static void showInfo(String message) {
		JOptionPane.showMessageDialog(parentComponent, message);

	}
	
	/**
	 * @功能：show出一个box 用于提示IT服务台上报问题
	 * @void
	 */
	public static void showITServiceInfo(String message) {
		MsgBoxForITService msg=new MsgBoxForITService(message);
		// 剧中显示弹出窗口
		WindowUtil.centerAndShow(msg);
	}

	/**
	 * @功能：show出一个带选项的box 可以选择 是或者否 返回一个布尔值 ，
	 * @如果选是，返回true 如果选择否 返回 false
	 * @int
	 */
	public static boolean showInfo(String message, String tittle) {
		int i = JOptionPane.showConfirmDialog(parentComponent, message, tittle,
				JOptionPane.YES_NO_OPTION);
		if (i == 0) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * @功能：show 出一个错误的box 用于图示信息错误
	 * @void
	 */
	public static void showError(String message) {

		JOptionPane.showMessageDialog(parentComponent, message);

	}

}

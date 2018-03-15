package org.hbhk.aili.client.core.core.workbench;

import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *	所有的
 */
public interface IWindow {
	/**
	 * 设置IWindow对象所持有的JComponent类型
	 * setComponentType
	 * @param componentClass 必须是Swing控件JComponent的子类型
	 * @return void
	 * @since:0.6
	 */
	void setComponentType(Class<? extends JComponent> componentClass);
	
	/**
	 *以字符串的形式设置IWindow对象持有的Swing控件的类的全名
	 * setComponentType
	 * @param componentType String类型的类全名
	 * @return void
	 * @since:
	 */
	void setComponentType(String componentType);
	
	/**
	 * 设置IWindow所持有的Swing控件
	 * setComponent
	 * @param component 组件
	 * @return void
	 * @since:
	 */
	void setComponent(JComponent component);
	
	/**
	 * 返回IWindow持有的swing控件
	 * getComponent
	 * @return
	 * @return JComponent
	 * @since:
	 */
	JComponent getComponent();
	
	/**
	 * 返回控件的标题
	 * getTitle
	 * @return
	 * @return String
	 * @since:
	 */
	String getTitle();
	
	/**
	 * 返回控件IWindow所持有的控件的图标
	 * getIcon
	 * @return
	 * @return Icon
	 * @since:
	 */
	Icon getIcon();
	
	/**
	 * 显示IWindow所持有的控件
	 * setVisible
	 * @param b 布尔值，true:可见，false:不可见
	 * @return void
	 * @since:0.6
	 */
	void setVisible(boolean b);
	
	/**
	 * 返回IWindow所持有的Swing控件是否可见
	 * isVisible
	 * @return
	 * @return boolean 布尔值，true:可见，false:不可见
	 * @since:0.6
	 */
	boolean isVisible();
	
	/**
	 * 像IWindow添加监听器
	 * addWindowListener
	 * @param listener 窗口监听器
	 * @return void
	 * @since:0.6
	 */
	void addWindowListener(IWindowListener listener);
	
	/**
	 * 移除监听器
	 * removeWindowListener
	 * @param listener 窗口监听器
	 * @return void
	 * @since:0.6
	 */
	void removeWindowListener(IWindowListener listener);
	
	/**
	 * 获取IWindow持有的WindowConfig对象，WindowConfig保存了对Window对象的一些配置信息
	 * getWindowConfig
	 * @return
	 * @return WindowConfig 窗口配置对象
	 * @since:0.6
	 */
	WindowConfig getWindowConfig();
}
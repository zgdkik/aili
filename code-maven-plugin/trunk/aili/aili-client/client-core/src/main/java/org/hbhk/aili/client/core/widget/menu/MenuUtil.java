package org.hbhk.aili.client.core.widget.menu;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

/**
* <b style="font-family:微软雅黑"><small>Description:菜单工具类，提供创建菜单、创建菜单项等功能</small></b>   </br>
 */
public class MenuUtil {
	// 监听器映射
	private Map<String, ActionListener> listeners;
	
	/**
	 * 
	 * <p>Title: MenuUtil</p>
	 * <p>Description: 构造方法</p>
	 * @param listeners 监听器映射
	 */
	public MenuUtil(Map<String,ActionListener> listeners) {
		this.listeners = listeners;
	}
	
	/**
	 * 
	 * <p>Title: createMenuBar</p>
	 * <p>Description: 创建菜单栏</p>
	 * @param menus 菜单列表
	 * @return
	 */
	public JMenuBar createMenuBar(List<Menu> menus) {
		JMenuBar menuBar = new JMenuBar();
		for(Menu m:menus) {
			menuBar.add(createMenu(m));
		}
		return menuBar;
	}
	
	/**
	 * 
	 * <p>Title: createMenu</p>
	 * <p>Description: 创建一个菜单</p>
	 * @param menu 菜单
	 * @return
	 */
	private JMenu createMenu(Menu menu) {
		JMenu jm = new JMenu();
		jm.setText(menu.getName());
		jm.setMnemonic(menu.getCls().charAt(0));
		for(Menu c:menu.getChildren()) {
			if(c.isSperator()) {
				jm.add(new JSeparator());
			} else {
				jm.add(createMenuItem(c));
			}
		}
		return jm;
	}
	
	/**
	 * 
	 * <p>Title: createMenuItem</p>
	 * <p>Description: 在当前菜单上创建一个菜单项</p>
	 * @param menu 菜单
	 * @return
	 */
	private JMenuItem createMenuItem(Menu menu) {
		if(menu==null){
			throw new NullPointerException("create menuitem error,menu is null");
		}
		JMenuItem m = null;
		if (0 == menu.getType()){
			m = new JMenuItem();
		}else if (1 == menu.getType()){
			m = new JCheckBoxMenuItem();
		}else if (2 == menu.getType()) {
			m = new JRadioButtonMenuItem();
		}else{
			throw new IllegalArgumentException("menu'type is illegal，pls check it's type argument");
		}
		m.setText(menu.getName());
		m.setMnemonic(menu.getCls().charAt(0));
		m.setEnabled(!menu.isDisabled());
		String listener = menu.getActionListener();
		ActionListener aListener = listeners.get(listener);
		if(aListener!=null){
			m.addActionListener(aListener);
		}
		return m;
	}
	
}
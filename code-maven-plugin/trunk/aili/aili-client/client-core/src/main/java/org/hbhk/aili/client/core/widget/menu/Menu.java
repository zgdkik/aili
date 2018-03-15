package org.hbhk.aili.client.core.widget.menu;

import java.util.List;
/**
* <b style="font-family:微软雅黑"><small>Description:菜单类</small></b>   </br>
 */
public class Menu {
	private String name;   //菜单名称
	private String cls;   //快捷键
	private int type;   //菜单项是JMenuItem、JCheckBoxMenuItem、JRadioButtonMenuItem中的哪一种类型
	private boolean disabled;   //是否可用
	private List<Menu> children;   //节点集合
	private int childrencount;   //子节点个数
	private boolean isSperator;   //是否为分隔条
	private String actionListener;   //菜单的事件监听器
	
	/**
	 * 
	 * <p>Title: Menu</p>
	 * <p>Description: 构造函数</p>
	 */
	public Menu() {
		super();
	}
	
	/**
	 * 
	 * <p>Title: Menu</p>
	 * <p>Description: 构造函数</p>
	 * @param name 名称
	 * @param cls 快捷键
	 * @param type 类型
	 * @param disabled 是否可用
	 * @param isSperator 是否为分隔条
	 * @param actionListener 动作监听器
	 */
	public Menu(String name, String cls, int type, boolean disabled,
			boolean isSperator,String actionListener) {
		super();
		this.name = name;
		this.cls = cls;
		this.type = type;
		this.disabled = disabled;
		this.isSperator = isSperator;
		this.actionListener = actionListener;
	}
	
	/**
	 * 
	 * <p>Title: getName</p>
	 * <p>Description: 获取菜单名称</p>
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * <p>Title: setName</p>
	 * <p>Description: 设置菜单名称</p>
	 * @param name 菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * <p>Title: getCls</p>
	 * <p>Description: 获取快捷键</p>
	 * @return
	 */
	public String getCls() {
		return cls;
	}
	
	/**
	 * 
	 * <p>Title: setCls</p>
	 * <p>Description: 设置快捷键</p>
	 * @param cls 快捷键
	 */
	public void setCls(String cls) {
		this.cls = cls;
	}
	
	/**
	 * 
	 * <p>Title: getType</p>
	 * <p>Description: 获取菜单类型</p>
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * 
	 * <p>Title: setType</p>
	 * <p>Description: 设置菜单类型</p>
	 * @param type 菜单类型
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 
	 * <p>Title: isDisabled</p>
	 * <p>Description: 获取菜单是否可用</p>
	 * @return 布尔值，true:可有；false:不可用
	 */
	public boolean isDisabled() {
		return disabled;
	}
	
	/**
	 * 
	 * <p>Title: setDisabled</p>
	 * <p>Description: 设置菜单是否可用</p>
	 * @param disabled 布尔值，true:可有；false:不可用
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	/**
	 * 
	 * <p>Title: isSperator</p>
	 * <p>Description: 获取是否分隔条</p>
	 * @return 布尔值，true:是；false:否
	 */
	public boolean isSperator() {
		return isSperator;
	}
	
	/**
	 * 
	 * <p>Title: setSperator</p>
	 * <p>Description: 设置是否分隔条</p>
	 * @param isSperator 布尔值，true:是；false:否
	 */
	public void setSperator(boolean isSperator) {
		this.isSperator = isSperator;
	}
	
	/**
	 * 
	 * <p>Title: getChildren</p>
	 * <p>Description: 获取子菜单列表</p>
	 * @return
	 */
	public List<Menu> getChildren() {
		return children;
	}
	
	/**
	 * 
	 * <p>Title: setChildren</p>
	 * <p>Description: 设置子菜单</p>
	 * @param children 子菜单列表
	 */
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	
	/**
	 * 
	 * <p>Title: getChildrencount</p>
	 * <p>Description: 获取子菜单的个数</p>
	 * @return
	 */
	public int getChildrencount() {
		return childrencount;
	}
	
	/**
	 * 
	 * <p>Title: setChildrencount</p>
	 * <p>Description: 设置子菜单的个数</p>
	 * @param childrencount 数量
	 */
	public void setChildrencount(int childrencount) {
		this.childrencount = childrencount;
	}
	
	/**
	 * 
	 * <p>Title: getActionListener</p>
	 * <p>Description: 获取动作监听器</p>
	 * @return
	 */
	public String getActionListener() {
		return actionListener;
	}
	
	/**
	 * 
	 * <p>Title: setActionListener</p>
	 * <p>Description: 设置动作监听器</p>
	 * @param actionListener 动作监听器
	 */
	public void setActionListener(String actionListener) {
		this.actionListener = actionListener;
	}
}
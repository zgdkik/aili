package org.hbhk.aili.client.main.client;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;


public class MenuNodeInfo {
	/**
	 * 
	 * 序号
	 */
	private int index;
	/**
	 * 
	 *图标
	 */
	private Icon icon;
	/**
	 * 
	 * 打开次数
	 */
	private int opentime;
	/**
	 * 
	 *ID
	 */
	private String id;
	/**
	 * 
	 * 编码
	 */
	private String code;
	/**
	 * 
	 * 文本
	 */
	private String text;
	/**
	 * 
	 * 动作
	 */
	private Action action;
	/**
	 * 
	 * 快捷键
	 */
	private String shortcut;
	
	/**
	 * 
	 * 子菜单
	 */
	private List<MenuNodeInfo> childMenus;
	
	/**
	 * 
	 * PARENT ID
	 */
	private String parentId;
	
	/**
	 * 
	 * Action class
	 */
	private String actionClass;

	/**
	 * 
	 */
	private String toDoMsgRemindBizType;
	
	/**
	 * background class
	 */
	private String bgClass;
	
	/**
	 * 功能层次
	 */
	private String resLevel;
	
	
	/**
	 * @return the bgClass
	 */
	public String getBgClass() {
		return bgClass;
	}

	
	/**
	 * @param bgClass the bgClass to set
	 */
	public void setBgClass(String bgClass) {
		this.bgClass = bgClass;
	}

	/**
	 * getShortcut
	 * @return
	 */
	public String getShortcut() {
		return shortcut;
	}

	/**
	 * setShortcut
	 * @param shortcut
	 */
	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}


	/**
	 * getChildMenus
	 * @return
	 */
	public List<MenuNodeInfo> getChildMenus() {
	    	/**
	    	 * 判断childMenus是否为空，如果为空，则创建新的List集合childMenus
	    	 */
		if (childMenus == null) {
			childMenus = new ArrayList<MenuNodeInfo>();
		}
		/**
	    	 * 返回childMenus
	    	 */
		return childMenus;
	}

	/**
	 * getAction
	 * @return
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * setAction
	 * @param action
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * getText
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * setText
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * getId
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * setId
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * getCode
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * setCode
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * getParentId
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * setParentId
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * getActionClass
	 * @return
	 */
	public String getActionClass() {
		return actionClass;
	}

	/**
	 * setActionClass
	 * @param actionClass
	 */
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	/**
	 * opentime
	 * 
	 * @return the opentime
	 * @since
	 */

	public int getOpentime() {
		return opentime;
	}

	/**
	 * @param opentime
	 *            the opentime to set
	 */

	public void setOpentime(int opentime) {
		this.opentime = opentime;
	}

	/**
	 * getIcon
	 * @return
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * setIcon
	 * @param icon
	 */
	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * getIndex
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * setIndex
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param childMenus the childMenus to set
	 */
	public void setChildMenus(List<MenuNodeInfo> childMenus) {
	    	/**
	    	 * 将参数childMenus赋给当前属性childMenus
	    	 */
		this.childMenus = childMenus;
	}

	public String getToDoMsgRemindBizType() {
	    	/**
	    	 * 返回toDoMsgRemindBizType
	    	 */
		return toDoMsgRemindBizType;
	}

	public void setToDoMsgRemindBizType(String toDoMsgRemindBizType) {
	    	/**
	    	 * 将参数toDoMsgRemindBizType赋给当前属性toDoMsgRemindBizType
	    	 */
		this.toDoMsgRemindBizType = toDoMsgRemindBizType;
	}


	public void setResLevel(String resLevel) {
	    	/**
	    	 * 将参数resLevel赋给当前属性resLevel
	    	 */
		this.resLevel = resLevel;
	}


	
	/**
	 * @return the resLevel
	 */
	public String getResLevel() {
	    	/**
	    	 * 返回当前resLevel
	    	 */
		return resLevel;
	}
	
}
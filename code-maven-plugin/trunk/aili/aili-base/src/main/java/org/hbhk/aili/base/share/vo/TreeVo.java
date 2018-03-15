package org.hbhk.aili.base.share.vo;

import java.io.Serializable;

import org.hbhk.aili.base.server.context.AppContext;

public class TreeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8490904335779922143L;

	public static final String TREE_ICON_OPEN = "/images/home/icon/1_open.png";
	public static final String TREE_ICON_CLOSE = "/images/home/icon/1_close.png";
	public static final String TREE_NODE_ICON = "/images/home/icon/jd.png";

	private String id;

	private String name;

	private String pId;
	/**
	 * 是否展开
	 */
	private boolean open;

	private String iconOpen;

	private String iconClose;
	
	private boolean isParent;
	
	private boolean checked;
	/**
	 * 节点图标
	 */
	private String icon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getIconOpen() {
		if (getIsParent()) {
			return AppContext.getAppContext().getContextPath()+TREE_ICON_OPEN;
		}
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		if (getIsParent()) {
			return AppContext.getAppContext().getContextPath()+TREE_ICON_CLOSE;
		}
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIcon() {
		if (!getIsParent()) {
			return AppContext.getAppContext().getContextPath()+TREE_NODE_ICON;
		}
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	
	
}

package com.yimidida.ows.base.share.entity;

import java.util.List;

/**
 * 
 * 用户菜单
 *
 */
public interface IUserMenu {

	String getParentId();

	String getAcl();

	List<IUserMenu> getChildList();

	void setChildList(List<IUserMenu> childList);
	
	String getIcon();
}

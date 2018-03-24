package com.yimidida.ows.base.share.entity;


public interface IPrivilege extends IEntity{
	
	String getUrl();
	/**
	 * 
	 * @Title:getFunctionCode
	 * @Description:功能菜单的的代码号：code
	 */
	String getPrivilegeCode();
	
	/**
	 * 获取名称
	 */
	String getName();

}

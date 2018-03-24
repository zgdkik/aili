package com.yimidida.ows.home.share.vo;

import java.util.List;

import com.yimidida.ows.user.share.entity.PrivilegeEntity;

public class MenuList {
	private String functionName;
	private String gwUrl;
	private String privilegeCode;
	private String parentCode;
	
	private List<PrivilegeEntity> menuList;
	
	public List<PrivilegeEntity> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<PrivilegeEntity> menuList) {
		this.menuList = menuList;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getGwUrl() {
		return gwUrl;
	}

	public void setGwUrl(String gwUrl) {
		this.gwUrl = gwUrl;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	

	
	
	
}

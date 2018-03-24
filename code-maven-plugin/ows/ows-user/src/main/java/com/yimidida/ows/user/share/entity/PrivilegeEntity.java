package com.yimidida.ows.user.share.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;
import com.yimidida.ows.base.share.entity.IPrivilege;

/**
 * 
 * 权限功能表对象实体
 */
@Table(value = "t_auth_privilege")
public class PrivilegeEntity extends BizBaseEntity implements IPrivilege {

	private static final long serialVersionUID = 8204215052602820708L;

	// 功能编码
	@Column("code")
	@NotEmpty(message="权限编码不能为空")
	private String privilegeCode;

	// 功能名称
	@Column("name")
	@NotEmpty(message="权限名称不能为空")
	private String functionName;

	// 功能入口url
	@Column("url")
	@NotEmpty(message="权限URl不能为空")
	private String url;
	
	// 官网入口url
	@Column("gwUrl")
	@NotEmpty(message="官网URl不能为空")
	private String gwUrl;

	// 父功能
	@Column("parent_code")
	@NotEmpty(message="权限父编码不能为空")
	private String parentCode;


	// 显示顺序
	@Column("DISPLAY_ORDER")
	@NotEmpty(message="显示顺序不能为空")
	private String displayOrder;

	// 是否权限检查
	@Column("checkable")
	private String checkable;

	// 功能类型
	@Column("type")
	private String functionType;

	// 是否叶子节点
	@Column("leaf")
	private String leaf;

	// 图标的CSS样式
	@Column("icon_cls")
	private String iconCls;

	// 节点的CSS样式
	@Column("cls")
	private String cls;

	// 功能描述
	@Column("descp")
	private String functionDesc;
	
	
	// 1-Root,2-App(系统),3-WORKBENCH,4-MENU(菜单),5-BUNDLE,6-PANEL,7-WIDGET(按钮),8-NONE
	@Column("MODULE_TYPE")
	@NotNull(message = "权限类型不能为空")
	private String moduleType;

	// 1-WEB,2-GUI,3-NONE
	@Column("APP_TYPE")
	@NotNull(message = "系统类型不能为空")
	private String appType;


	// 快捷键定义,如:CTRL+T,ALT+A,F2,F3
	@Column("SHOUTCUT_KEYS")
	private String shoutcutKeys;
	
	@Column("compCode")
	
	private String  compCode;
	
	@Column("is_web_menu")
	
	private String  isWebMenu;
	
	
	public PrivilegeEntity() {
	}

	public String getParentCode() {
		return parentCode;
	}
	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}


	public String getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCheckable() {
		return checkable;
	}

	public void setCheckable(String checkable) {
		this.checkable = checkable;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}

	public String getName() {
		return this.functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getShoutcutKeys() {
		return shoutcutKeys;
	}

	public void setShoutcutKeys(String shoutcutKeys) {
		this.shoutcutKeys = shoutcutKeys;
	}

	public String getGwUrl() {
		return gwUrl;
	}

	public void setGwUrl(String gwUrl) {
		this.gwUrl = gwUrl;
	}

	public String getIsWebMenu() {
		return isWebMenu;
	}

	public void setIsWebMenu(String isWebMenu) {
		this.isWebMenu = isWebMenu;
	}
	
	

	
	
}

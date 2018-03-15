package com.feisuo.sds.user.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.feisuo.sds.base.share.entity.BizBaseEntity;
import com.feisuo.sds.base.share.entity.IPrivilege;

/**
 * 
 * 权限功能表对象实体
 */
@Table(value = "t_auth_privilege", dynamicUpdate = true)
public class PrivilegeEntity extends BizBaseEntity implements IPrivilege {

	private static final long serialVersionUID = 8204215052602820708L;

	// 功能编码
	@Column("privilege_code")
	private String privilegeCode;

	// 功能名称
	@Column("privilege_name")
	private String functionName;

	// 功能入口url
	@Column("url")
	private String url;

	// 父功能
	@Column("parent_code")
	private String parentCode;

	// 是否有效
	@Column("active")
	private String active;

	// 显示顺序
	@Column("display_order")
	private String displayOrder;

	// 是否权限检查
	@Column("checkable")
	private String checkable;

	// 功能类型
	@Column("function_type")
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
	@Column("function_desc")
	private String functionDesc;

	public PrivilegeEntity() {
	}

	public String getParentCode() {
		return parentCode;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

}

package org.hbhk.hls.user.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.base.share.entity.IPrivilege;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hibernate.validator.constraints.NotEmpty;

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
	private String  privilegeName;

	// 功能入口url
	@Column("url")
	@NotEmpty(message="权限URl不能为空")
	private String url;

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
	private String type;

	// 是否叶子节点
	@Column("leaf")
	private String leaf;


	// 节点的CSS样式
	@Column("cls")
	private String cls;

	// 功能描述
	@Column("descp")
	private String functionDesc;


	// 快捷键定义,如:CTRL+T,ALT+A,F2,F3
	@Column("SHOUTCUT_KEYS")
	private String shoutcutKeys;

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

	

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShoutcutKeys() {
		return shoutcutKeys;
	}

	public void setShoutcutKeys(String shoutcutKeys) {
		this.shoutcutKeys = shoutcutKeys;
	}

	@Override
	public String getName() {
		return privilegeName;
	}

	
	
}

package com.yimidida.ows.user.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;

@Table("t_auth_role_privilege")
public class RolePrivilegeEntity extends BizBaseEntity {

	private static final long serialVersionUID = 1L;

	@Column("role_code")
	private String roleCode;

	@Column("privilege_code")
	private String functionCode;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	
}

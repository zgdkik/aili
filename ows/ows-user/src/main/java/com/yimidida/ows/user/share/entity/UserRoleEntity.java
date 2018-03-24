package com.yimidida.ows.user.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.yimidida.ows.base.share.entity.BizBaseEntity;

@Table("t_auth_user_role")
public class UserRoleEntity extends BizBaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column("user_code")
	private String userCode;
	
	@Column("role_code")
	private String roleCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	
	
}

package org.hbhk.hls.user.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

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

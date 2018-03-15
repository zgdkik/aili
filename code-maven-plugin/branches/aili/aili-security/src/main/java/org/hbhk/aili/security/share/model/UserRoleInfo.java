package org.hbhk.aili.security.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_aili_user_role")
public class UserRoleInfo extends BaseModel {

	private static final long serialVersionUID = 3957210246592405024L;

	/**
	 * 角色编码
	 */
	@Column("user_code")
	private String userCode;

	/**
	 * 角色编码
	 */
	@Column("role_code")
	private String roleCode;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}

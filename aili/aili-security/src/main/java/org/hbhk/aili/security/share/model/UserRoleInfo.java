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
	@Column("user_name")
	private String userName;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}

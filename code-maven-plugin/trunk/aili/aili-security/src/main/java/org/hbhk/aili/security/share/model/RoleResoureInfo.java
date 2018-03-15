package org.hbhk.aili.security.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_aili_role_res")
public class RoleResoureInfo extends BaseModel {

	private static final long serialVersionUID = -4910835112103373768L;
	/**
	 * 角色编码
	 */
	@Column("role_code")
	private String roleCode;
	/**
	 * 权限编码
	 */
	@Column("res_code")
	private String resCode;

	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	
}

package org.hbhk.hls.user.share.entity;

import org.hbhk.aili.base.share.entity.BaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;

public class PostRoleEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -92486179089974377L;

	@Column("post_code")
	private String postCode;

	@Column("role_code")
	private String roleCode;

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}

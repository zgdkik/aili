package com.yimidida.ows.user.share.entity;

import org.mybatis.spring.annotation.Column;

import com.yimidida.ows.base.share.entity.BaseEntity;

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

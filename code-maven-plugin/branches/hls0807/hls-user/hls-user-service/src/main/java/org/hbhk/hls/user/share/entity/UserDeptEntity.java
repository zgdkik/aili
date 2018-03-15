package org.hbhk.hls.user.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table(value = "t_auth_user_dept")
public class UserDeptEntity extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4989078855619319530L;

	private String userCode;

	private String compCode;
	
	private String deptCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}

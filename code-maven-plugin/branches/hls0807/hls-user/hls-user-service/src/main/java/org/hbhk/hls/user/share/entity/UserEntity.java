package org.hbhk.hls.user.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

/**
 * 
 * 用户实体对象
 */
@Table(value = "t_auth_user")
public class UserEntity extends BizBaseEntity {

	private static final long serialVersionUID = 6334973378782503164L;

	// 用户登录名
	@Column("USERNAME")
	private String userName;

	// 用户登录密码
	@Column("PASSWORD")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

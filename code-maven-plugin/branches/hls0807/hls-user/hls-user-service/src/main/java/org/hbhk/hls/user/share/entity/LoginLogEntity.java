package org.hbhk.hls.user.share.entity;

import org.hbhk.aili.base.share.entity.BaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_user_login_log")
public class LoginLogEntity extends BaseEntity {

	private static final long serialVersionUID = 7992559924364953256L;

	@Column("user_code")
	private String userCode;

	@Column("ip")
	private String ip;
	/**
	 * 登陆方式 pc 平板 手机
	 */
	@Column("login_method")
	private String loginMethod;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginMethod() {
		return loginMethod;
	}

	public void setLoginMethod(String loginMethod) {
		this.loginMethod = loginMethod;
	}

}

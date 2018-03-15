package org.hbhk.aili.base.share.entity;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_auth_access_token")
public class AccessToken extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8549836699271750178L;

	@Column("app_key")
	private String appKey;
	
	@Column("username")
	private String username;
	
	@Column("access_token")
	private String accessToken;
	
	@Column("token_type")
	private String tokenType;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

}

package com.yimidida.ows.base.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

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
	
	@Column("device_id")
	private String deviceId;

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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	
}

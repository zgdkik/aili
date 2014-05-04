package org.hbhk.service;

import java.io.Serializable;

public class User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email = "";
//	private String nickname = "";
//	private String password = "";
//	private String userIntegral = "";
//	private String emailVerify = "";
//	private String emailVerifyCode = "";
//	private String lastLoginTime = "";
//	private String lastLoginIp = "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public String getNickname() {
//		return nickname;
//	}
//
//	public void setNickname(String nickname) {
//		this.nickname = nickname;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getUserIntegral() {
//		return userIntegral;
//	}
//
//	public void setUserIntegral(String userIntegral) {
//		this.userIntegral = userIntegral;
//	}
//
//	public String getEmailVerify() {
//		return emailVerify;
//	}
//
//	public void setEmailVerify(String emailVerify) {
//		this.emailVerify = emailVerify;
//	}
//
//	public String getEmailVerifyCode() {
//		return emailVerifyCode;
//	}
//
//	public void setEmailVerifyCode(String emailVerifyCode) {
//		this.emailVerifyCode = emailVerifyCode;
//	}
//
//	public String getLastLoginTime() {
//		return lastLoginTime;
//	}
//
//	public void setLastLoginTime(String lastLoginTime) {
//		this.lastLoginTime = lastLoginTime;
//	}
//
//	public String getLastLoginIp() {
//		return lastLoginIp;
//	}
//
//	public void setLastLoginIp(String lastLoginIp) {
//		this.lastLoginIp = lastLoginIp;
//	}

}

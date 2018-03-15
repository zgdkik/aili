package com.deppon.esb.management.user.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class SysUserInfo.
 * 
 * @Description 系统用户信息
 * @author HuangHua
 * @date 2012-03-08 11:56:07
 */
public class SysUserInfo extends BaseEntity {
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 5198220204125398859L;

	/**
	 * 系统用户名.
	 */
	private String sysUserName;
	
	/** 用户名. */
	private String userName;
	
	/** 密码. */
	private String password;
	
	/** 电话，用于发送语音通知. */
	private String telPhone;
	
	/** 移动电话，用于接收短信通知. */
	private String mobilePhone;
	
	/** 邮件. */
	private String email;

	/**
	 * Instantiates a new sys user info.
	 */
	public SysUserInfo() {
		super();
	}

	/**
	 * Instantiates a new sys user info.
	 * 
	 * @param sysUserName
	 *            the sys user name
	 * @param password
	 *            the password
	 */
	public SysUserInfo(String sysUserName, String password) {
		super();
		this.sysUserName = sysUserName;
		this.password = password;
	}

	/**
	 * 获取 系统用户名.
	 * 
	 * @return the 系统用户名
	 */
	public String getSysUserName() {
		return sysUserName;
	}

	/**
	 * 设置 系统用户名.
	 * 
	 * @param sysUserName
	 *            the new 系统用户名
	 */
	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	/**
	 * 获取 用户名.
	 * 
	 * @return the 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置 用户名.
	 * 
	 * @param userName
	 *            the new 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取 密码.
	 * 
	 * @return the 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置 密码.
	 * 
	 * @param password
	 *            the new 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 电话，用于发送语音通知.
	 * 
	 * @return the 电话，用于发送语音通知
	 */
	public String getTelPhone() {
		return telPhone;
	}

	/**
	 * 设置 电话，用于发送语音通知.
	 * 
	 * @param telPhone
	 *            the new 电话，用于发送语音通知
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**
	 * 获取 移动电话，用于接收短信通知.
	 * 
	 * @return the 移动电话，用于接收短信通知
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * 设置 移动电话，用于接收短信通知.
	 * 
	 * @param mobilePhone
	 *            the new 移动电话，用于接收短信通知
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * 获取 邮件.
	 * 
	 * @return the 邮件
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 邮件.
	 * 
	 * @param email
	 *            the new 邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}

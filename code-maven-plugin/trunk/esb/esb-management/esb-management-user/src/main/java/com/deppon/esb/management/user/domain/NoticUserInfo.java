package com.deppon.esb.management.user.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

/**
 * The Class NoticUserInfo.
 * 
 * @Description 预警通知用户
 * @author HuangHua
 * @Date 2012-03-08 14:42:09
 */
public class NoticUserInfo extends BaseEntity {
	
	/** 常量定义 serialVersionUID. */
	private static final long serialVersionUID = 5864193741692872011L;
	
	/** 用户名. */
	private String userName;
	
	/** 电话，用于发送语音通知. */
	private String telPhone;
	
	/** 移动电话，用于接收短信通知. */
	private String mobilePhone;
	
	/** 邮件. */
	private String email;
	
	/** 是否属于2期告警。 */
	private String pjVersion;

	public String getPjVersion() {
		return pjVersion;
	}

	public void setPjVersion(String pjVersion) {
		this.pjVersion = pjVersion;
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

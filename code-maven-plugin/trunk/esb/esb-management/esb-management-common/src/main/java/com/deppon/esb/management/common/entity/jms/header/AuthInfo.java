package com.deppon.esb.management.common.entity.jms.header;

import java.io.Serializable;

/**
 * The Class AuthInfo.
 */
public class AuthInfo implements Serializable {

	/** 常量定义 serialVersionUID. */
	private final static long serialVersionUID = 11082011L;
	
	/** The username. */
	protected String username;
	
	/** The password. */
	protected String password;

	/**
	 * 获取username属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置username属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setUsername(String value) {
		this.username = value;
	}

	/**
	 * 获取password属性的值。.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置password属性的值。.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setPassword(String value) {
		this.password = value;
	}

}

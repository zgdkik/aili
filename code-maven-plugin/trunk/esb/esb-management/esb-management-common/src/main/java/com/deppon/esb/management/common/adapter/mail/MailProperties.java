package com.deppon.esb.management.common.adapter.mail;


import java.util.Properties;

/**
 * 一期移植
 * @author HuangHua
 * @date 2012-12-27 上午9:53:04
 */
public class MailProperties extends Properties {
	private static final long serialVersionUID = 3890018445331359602L;
	
	private String auth;

	public MailProperties(String auth) {
		super.setProperty("mail.smtp.auth", auth);
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}

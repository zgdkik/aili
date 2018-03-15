package org.hbhk.aili.client.login.service;


/**
 * 登录异常类
 */
@SuppressWarnings("serial")
public class LoginException extends Exception {

	public LoginException() {
		super();
	}

	public LoginException(String message) {
		super(message);
	}

}
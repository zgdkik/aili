package com.yimidida.ows.common.share.request.sms;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("content")  
public class SmsContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3917218974978898373L;
	/**
	 * 手机号码
	 */
	private String phoneNumber;
	/**
	 * 短信内容
	 */
	private String body;
	/**
	 * 来源
	 */
	private String source;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}

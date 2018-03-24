package com.yimidida.ows.common.share.request.push;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTarget implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1692656141459245378L;

	@JsonProperty("UserId")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

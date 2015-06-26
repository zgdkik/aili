package org.hbhk.aili.core.share.entity;

import java.io.Serializable;

public class Token implements Serializable{

	private static final long serialVersionUID = 6290948047683392166L;
	
	private String userCode;
	
	private String timestamp;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	

}

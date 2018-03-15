package org.hbhk.aili.core.share.entity;

import java.io.Serializable;

public class Token implements Serializable {

	private static final long serialVersionUID = 6290948047683392166L;

	private String userCode;

	private String deptCode;

	private String timestamp;

	private String sessionId;

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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

}

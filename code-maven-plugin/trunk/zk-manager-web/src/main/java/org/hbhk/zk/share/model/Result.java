package org.hbhk.zk.share.model;

import java.io.Serializable;

public class Result implements Serializable {

	private static final long serialVersionUID = 9108616891288085622L;
	private boolean success = true;
	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Result() {
	}

	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String msg) {
		this.success = success;
		this.msg = msg;

	}

}

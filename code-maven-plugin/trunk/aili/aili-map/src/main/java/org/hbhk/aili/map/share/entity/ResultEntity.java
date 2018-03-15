package org.hbhk.aili.map.share.entity;

import java.io.Serializable;

public class ResultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2022373865038487946L;

	private int status;

	private String message;

	private int fence_id;

	private String conf;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFence_id() {
		return fence_id;
	}

	public void setFence_id(int fence_id) {
		this.fence_id = fence_id;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	@Override
	public String toString() {
		return "ResultEntity [status=" + status + ", message=" + message
				+ ", fence_id=" + fence_id + ", conf=" + conf + "]";
	}

}

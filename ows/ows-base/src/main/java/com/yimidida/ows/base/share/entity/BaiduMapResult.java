package com.yimidida.ows.base.share.entity;

import java.io.Serializable;

public class BaiduMapResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444127008301614077L;

	private LocationResult result;

	private String status;

	public LocationResult getResult() {
		return result;
	}

	public void setResult(LocationResult result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

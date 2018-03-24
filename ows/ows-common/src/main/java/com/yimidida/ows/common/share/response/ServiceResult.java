package com.yimidida.ows.common.share.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2604997778805045195L;
	@JsonProperty("Data")
	private String data;
	@JsonProperty("Index")
	private String index;
	@JsonProperty("ErrCode")
	private String errCode;
	@JsonProperty("ErrMsg")
	private String errMsg;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "ServiceResult [data=" + data + ", index=" + index
				+ ", errCode=" + errCode + ", errMsg=" + errMsg + "]";
	}

}

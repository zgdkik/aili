package com.feisuo.sds.api.share.entity;

import java.io.Serializable;

public class ApiInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6015080526658505544L;

	private String method;

	private String param;

	private String result;

	private String requestMode;

	private String requestFormat;

	private String url;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRequestMode() {
		return requestMode;
	}

	public void setRequestMode(String requestMode) {
		this.requestMode = requestMode;
	}

	public String getRequestFormat() {
		return requestFormat;
	}

	public void setRequestFormat(String requestFormat) {
		this.requestFormat = requestFormat;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}

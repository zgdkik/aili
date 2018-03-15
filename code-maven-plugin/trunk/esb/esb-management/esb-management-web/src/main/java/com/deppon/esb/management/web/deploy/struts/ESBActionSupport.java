package com.deppon.esb.management.web.deploy.struts;

import org.apache.struts2.dispatcher.DefaultActionSupport;

public class ESBActionSupport extends DefaultActionSupport {

	private static final long serialVersionUID = -8062614980799672200L;

	protected String message;
	protected int start;
	protected int limit;
	protected int resultCount;
	protected boolean success;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}

package com.deppon.esb.management.svccfg.domain.rest;

import java.io.Serializable;

public class ESBServiceConfigResult implements Serializable{

	private static final long serialVersionUID = -1213560300958334929L;
	
	private boolean resultCode ;
	
	private String resultMessage ;

	public boolean isResultCode() {
		return resultCode;
	}

	public void setResultCode(boolean resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}

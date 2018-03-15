package com.deppon.esb.management.svccfg.domain.rest;

import java.io.Serializable;

public class ESBServiceRoute implements Serializable{

	private static final long serialVersionUID = 1L;

	private String fid ;
	
	private String esbSvcCode;
	
	private String targetSvcCode;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getEsbSvcCode() {
		return esbSvcCode;
	}

	public void setEsbSvcCode(String esbSvcCode) {
		this.esbSvcCode = esbSvcCode;
	}

	public String getTargetSvcCode() {
		return targetSvcCode;
	}

	public void setTargetSvcCode(String targetSvcCode) {
		this.targetSvcCode = targetSvcCode;
	}
	
}

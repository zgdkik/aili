package com.yimidida.ows.common.share.vo;

import java.io.Serializable;

public class ComplaintsTypeVo implements Serializable {
	private static final long serialVersionUID = 6269588087890453075L;
	private String id;
	private String pid;
	private String complaintsType;
	public ComplaintsTypeVo() {
		
	}
	public ComplaintsTypeVo(String id, String pid, String complaintsType) {
		this.id = id;
		this.pid = pid;
		this.complaintsType = complaintsType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getComplaintsType() {
		return complaintsType;
	}
	public void setComplaintsType(String complaintsType) {
		this.complaintsType = complaintsType;
	}
}

package com.deppon.esb.management.failure.view;

import java.util.Date;

import com.deppon.esb.management.failure.domain.EsbFailureInfo;

/**
 * 页面展示DTO
 */
public class EsbFailureInfoView extends EsbFailureInfo{
	
	private static final long serialVersionUID = -7838297666041119967L;
	private String fid;
	private Date createTime;
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

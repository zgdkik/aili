package com.deppon.esb.management.audit.view;

import java.util.Date;

import com.deppon.esb.management.audit.domain.EsbAuditInfo;

/**
 * 
 * 封装展现给前台审计信息
 * @author qiancheng
 * @date 2013-2-27 下午3:07:43
 */
public class EsbAuditInfoView extends EsbAuditInfo{
	private static final long serialVersionUID = 1L;
	/**
	 * FID
	 */
	private String fid;
	/**
	 * 创建时间
	 */
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

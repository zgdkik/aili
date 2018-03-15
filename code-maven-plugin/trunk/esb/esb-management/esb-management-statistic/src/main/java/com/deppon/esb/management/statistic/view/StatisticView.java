package com.deppon.esb.management.statistic.view;

import java.io.Serializable;
import java.util.Date;

public class StatisticView implements Serializable {
	private static final long serialVersionUID = -6653513819204474977L;
	private String fid;
	private String name;
	private String esbSvcCode;
	private String backSvcCode;
	private Long totalCount;
	private Long successCount;
	private Long exceptionCount;
	private Long incompletestatuscount;
	private Date startDate;
	private Date endDate;
	private Date createTime;
	private String type;
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Long successCount) {
		this.successCount = successCount;
	}
	public Long getExceptionCount() {
		return exceptionCount;
	}
	public void setExceptionCount(Long exceptionCount) {
		this.exceptionCount = exceptionCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBackSvcCode() {
		return backSvcCode;
	}
	public void setBackSvcCode(String backSvcCode) {
		this.backSvcCode = backSvcCode;
	}
	public Long getIncompletestatuscount() {
		return incompletestatuscount;
	}
	public void setIncompletestatuscount(Long incompletestatuscount) {
		this.incompletestatuscount = incompletestatuscount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}

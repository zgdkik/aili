package com.deppon.esb.management.statistic.view;

import java.util.Date;

import com.deppon.esb.management.common.entity.FormEntity;

public class StatisticQueryBean extends FormEntity {
	private static final long serialVersionUID = -7250216739891597768L;
	private Date startDate;
	private Date endDate;
	private String esbSvcCode;
	private String backSvcCode;
	private String type;
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
	public String getEsbSvcCode() {
		return esbSvcCode;
	}
	public void setEsbSvcCode(String esbSvcCode) {
		this.esbSvcCode = esbSvcCode;
	}
	public String getBackSvcCode() {
		return backSvcCode;
	}
	public void setBackSvcCode(String backSvcCode) {
		this.backSvcCode = backSvcCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

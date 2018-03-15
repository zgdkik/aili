package com.deppon.esb.management.common.entity;

public class FormEntity extends BaseEntity{

	private static final long serialVersionUID = 8898908477545444790L;
	private Integer start;
	private Integer limit;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}

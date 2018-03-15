package com.deppon.esb.management.rbackqe.domain;

public class JmsThreadInfo extends JmsCommonInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7610189575232518452L;
	
	//线程使用天数
	private int days;

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
}

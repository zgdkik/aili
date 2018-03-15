package com.deppon.esb.management.alert.domain;

import com.deppon.esb.management.common.entity.BaseEntity;

public class QueueAlertInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1723660279971366873L;
	
	private QueueThresholdInfo thresholdInfo;
	
	private String[] emailAdder;

	public String[] getEmailAdder() {
		return emailAdder;
	}

	public void setEmailAdder(String[] emailAdder) {
		this.emailAdder = emailAdder;
	}

	public QueueThresholdInfo getThresholdInfo() {
		return thresholdInfo;
	}

	public void setThresholdInfo(QueueThresholdInfo thresholdInfo) {
		this.thresholdInfo = thresholdInfo;
	}

}

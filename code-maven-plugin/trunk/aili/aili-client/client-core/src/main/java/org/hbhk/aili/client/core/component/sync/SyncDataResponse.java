package org.hbhk.aili.client.core.component.sync;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SyncDataResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2024887334067311878L;
	private List<?> fromData;
	private Date fromDate;
	private Date maxDate;
	//private int pageSize;
	//private int fromPage;
	private Class<?> syncKey;
	private String   userID;
    

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public List<?> getFromData() {
		return fromData;
	}

	public void setFromData(List<?> fromData) {
		this.fromData = fromData;
	}
/*
	public int getPageSize() {
    	return pageSize;
    }

	public void setPageSize(int pageSize) {
    	this.pageSize = pageSize;
    }
	public int getFromPage() {
		return fromPage;
	}

	public void setFromPage(int fromPage) {
		this.fromPage = fromPage;
	}*/


	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}


	
	public Class<?> getSyncKey() {
		return syncKey;
	}
	
	public void setSyncKey(Class<?> syncKey) {
		this.syncKey = syncKey;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	
}


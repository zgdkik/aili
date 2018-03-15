package org.hbhk.aili.client.core.component.sync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyncDataRequest implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7441693678617034943L;
	private Class<?> syncKey;
    private Date     fromDate;
    private Date 	 maxDate;
    private String   orgCode;
	private String   userID;
	private String   regionID;
	private String needOrgSearch;
	
	private List<SyncDataRequest> list =new ArrayList<SyncDataRequest> ();
	//是否需要分页
	
	private String pagination;
	
	// 同步页数
	private int syncPage = 1;
	
	
	
	/**
	 * @return the pagination
	 */
	public String getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the syncPage
	 */
	public int getSyncPage() {
		return syncPage;
	}
	
	/**
	 * @param syncPage the syncPage to set
	 */
	public void setSyncPage(int syncPage) {
		this.syncPage = syncPage;
	}
	/**
	 * @return the list
	 */
	public List<SyncDataRequest> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<SyncDataRequest> list) {
		this.list = list;
	}

	/**
	 * @return the needOrgSearch
	 */
	public String getNeedOrgSearch() {
		return needOrgSearch;
	}

	/**
	 * @param needOrgSearch the needOrgSearch to set
	 */
	public void setNeedOrgSearch(String needOrgSearch) {
		this.needOrgSearch = needOrgSearch;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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
    
    public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	

 
	
    
}

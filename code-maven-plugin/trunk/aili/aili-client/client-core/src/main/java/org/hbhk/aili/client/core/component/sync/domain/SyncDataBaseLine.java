package org.hbhk.aili.client.core.component.sync.domain;

import java.util.Date;
/**
* <b style="font-family:微软雅黑"><small>Description:同步数据基线实体类</small></b>   </br>
 */
public class SyncDataBaseLine {
	// ID
	private String id;
	
	// 实体类名
	private String entityClsName;
	
	// 同步日期
	private Date syncDate;
	
	// 组织编码
	private String orgCode;
	
	// 区域ID(计价区域 ID 或者 时效区域ID)
	private String regionID;
	
	private String needOrgSearch;
	
	//是否需要分页
	private String pagination;
	
	// 同步页数
	private int syncPage = 0;
	
	

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
	 * @return the needOrgSearch
	 */
	public String getNeedOrgSearch() {
		return needOrgSearch;
	}

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
	 * @param needOrgSearch the needOrgSearch to set
	 */
	public void setNeedOrgSearch(String needOrgSearch) {
		this.needOrgSearch = needOrgSearch;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the entityClsName
	 */
	public String getEntityClsName() {
		return entityClsName;
	}

	/**
	 * @param entityClsName the entityClsName to set
	 */
	public void setEntityClsName(String entityClsName) {
		this.entityClsName = entityClsName;
	}

	/**
	 * @return the syncDate
	 */
	public Date getSyncDate() {
		return syncDate;
	}

	/**
	 * @param syncDate the syncDate to set
	 */
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRegionID() {
		return regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}



	
	
	/**
	 * @return the syncPage
	 */
/*	public int getSyncPage() {
		return syncPage;
	}

	*//**
	 * @param syncPage the syncPage to set
	 *//*
	public void setSyncPage(int syncPage) {
		this.syncPage = syncPage;
	}*/
}
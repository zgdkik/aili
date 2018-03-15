package com.feisuo.yhhc.module.bseservice_gis.api.shared.dto;

import java.io.Serializable;


public class PointDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**经度*/
	private String latitude;
	/**纬度*/
	private String longitude;
	/**司机ID*/
	private String userId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

		
	

	

}

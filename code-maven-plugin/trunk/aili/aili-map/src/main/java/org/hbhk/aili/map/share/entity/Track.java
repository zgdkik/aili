package org.hbhk.aili.map.share.entity;

import java.io.Serializable;

public class Track implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1943385340652517535L;
	
	private double latitude;
	private double longitude;
	//	必选，1：GPS经纬度坐标2：国测局加密经纬度坐标 3：百度加密经纬度坐标。
	private int coord_type;

	private long loc_time;

	private String entity_name;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(int coord_type) {
		this.coord_type = coord_type;
	}

	public long getLoc_time() {
		return loc_time;
	}

	public void setLoc_time(long loc_time) {
		this.loc_time = loc_time;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}
	
	

}

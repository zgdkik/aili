package com.deppon.foss.module.example.shared.domain;

import java.io.Serializable;

/**
 * 地理的经纬度实体
 * @author 129903-阮正华
 *
 */
public class Coordinate implements Serializable{
	private static final long serialVersionUID = 8374960762357721378L;
	private double longitude;
	private double latitude;
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}

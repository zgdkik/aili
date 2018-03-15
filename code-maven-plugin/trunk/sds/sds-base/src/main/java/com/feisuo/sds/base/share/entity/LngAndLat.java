package com.feisuo.sds.base.share.entity;

import java.io.Serializable;

public class LngAndLat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4444127008301614077L;
	/**
	 * 经度
	 */
	private Double lng;
	/**
	 * 纬度
	 */
	private Double lat;

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "LngAndLat [lng=" + lng + ", lat=" + lat + "]";
	}

	
}

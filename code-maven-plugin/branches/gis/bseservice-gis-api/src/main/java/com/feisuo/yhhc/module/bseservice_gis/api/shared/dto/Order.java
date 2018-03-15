package com.feisuo.yhhc.module.bseservice_gis.api.shared.dto;

public class Order {

	private  Double latitude;
	
	private  Double longitude;
	
	private  String orders_id;
	
	private  String orderno;
	
	private  String userid;
	
	private  String invariant_trucktype;
	
	private  String invariant_location_city;
	
	private  String invariant_createat;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(String orders_id) {
		this.orders_id = orders_id;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getInvariant_trucktype() {
		return invariant_trucktype;
	}

	public void setInvariant_trucktype(String invariant_trucktype) {
		this.invariant_trucktype = invariant_trucktype;
	}

	public String getInvariant_location_city() {
		return invariant_location_city;
	}

	public void setInvariant_location_city(String invariant_location_city) {
		this.invariant_location_city = invariant_location_city;
	}

	public String getInvariant_createat() {
		return invariant_createat;
	}

	public void setInvariant_createat(String invariant_createat) {
		this.invariant_createat = invariant_createat;
	}
	




}

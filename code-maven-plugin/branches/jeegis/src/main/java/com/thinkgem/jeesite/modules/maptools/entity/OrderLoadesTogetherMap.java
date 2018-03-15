/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.maptools.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Entity
 * @author 刘荣辉
 * @version 2015-10-29
 */
public class OrderLoadesTogetherMap extends DataEntity<OrderLoadesTogetherMap> {
	
	private static final long serialVersionUID = 1L;
	private String mapType="0";	 
	private String area;		
	private String city;
	private String province;
	private String startDate;
	private String endDate;
	private String status;
	private String truckType;
	private String verified;
	private String addressType;
	private String orderPointType;
	
	public String getMapType() {
		return mapType;
	}
	public void setMapType(String mapType) {
		this.mapType = mapType;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getOrderPointType() {
		return orderPointType;
	}
	public void setOrderPointType(String orderPointType) {
		this.orderPointType = orderPointType;
	}
	
	

		
}
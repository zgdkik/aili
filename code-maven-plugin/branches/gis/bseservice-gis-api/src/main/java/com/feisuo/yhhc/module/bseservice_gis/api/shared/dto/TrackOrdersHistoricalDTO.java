package com.feisuo.yhhc.module.bseservice_gis.api.shared.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TrackOrdersHistoricalDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/*主键*/
	private String sequenceID;
	/*司机*/
	private String userID;
	/*纬度*/
	private Double latitude;
	/*经度*/
	private Double longitude;
	/*海拔高度*/
	private Double altitude;
	/*地址名称*/
	private String addressName;
	/*地址*/
	private String address;
	/*国家*/
	private String country;
	/*省份*/
	private String province;
	/*城市*/
	private String city;
	/*区域*/
	private String area;
	/*创建时间*/
	private Date createDate;
	/*创建时间*/
	private String createDate_text;
	/*订单接收地址坐标*/
	private PointDTO orderStartAddrPoint = new PointDTO();
	/*订单派送地址坐标*/
	private PointDTO orderEndAddrPoint = new PointDTO();
	/*订单时间*/
	private String orderTime ;
	/*签到时间*/
	private String signdate ;
	/*签到地址坐标*/
	private String signpoint ;
	/*司机号码*/
	private String truckPhoneNumber ;
	/*开始时间*/
	private String drivingStart ;
	/*结束时间*/
	private String drivingEnd ;
	/*订单编号*/
	private String orderNumber ;
	/*开始时间*/
	private String startTime ;
	/*结束时间*/
	private String endTime ;
	/*V司机 0 普通司机 v司机*/
	private Integer isSignTruck ;	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getSequenceID() {
		return sequenceID;
	}
	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
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
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
		this.createDate_text = sdf.format(createDate);
	}
	public PointDTO getOrderStartAddrPoint() {
		return orderStartAddrPoint;
	}
	public void setOrderStartAddrPoint(PointDTO orderStartAddrPoint) {
		this.orderStartAddrPoint = orderStartAddrPoint;
	}
	public PointDTO getOrderEndAddrPoint() {
		return orderEndAddrPoint;
	}
	public void setOrderEndAddrPoint(PointDTO orderEndAddrPoint) {
		this.orderEndAddrPoint = orderEndAddrPoint;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getCreateDate_text() {
		return createDate_text;
	}
	public void setCreateDate_text(String createDate_text) {
		this.createDate_text = sdf.format(createDate);
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getSignpoint() {
		return signpoint;
	}
	public void setSignpoint(String signpoint) {
		this.signpoint = signpoint;
	}
	public String getTruckPhoneNumber() {
		return truckPhoneNumber;
	}
	public void setTruckPhoneNumber(String truckPhoneNumber) {
		this.truckPhoneNumber = truckPhoneNumber;
	}
	public String getDrivingStart() {
		return drivingStart;
	}
	public void setDrivingStart(String drivingStart) {
		this.drivingStart = drivingStart;
	}
	public String getDrivingEnd() {
		return drivingEnd;
	}
	public void setDrivingEnd(String drivingEnd) {
		this.drivingEnd = drivingEnd;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getIsSignTruck() {
		return isSignTruck;
	}
	public void setIsSignTruck(Integer isSignTruck) {
		isSignTruck = isSignTruck;
	}

	

}

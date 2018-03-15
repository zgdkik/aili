package com.feisuo.yhhc.module.bseservice_gis.api.shared.dto;

import java.io.Serializable;
import java.util.List;


public class OrderSignInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String orderID;
	private String orderNo;
	private String cargoID;
	private String cargoPhoneNumber;
	private String cargoName;
	private String cargoEnterprise;
	private String cargoStatus;
	private String cargoOrders;
	private String cargoGroup;
	private String cargoCatalog;
	private String cargoAgent;
	private String cargoAgentPhoneNumber;
	private String cargoAgentID;
	private String fromCity;
	private String fromAddr;
	private String fromPoint;
	private String endCity;
	private String endAddr;
	private String endPoint;
	private String truckType;
	private String distance;
	private String cost;
	private String systemCost;
	private String immediate;
	private String useTime;
	private String createDate;
	private String payType;
	private String orderCost;
	private String accountCost;
	private String couponCost;
	private String onlineCost;
	private String status;
	private String truckID;
	private String truckPhoneNumber;
	private String truckName;
	private String truckStatus;
	private String truckGroup;
	private String truckCatalog;
	private String truckAgent;
	private String truckAgentPhoneNumber;
	private String truckAgentID;
	private String truckOrders;
	private String rushSeconds;
	private String signDate;
	private String signPoint;
	private String fromBeginDistance;
	private String fromEndDistance;
	private String fromPublishTime;
	private String fromRushTime;
	private String truckRateType;
	private String cargoRateType;
	private String truckRateContent;
	private String cargoRateContent;
	private String truckCall;
	private String cargoCall;
	private List<TrackOrdersHistoricalDTO> trackOrdersHistoricalList;
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCargoID() {
		return cargoID;
	}
	public void setCargoID(String cargoID) {
		this.cargoID = cargoID;
	}
	public String getCargoPhoneNumber() {
		return cargoPhoneNumber;
	}
	public void setCargoPhoneNumber(String cargoPhoneNumber) {
		this.cargoPhoneNumber = cargoPhoneNumber;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getCargoEnterprise() {
		return cargoEnterprise;
	}
	public void setCargoEnterprise(String cargoEnterprise) {
		this.cargoEnterprise = cargoEnterprise;
	}
	public String getCargoStatus() {
		return cargoStatus;
	}
	public void setCargoStatus(String cargoStatus) {
		this.cargoStatus = cargoStatus;
	}
	public String getCargoOrders() {
		return cargoOrders;
	}
	public void setCargoOrders(String cargoOrders) {
		this.cargoOrders = cargoOrders;
	}
	public String getCargoGroup() {
		return cargoGroup;
	}
	public void setCargoGroup(String cargoGroup) {
		this.cargoGroup = cargoGroup;
	}
	public String getCargoCatalog() {
		return cargoCatalog;
	}
	public void setCargoCatalog(String cargoCatalog) {
		this.cargoCatalog = cargoCatalog;
	}
	public String getCargoAgent() {
		return cargoAgent;
	}
	public void setCargoAgent(String cargoAgent) {
		this.cargoAgent = cargoAgent;
	}
	public String getCargoAgentPhoneNumber() {
		return cargoAgentPhoneNumber;
	}
	public void setCargoAgentPhoneNumber(String cargoAgentPhoneNumber) {
		this.cargoAgentPhoneNumber = cargoAgentPhoneNumber;
	}
	public String getCargoAgentID() {
		return cargoAgentID;
	}
	public void setCargoAgentID(String cargoAgentID) {
		this.cargoAgentID = cargoAgentID;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
	public String getFromPoint() {
		return fromPoint;
	}
	public void setFromPoint(String fromPoint) {
		this.fromPoint = fromPoint;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public String getEndAddr() {
		return endAddr;
	}
	public void setEndAddr(String endAddr) {
		this.endAddr = endAddr;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSystemCost() {
		return systemCost;
	}
	public void setSystemCost(String systemCost) {
		this.systemCost = systemCost;
	}
	public String getImmediate() {
		return immediate;
	}
	public void setImmediate(String immediate) {
		this.immediate = immediate;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getOrderCost() {
		return orderCost;
	}
	public void setOrderCost(String orderCost) {
		this.orderCost = orderCost;
	}
	public String getAccountCost() {
		return accountCost;
	}
	public void setAccountCost(String accountCost) {
		this.accountCost = accountCost;
	}
	public String getCouponCost() {
		return couponCost;
	}
	public void setCouponCost(String couponCost) {
		this.couponCost = couponCost;
	}
	public String getOnlineCost() {
		return onlineCost;
	}
	public void setOnlineCost(String onlineCost) {
		this.onlineCost = onlineCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTruckID() {
		return truckID;
	}
	public void setTruckID(String truckID) {
		this.truckID = truckID;
	}
	public String getTruckPhoneNumber() {
		return truckPhoneNumber;
	}
	public void setTruckPhoneNumber(String truckPhoneNumber) {
		this.truckPhoneNumber = truckPhoneNumber;
	}
	public String getTruckName() {
		return truckName;
	}
	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}
	public String getTruckStatus() {
		return truckStatus;
	}
	public void setTruckStatus(String truckStatus) {
		this.truckStatus = truckStatus;
	}
	public String getTruckGroup() {
		return truckGroup;
	}
	public void setTruckGroup(String truckGroup) {
		this.truckGroup = truckGroup;
	}
	public String getTruckCatalog() {
		return truckCatalog;
	}
	public void setTruckCatalog(String truckCatalog) {
		this.truckCatalog = truckCatalog;
	}
	public String getTruckAgent() {
		return truckAgent;
	}
	public void setTruckAgent(String truckAgent) {
		this.truckAgent = truckAgent;
	}
	public String getTruckAgentPhoneNumber() {
		return truckAgentPhoneNumber;
	}
	public void setTruckAgentPhoneNumber(String truckAgentPhoneNumber) {
		this.truckAgentPhoneNumber = truckAgentPhoneNumber;
	}
	public String getTruckAgentID() {
		return truckAgentID;
	}
	public void setTruckAgentID(String truckAgentID) {
		this.truckAgentID = truckAgentID;
	}
	public String getTruckOrders() {
		return truckOrders;
	}
	public void setTruckOrders(String truckOrders) {
		this.truckOrders = truckOrders;
	}
	public String getRushSeconds() {
		return rushSeconds;
	}
	public void setRushSeconds(String rushSeconds) {
		this.rushSeconds = rushSeconds;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getSignPoint() {
		return signPoint;
	}
	public void setSignPoint(String signPoint) {
		this.signPoint = signPoint;
	}
	public String getFromBeginDistance() {
		return fromBeginDistance;
	}
	public void setFromBeginDistance(String fromBeginDistance) {
		this.fromBeginDistance = fromBeginDistance;
	}
	public String getFromEndDistance() {
		return fromEndDistance;
	}
	public void setFromEndDistance(String fromEndDistance) {
		this.fromEndDistance = fromEndDistance;
	}
	public String getFromPublishTime() {
		return fromPublishTime;
	}
	public void setFromPublishTime(String fromPublishTime) {
		this.fromPublishTime = fromPublishTime;
	}
	public String getFromRushTime() {
		return fromRushTime;
	}
	public void setFromRushTime(String fromRushTime) {
		this.fromRushTime = fromRushTime;
	}
	public String getTruckRateType() {
		return truckRateType;
	}
	public void setTruckRateType(String truckRateType) {
		this.truckRateType = truckRateType;
	}
	public String getCargoRateType() {
		return cargoRateType;
	}
	public void setCargoRateType(String cargoRateType) {
		this.cargoRateType = cargoRateType;
	}
	public String getTruckRateContent() {
		return truckRateContent;
	}
	public void setTruckRateContent(String truckRateContent) {
		this.truckRateContent = truckRateContent;
	}
	public String getCargoRateContent() {
		return cargoRateContent;
	}
	public void setCargoRateContent(String cargoRateContent) {
		this.cargoRateContent = cargoRateContent;
	}
	public String getTruckCall() {
		return truckCall;
	}
	public void setTruckCall(String truckCall) {
		this.truckCall = truckCall;
	}
	public String getCargoCall() {
		return cargoCall;
	}
	public void setCargoCall(String cargoCall) {
		this.cargoCall = cargoCall;
	}
	public List<TrackOrdersHistoricalDTO> getTrackOrdersHistoricalList() {
		return trackOrdersHistoricalList;
	}
	public void setTrackOrdersHistoricalList(
			List<TrackOrdersHistoricalDTO> trackOrdersHistoricalList) {
		this.trackOrdersHistoricalList = trackOrdersHistoricalList;
	}
	

}

package com.yimidida.ows.home.share.vo;

import java.io.Serializable;
import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;


/**
 * 大道标准运价
 * @author YanLong.Dong
 *
 */
public class SimpleStandardPrice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String compCode;
	
	//起运地代码
	private String srcZoneCode;
	//目的地代码
	private String destZoneCode;
	//件数单价
	private Double unitPrice;
	//重量单价
	private Double weightPrice;
	//体积单价
	private Double volumnPrice;
	//货物类型
	private String cargoTypeCode;
	//时效类型
	@Column("LIMIT_TYPE_CODE")
	private String limitTypeCode;
	//服务类型
	private String serviceCode;
	//线路距离
	private Double distance;
	// 生效日期
	private Date validTm;
	// 失效日期
	private Date invalidTm;
	//有效标志
	private Boolean validFlag;
	private String createdEmpCode;
	private Date createdTm;
	private String modifiedEmpCode;
	private Date modifiedTm;
	
	public String getSrcZoneCode() {
		return srcZoneCode;
	}
	public void setSrcZoneCode(String srcZoneCode) {
		this.srcZoneCode = srcZoneCode;
	}
	public String getDestZoneCode() {
		return destZoneCode;
	}
	public void setDestZoneCode(String destZoneCode) {
		this.destZoneCode = destZoneCode;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getWeightPrice() {
		return weightPrice;
	}
	public void setWeightPrice(Double weightPrice) {
		this.weightPrice = weightPrice;
	}
	public Double getVolumnPrice() {
		return volumnPrice;
	}
	public void setVolumnPrice(Double volumnPrice) {
		this.volumnPrice = volumnPrice;
	}
	public String getCargoTypeCode() {
		return cargoTypeCode;
	}
	public void setCargoTypeCode(String cargoTypeCode) {
		this.cargoTypeCode = cargoTypeCode;
	}
	public String getLimitTypeCode() {
		return limitTypeCode;
	}
	public void setLimitTypeCode(String limitTypeCode) {
		this.limitTypeCode = limitTypeCode;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Date getValidTm() {
		return validTm;
	}
	public void setValidTm(Date validTm) {
		this.validTm = validTm;
	}
	public Date getInvalidTm() {
		return invalidTm;
	}
	public void setInvalidTm(Date invalidTm) {
		this.invalidTm = invalidTm;
	}
	public Boolean getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(Boolean validFlag) {
		this.validFlag = validFlag;
	}
	public String getCreatedEmpCode() {
		return createdEmpCode;
	}
	public void setCreatedEmpCode(String createdEmpCode) {
		this.createdEmpCode = createdEmpCode;
	}
	public Date getCreatedTm() {
		return createdTm;
	}
	public void setCreatedTm(Date createdTm) {
		this.createdTm = createdTm;
	}
	public String getModifiedEmpCode() {
		return modifiedEmpCode;
	}
	public void setModifiedEmpCode(String modifiedEmpCode) {
		this.modifiedEmpCode = modifiedEmpCode;
	}
	public Date getModifiedTm() {
		return modifiedTm;
	}
	public void setModifiedTm(Date modifiedTm) {
		this.modifiedTm = modifiedTm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	
}

package org.hbhk.aili.esb.test;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AsynGoodsBillReceiveRequest", propOrder = { "custName",
		"custMobile", "custTel", "receiveProvince", "receiveProvinceCode",
		"receiveCity", "receiveCityCode", "receiveCounty", "receiveCountyCode",
		"receiveDetailAddress", "receiveDetailAddressRemark",
		"consigneeContactCode", "receiverCustName", "receiverCustMobile",
		"receiverCustPhone", "consigneeProvince", "consigneeProvinceCode",
		"consigneeCity", "consigneeCityCode", "consigneeCounty",
		"consigneeCountyCode", "consigneeDetailAddress",
		"consigneeDetailAddressRemark", "consigneeAddress", "goodsName",
		"weight", "cubage", "packing", "pieces", "size", "desc",
		"transProperty", "deliverMode", "ladingStationId", "goodsType",
		"usingVehicleDeptNum", "usingVehicleDeptId", "firstPickTime",
		"lastPickTime", "vehDeptNum", "vehDeptId", "orderNumber",
		"orderedTime", "orderType", "orderOwnDeptId", "creatorNum",
		"creatorId", "carType", "memberType", "waybillNumber",
		"reciveLoanType", "reviceMoneyAmount", "insuredAmount", "couponNumber",
		"paidMethod", "returnBillType", "channelCustID", "waybillType",
		"deliveryCustomerCode", "isEWaybillBigCustomer", "codRefundAccount",
		"codRefundAccountName", "teanLimit", "procurementNumber",
		"channelNumber", "senderCode", "paymentOrgCode" })
@XmlRootElement(name ="AsynGoodsBillReceiveRequest")
public class AsynGoodsBillReceiveRequest implements Serializable {

	private final static long serialVersionUID = 11082011L;
	protected String custName;
	protected String custMobile;
	protected String custTel;
	@XmlElement(required = true)
	protected String receiveProvince;
	@XmlElement(required = true)
	protected String receiveProvinceCode;
	@XmlElement(required = true)
	protected String receiveCity;
	@XmlElement(required = true)
	protected String receiveCityCode;
	@XmlElement(required = true)
	protected String receiveCounty;
	@XmlElement(required = true)
	protected String receiveCountyCode;
	@XmlElement(required = true)
	protected String receiveDetailAddress;
	@XmlElement(required = true)
	protected String receiveDetailAddressRemark;
	protected String consigneeContactCode;
	@XmlElement(required = true)
	protected String receiverCustName;
	@XmlElement(required = true)
	protected String receiverCustMobile;
	@XmlElement(required = true)
	protected String receiverCustPhone;
	@XmlElement(required = true)
	protected String consigneeProvince;
	@XmlElement(required = true)
	protected String consigneeProvinceCode;
	@XmlElement(required = true)
	protected String consigneeCity;
	@XmlElement(required = true)
	protected String consigneeCityCode;
	@XmlElement(required = true)
	protected String consigneeCounty;
	@XmlElement(required = true)
	protected String consigneeCountyCode;
	@XmlElement(required = true)
	protected String consigneeDetailAddress;
	@XmlElement(required = true)
	protected String consigneeDetailAddressRemark;
	@XmlElement(required = true)
	protected String consigneeAddress;
	@XmlElement(required = true)
	protected String goodsName;
	protected double weight;
	protected double cubage;
	@XmlElement(required = true)
	protected String packing;
	protected int pieces;
	@XmlElement(required = true)
	protected String size;
	protected String desc;
	@XmlElement(required = true)
	protected String transProperty;
	@XmlElement(required = true)
	protected String deliverMode;
	@XmlElement(required = true)
	protected String ladingStationId;
	protected String goodsType;
	@XmlElement(required = true)
	protected String usingVehicleDeptNum;
	@XmlElement(required = true)
	protected String usingVehicleDeptId;
	@XmlElement(required = true, type = String.class)
	@XmlSchemaType(name = "dateTime")
	protected Date firstPickTime;
	@XmlElement(required = true, type = String.class)
	@XmlSchemaType(name = "dateTime")
	protected Date lastPickTime;
	@XmlElement(required = true)
	protected String vehDeptNum;
	@XmlElement(required = true)
	protected String vehDeptId;
	@XmlElement(required = true)
	protected String orderNumber;
	@XmlElement(required = true, type = String.class)
	@XmlSchemaType(name = "dateTime")
	protected Date orderedTime;
	@XmlElement(required = true)
	protected String orderType;
	@XmlElement(required = true)
	protected String orderOwnDeptId;
	@XmlElement(required = true)
	protected String creatorNum;
	@XmlElement(required = true)
	protected String creatorId;
	@XmlElement(required = true)
	protected String carType;
	protected String memberType;
	@XmlElement(required = true)
	protected String waybillNumber;
	@XmlElement(required = true)
	protected String reciveLoanType;
	@XmlElement(required = true)
	protected BigDecimal reviceMoneyAmount;
	@XmlElement(required = true)
	protected BigDecimal insuredAmount;
	@XmlElement(required = true)
	protected String couponNumber;
	protected String paidMethod;
	protected String returnBillType;
	protected String channelCustID;
	protected String waybillType;
	protected String deliveryCustomerCode;
	protected String isEWaybillBigCustomer;
	protected String codRefundAccount;
	protected String codRefundAccountName;
	@XmlElement(required = true)
	protected BigDecimal teanLimit;
	protected String procurementNumber;
	protected String channelNumber;
	protected String senderCode;
	protected String paymentOrgCode;

	/**
	 * 鑾峰彇custName灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * 璁剧疆custName灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustName(String value) {
		this.custName = value;
	}

	/**
	 * 鑾峰彇custMobile灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustMobile() {
		return custMobile;
	}

	/**
	 * 璁剧疆custMobile灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustMobile(String value) {
		this.custMobile = value;
	}

	/**
	 * 鑾峰彇custTel灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCustTel() {
		return custTel;
	}

	/**
	 * 璁剧疆custTel灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCustTel(String value) {
		this.custTel = value;
	}

	/**
	 * 鑾峰彇receiveProvince灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveProvince() {
		return receiveProvince;
	}

	/**
	 * 璁剧疆receiveProvince灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveProvince(String value) {
		this.receiveProvince = value;
	}

	/**
	 * 鑾峰彇receiveProvinceCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveProvinceCode() {
		return receiveProvinceCode;
	}

	/**
	 * 璁剧疆receiveProvinceCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveProvinceCode(String value) {
		this.receiveProvinceCode = value;
	}

	/**
	 * 鑾峰彇receiveCity灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveCity() {
		return receiveCity;
	}

	/**
	 * 璁剧疆receiveCity灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveCity(String value) {
		this.receiveCity = value;
	}

	/**
	 * 鑾峰彇receiveCityCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveCityCode() {
		return receiveCityCode;
	}

	/**
	 * 璁剧疆receiveCityCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveCityCode(String value) {
		this.receiveCityCode = value;
	}

	/**
	 * 鑾峰彇receiveCounty灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveCounty() {
		return receiveCounty;
	}

	/**
	 * 璁剧疆receiveCounty灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveCounty(String value) {
		this.receiveCounty = value;
	}

	/**
	 * 鑾峰彇receiveCountyCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveCountyCode() {
		return receiveCountyCode;
	}

	/**
	 * 璁剧疆receiveCountyCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveCountyCode(String value) {
		this.receiveCountyCode = value;
	}

	/**
	 * 鑾峰彇receiveDetailAddress灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveDetailAddress() {
		return receiveDetailAddress;
	}

	/**
	 * 璁剧疆receiveDetailAddress灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveDetailAddress(String value) {
		this.receiveDetailAddress = value;
	}

	/**
	 * 鑾峰彇receiveDetailAddressRemark灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveDetailAddressRemark() {
		return receiveDetailAddressRemark;
	}

	/**
	 * 璁剧疆receiveDetailAddressRemark灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveDetailAddressRemark(String value) {
		this.receiveDetailAddressRemark = value;
	}

	/**
	 * 鑾峰彇consigneeContactCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeContactCode() {
		return consigneeContactCode;
	}

	/**
	 * 璁剧疆consigneeContactCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeContactCode(String value) {
		this.consigneeContactCode = value;
	}

	/**
	 * 鑾峰彇receiverCustName灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiverCustName() {
		return receiverCustName;
	}

	/**
	 * 璁剧疆receiverCustName灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiverCustName(String value) {
		this.receiverCustName = value;
	}

	/**
	 * 鑾峰彇receiverCustMobile灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiverCustMobile() {
		return receiverCustMobile;
	}

	/**
	 * 璁剧疆receiverCustMobile灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiverCustMobile(String value) {
		this.receiverCustMobile = value;
	}

	/**
	 * 鑾峰彇receiverCustPhone灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiverCustPhone() {
		return receiverCustPhone;
	}

	/**
	 * 璁剧疆receiverCustPhone灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiverCustPhone(String value) {
		this.receiverCustPhone = value;
	}

	/**
	 * 鑾峰彇consigneeProvince灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeProvince() {
		return consigneeProvince;
	}

	/**
	 * 璁剧疆consigneeProvince灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeProvince(String value) {
		this.consigneeProvince = value;
	}

	/**
	 * 鑾峰彇consigneeProvinceCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeProvinceCode() {
		return consigneeProvinceCode;
	}

	/**
	 * 璁剧疆consigneeProvinceCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeProvinceCode(String value) {
		this.consigneeProvinceCode = value;
	}

	/**
	 * 鑾峰彇consigneeCity灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeCity() {
		return consigneeCity;
	}

	/**
	 * 璁剧疆consigneeCity灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeCity(String value) {
		this.consigneeCity = value;
	}

	/**
	 * 鑾峰彇consigneeCityCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeCityCode() {
		return consigneeCityCode;
	}

	/**
	 * 璁剧疆consigneeCityCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeCityCode(String value) {
		this.consigneeCityCode = value;
	}

	/**
	 * 鑾峰彇consigneeCounty灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeCounty() {
		return consigneeCounty;
	}

	/**
	 * 璁剧疆consigneeCounty灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeCounty(String value) {
		this.consigneeCounty = value;
	}

	/**
	 * 鑾峰彇consigneeCountyCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeCountyCode() {
		return consigneeCountyCode;
	}

	/**
	 * 璁剧疆consigneeCountyCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeCountyCode(String value) {
		this.consigneeCountyCode = value;
	}

	/**
	 * 鑾峰彇consigneeDetailAddress灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeDetailAddress() {
		return consigneeDetailAddress;
	}

	/**
	 * 璁剧疆consigneeDetailAddress灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeDetailAddress(String value) {
		this.consigneeDetailAddress = value;
	}

	/**
	 * 鑾峰彇consigneeDetailAddressRemark灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeDetailAddressRemark() {
		return consigneeDetailAddressRemark;
	}

	/**
	 * 璁剧疆consigneeDetailAddressRemark灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeDetailAddressRemark(String value) {
		this.consigneeDetailAddressRemark = value;
	}

	/**
	 * 鑾峰彇consigneeAddress灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	/**
	 * 璁剧疆consigneeAddress灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeAddress(String value) {
		this.consigneeAddress = value;
	}

	/**
	 * 鑾峰彇goodsName灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * 璁剧疆goodsName灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGoodsName(String value) {
		this.goodsName = value;
	}

	/**
	 * 鑾峰彇weight灞炴�鐨勫�銆�
	 * 
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * 璁剧疆weight灞炴�鐨勫�銆�
	 * 
	 */
	public void setWeight(double value) {
		this.weight = value;
	}

	/**
	 * 鑾峰彇cubage灞炴�鐨勫�銆�
	 * 
	 */
	public double getCubage() {
		return cubage;
	}

	/**
	 * 璁剧疆cubage灞炴�鐨勫�銆�
	 * 
	 */
	public void setCubage(double value) {
		this.cubage = value;
	}

	/**
	 * 鑾峰彇packing灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPacking() {
		return packing;
	}

	/**
	 * 璁剧疆packing灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPacking(String value) {
		this.packing = value;
	}

	/**
	 * 鑾峰彇pieces灞炴�鐨勫�銆�
	 * 
	 */
	public int getPieces() {
		return pieces;
	}

	/**
	 * 璁剧疆pieces灞炴�鐨勫�銆�
	 * 
	 */
	public void setPieces(int value) {
		this.pieces = value;
	}

	/**
	 * 鑾峰彇size灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 璁剧疆size灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSize(String value) {
		this.size = value;
	}

	/**
	 * 鑾峰彇desc灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 璁剧疆desc灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDesc(String value) {
		this.desc = value;
	}

	/**
	 * 鑾峰彇transProperty灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransProperty() {
		return transProperty;
	}

	/**
	 * 璁剧疆transProperty灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransProperty(String value) {
		this.transProperty = value;
	}

	/**
	 * 鑾峰彇deliverMode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeliverMode() {
		return deliverMode;
	}

	/**
	 * 璁剧疆deliverMode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeliverMode(String value) {
		this.deliverMode = value;
	}

	/**
	 * 鑾峰彇ladingStationId灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLadingStationId() {
		return ladingStationId;
	}

	/**
	 * 璁剧疆ladingStationId灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLadingStationId(String value) {
		this.ladingStationId = value;
	}

	/**
	 * 鑾峰彇goodsType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * 璁剧疆goodsType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGoodsType(String value) {
		this.goodsType = value;
	}

	/**
	 * 鑾峰彇usingVehicleDeptNum灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsingVehicleDeptNum() {
		return usingVehicleDeptNum;
	}

	/**
	 * 璁剧疆usingVehicleDeptNum灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsingVehicleDeptNum(String value) {
		this.usingVehicleDeptNum = value;
	}

	/**
	 * 鑾峰彇usingVehicleDeptId灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsingVehicleDeptId() {
		return usingVehicleDeptId;
	}

	/**
	 * 璁剧疆usingVehicleDeptId灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsingVehicleDeptId(String value) {
		this.usingVehicleDeptId = value;
	}

	/**
	 * 鑾峰彇firstPickTime灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getFirstPickTime() {
		return firstPickTime;
	}

	/**
	 * 璁剧疆firstPickTime灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFirstPickTime(Date value) {
		this.firstPickTime = value;
	}

	/**
	 * 鑾峰彇lastPickTime灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getLastPickTime() {
		return lastPickTime;
	}

	/**
	 * 璁剧疆lastPickTime灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLastPickTime(Date value) {
		this.lastPickTime = value;
	}

	/**
	 * 鑾峰彇vehDeptNum灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVehDeptNum() {
		return vehDeptNum;
	}

	/**
	 * 璁剧疆vehDeptNum灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVehDeptNum(String value) {
		this.vehDeptNum = value;
	}

	/**
	 * 鑾峰彇vehDeptId灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVehDeptId() {
		return vehDeptId;
	}

	/**
	 * 璁剧疆vehDeptId灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVehDeptId(String value) {
		this.vehDeptId = value;
	}

	/**
	 * 鑾峰彇orderNumber灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * 璁剧疆orderNumber灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderNumber(String value) {
		this.orderNumber = value;
	}

	/**
	 * 鑾峰彇orderedTime灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public Date getOrderedTime() {
		return orderedTime;
	}

	/**
	 * 璁剧疆orderedTime灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderedTime(Date value) {
		this.orderedTime = value;
	}

	/**
	 * 鑾峰彇orderType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 璁剧疆orderType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderType(String value) {
		this.orderType = value;
	}

	/**
	 * 鑾峰彇orderOwnDeptId灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOrderOwnDeptId() {
		return orderOwnDeptId;
	}

	/**
	 * 璁剧疆orderOwnDeptId灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOrderOwnDeptId(String value) {
		this.orderOwnDeptId = value;
	}

	/**
	 * 鑾峰彇creatorNum灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorNum() {
		return creatorNum;
	}

	/**
	 * 璁剧疆creatorNum灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorNum(String value) {
		this.creatorNum = value;
	}

	/**
	 * 鑾峰彇creatorId灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * 璁剧疆creatorId灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreatorId(String value) {
		this.creatorId = value;
	}

	/**
	 * 鑾峰彇carType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCarType() {
		return carType;
	}

	/**
	 * 璁剧疆carType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCarType(String value) {
		this.carType = value;
	}

	/**
	 * 鑾峰彇memberType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemberType() {
		return memberType;
	}

	/**
	 * 璁剧疆memberType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMemberType(String value) {
		this.memberType = value;
	}

	/**
	 * 鑾峰彇waybillNumber灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWaybillNumber() {
		return waybillNumber;
	}

	/**
	 * 璁剧疆waybillNumber灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWaybillNumber(String value) {
		this.waybillNumber = value;
	}

	/**
	 * 鑾峰彇reciveLoanType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReciveLoanType() {
		return reciveLoanType;
	}

	/**
	 * 璁剧疆reciveLoanType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReciveLoanType(String value) {
		this.reciveLoanType = value;
	}

	/**
	 * 鑾峰彇reviceMoneyAmount灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getReviceMoneyAmount() {
		return reviceMoneyAmount;
	}

	/**
	 * 璁剧疆reviceMoneyAmount灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setReviceMoneyAmount(BigDecimal value) {
		this.reviceMoneyAmount = value;
	}

	/**
	 * 鑾峰彇insuredAmount灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getInsuredAmount() {
		return insuredAmount;
	}

	/**
	 * 璁剧疆insuredAmount灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setInsuredAmount(BigDecimal value) {
		this.insuredAmount = value;
	}

	/**
	 * 鑾峰彇couponNumber灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCouponNumber() {
		return couponNumber;
	}

	/**
	 * 璁剧疆couponNumber灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCouponNumber(String value) {
		this.couponNumber = value;
	}

	/**
	 * 鑾峰彇paidMethod灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPaidMethod() {
		return paidMethod;
	}

	/**
	 * 璁剧疆paidMethod灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPaidMethod(String value) {
		this.paidMethod = value;
	}

	/**
	 * 鑾峰彇returnBillType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturnBillType() {
		return returnBillType;
	}

	/**
	 * 璁剧疆returnBillType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReturnBillType(String value) {
		this.returnBillType = value;
	}

	/**
	 * 鑾峰彇channelCustID灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChannelCustID() {
		return channelCustID;
	}

	/**
	 * 璁剧疆channelCustID灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChannelCustID(String value) {
		this.channelCustID = value;
	}

	/**
	 * 鑾峰彇waybillType灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getWaybillType() {
		return waybillType;
	}

	/**
	 * 璁剧疆waybillType灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setWaybillType(String value) {
		this.waybillType = value;
	}

	/**
	 * 鑾峰彇deliveryCustomerCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeliveryCustomerCode() {
		return deliveryCustomerCode;
	}

	/**
	 * 璁剧疆deliveryCustomerCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeliveryCustomerCode(String value) {
		this.deliveryCustomerCode = value;
	}

	/**
	 * 鑾峰彇isEWaybillBigCustomer灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsEWaybillBigCustomer() {
		return isEWaybillBigCustomer;
	}

	/**
	 * 璁剧疆isEWaybillBigCustomer灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsEWaybillBigCustomer(String value) {
		this.isEWaybillBigCustomer = value;
	}

	/**
	 * 鑾峰彇codRefundAccount灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodRefundAccount() {
		return codRefundAccount;
	}

	/**
	 * 璁剧疆codRefundAccount灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodRefundAccount(String value) {
		this.codRefundAccount = value;
	}

	/**
	 * 鑾峰彇codRefundAccountName灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodRefundAccountName() {
		return codRefundAccountName;
	}

	/**
	 * 璁剧疆codRefundAccountName灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodRefundAccountName(String value) {
		this.codRefundAccountName = value;
	}

	/**
	 * 鑾峰彇teanLimit灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getTeanLimit() {
		return teanLimit;
	}

	/**
	 * 璁剧疆teanLimit灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setTeanLimit(BigDecimal value) {
		this.teanLimit = value;
	}

	/**
	 * 鑾峰彇procurementNumber灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProcurementNumber() {
		return procurementNumber;
	}

	/**
	 * 璁剧疆procurementNumber灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProcurementNumber(String value) {
		this.procurementNumber = value;
	}

	/**
	 * 鑾峰彇channelNumber灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChannelNumber() {
		return channelNumber;
	}

	/**
	 * 璁剧疆channelNumber灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChannelNumber(String value) {
		this.channelNumber = value;
	}

	/**
	 * 鑾峰彇senderCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSenderCode() {
		return senderCode;
	}

	/**
	 * 璁剧疆senderCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSenderCode(String value) {
		this.senderCode = value;
	}

	/**
	 * 鑾峰彇paymentOrgCode灞炴�鐨勫�銆�
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPaymentOrgCode() {
		return paymentOrgCode;
	}

	/**
	 * 璁剧疆paymentOrgCode灞炴�鐨勫�銆�
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPaymentOrgCode(String value) {
		this.paymentOrgCode = value;
	}

}

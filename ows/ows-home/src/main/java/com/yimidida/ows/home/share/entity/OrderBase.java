package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;

/**
 * @author RHB
 *
 * 订单实体类
 */
public class OrderBase {
	private static final long serialVersionUID = 2032230581270266696L;
	@Id
	@Column("order_Id")
	private String orderId; //订单ID
	@Column("order_No")
	private String orderNo; //订单号
	@Column("consignor_Name")
	private String consignorName; //寄件人姓名
	@Column("consignor_Phone")
	private String consignorPhone; //寄件人电话(手机号/固定电话)
	@Column("consignor_Comp_Name")
	private String consignorCompName; //寄件公司
	@Column("consignor_Addr")
	private String consignorAddr; //寄件人地址
	@Column("addressee_Name")
	private String addresseeName; //收件人姓名
	@Column("addressee_Phone")
	private String addresseePhone; //收件人电话(手机号/固定电话)
	@Column("addressee_Addr")
	private String addresseeAddr; //收件人地址
	@Column("send_Way")
	private String sendWay; //寄件方式(S1-门到门,S2-港到门,S3-港到港)
	@Column("payment_Type_Code")
	private String paymentTypeCode; //付款类型:1-寄付,2-到付
	@Column("goods_Name")
	private String goodsName; //寄托物名称
	@Column("collection_Fee")
	private Double collectionFee; //代收款
	@Column("valuation_Fee")
	private Double valuationFee; //声明价值
	@Column("create_Tm")
	private Date createTm; //创建时间
	@Column("send_Tm")
	private Date sendTm; //寄件时间
	@Column("remark")
	private String remark; //备注
	@Column("dept_Code")
	private String deptCode; //营业网点
	@Column("order_State")
	private String orderState; //订单状态：1-有效；0-无效
	@Column("bank_Account")
	private String bankAccount; //银行账户
	@Column("bank_Type")
	private String bankType; //银行类型:0:无卡号,1-工商银行,2-浦发银行,3-农业银行,4-建设银行
	@Column("express_Fee")
	private Double expressFee; //代收运费
	@Column("weight_Qty")
	private Double weightQty; //重量
	@Column("volume")
	private Double volume; //体积
	@Column("dest_Zone_Code")
	private String destZoneCode; //目的地 
	@Column("quantity")
	private Integer quantity;//件数
	@Column("src_Dist_Code")
	private String srcDistCode; //区域代码
	@Column("card_Number")
	private String cardNumber; //发货人会员卡号
	@Column("card_Name")
	private String cardName; //发货人持卡人
	@Column("packing_Number")
	private String packingNumber; //货物包装及数量(例如：木箱-1-纸箱-2-纤袋-1)
	@Column("delivery")
	private String delivery; //送货(选中为1)
	@Column("deal_flg")
    private String dealFlg; //是否处理
	@Column("ORDER_CHANNEL")
	private String orderChannel;//订单来源
	/**
	 * 获取实体ID
	 *
	 * @return 实体ID
	 */
	private Long id;

	/**
	 * 获取资源编号
	 *
	 * @return 资源编号
	 */
	private String code;
	@Column("comp_code")
	private String compCode;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getConsignorName() {
		return consignorName;
	}

	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	public String getConsignorPhone() {
		return consignorPhone;
	}

	public void setConsignorPhone(String consignorPhone) {
		this.consignorPhone = consignorPhone;
	}

	public String getConsignorCompName() {
		return consignorCompName;
	}

	public void setConsignorCompName(String consignorCompName) {
		this.consignorCompName = consignorCompName;
	}

	public String getConsignorAddr() {
		return consignorAddr;
	}

	public void setConsignorAddr(String consignorAddr) {
		this.consignorAddr = consignorAddr;
	}

	public String getAddresseeName() {
		return addresseeName;
	}

	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}

	public String getAddresseePhone() {
		return addresseePhone;
	}

	public void setAddresseePhone(String addresseePhone) {
		this.addresseePhone = addresseePhone;
	}

	public String getAddresseeAddr() {
		return addresseeAddr;
	}

	public void setAddresseeAddr(String addresseeAddr) {
		this.addresseeAddr = addresseeAddr;
	}

	public String getSendWay() {
		return sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getCollectionFee() {
		return collectionFee;
	}

	public void setCollectionFee(Double collectionFee) {
		this.collectionFee = collectionFee;
	}

	public Double getValuationFee() {
		return valuationFee;
	}

	public void setValuationFee(Double valuationFee) {
		this.valuationFee = valuationFee;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getSendTm() {
		return sendTm;
	}

	public void setSendTm(Date sendTm) {
		this.sendTm = sendTm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Double getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(Double expressFee) {
		this.expressFee = expressFee;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDealFlg() {
		return dealFlg;
	}

	public void setDealFlg(String dealFlg) {
		this.dealFlg = dealFlg;
	}

	public Double getWeightQty() {
		return weightQty;
	}

	public void setWeightQty(Double weightQty) {
		this.weightQty = weightQty;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getDestZoneCode() {
		return destZoneCode;
	}

	public void setDestZoneCode(String destZoneCode) {
		this.destZoneCode = destZoneCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Boolean hasCompCode() {
		return Boolean.FALSE;
	}

	public String getSrcDistCode() {
		return srcDistCode;
	}

	public void setSrcDistCode(String srcDistCode) {
		this.srcDistCode = srcDistCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getPackingNumber() {
		return packingNumber;
	}

	public void setPackingNumber(String packingNumber) {
		this.packingNumber = packingNumber;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}
	
	
	
}

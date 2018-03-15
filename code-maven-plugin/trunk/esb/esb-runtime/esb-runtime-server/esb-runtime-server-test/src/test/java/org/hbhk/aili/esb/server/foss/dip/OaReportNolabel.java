package org.hbhk.aili.esb.server.foss.dip;

import java.util.Date;

/**
 * The Class OaReportNolabel.
 * 
 * @author xiejianghao
 * @version 2012-09-19 <br>
 *          无标签多货
 */

public class OaReportNolabel {

	// 品名
	/** The goods name. */
	private String goodsName;

	// 品牌
	/** The brand. */
	private String brand;

	// 品类
	/** The categorys. */
	private String categorys;

	// 包装
	/** The goods packed. */
	private String goodsPacked;

	// 内物属性
	/** The attributes. */
	private String attributes;

	// 重量
	/** The weight. */
	private double weight;

	// 体积
	/** The volume. */
	private double volume;

	// 交接单号
	/** The replay bill. */
	private String replayBill;

	// 上报人工号
	/** The user id. */
	private String userId;

	// 包装关键字
	/** The package key. */
	private String packageKey;

	// 手写关键字
	/** The hand key. */
	private String handKey;

	// 同车少货备注
	/** The less goods note. */
	private String lessGoodsNote;

	// 发现货区
	/** The find place. */
	private String findPlace;

	// 货区发现时间
	/** The gaf time. */
	private Date gafTime;

	// 上一环节部门
	/** The dept name. */
	private String deptName;

	// 上一环节部门编号
	/** The dept orgid. */
	private String deptOrgid;

	// 短信通知对象
	/** The notice objects. */
	private String noticeObjects;

	// 正面照地址
	/** The image zm. */
	private String imageZm;

	// 整体照地址
	/** The image zt. */
	private String imageZt;

	// 内物照地址
	/** The image nw. */
	private String imageNw;

	// 事件经过
	/** The event report. */
	private String eventReport;

	// 无标签运单号
	/** The no label way bill. */
	private String noLabelWayBill;

	// 无标签流水号
	/** The no label serail. */
	private String noLabelSerail;

	// ISLESSGOODS,ISREPLAYBILL
	// 是否同车少货
	/** The is less goods. */
	private String isLessGoods;

	// 时候有交接单
	/** The is replay bill. */
	private String isReplayBill;

	// 责任事业部标杆编码

	/** The final sys code. */
	private String finalSysCode;

	/**
	 * Instantiates a new oa report nolabel.
	 */
	public OaReportNolabel() {

	}

	/**
	 * Gets the final sys code.
	 * 
	 * @return the final sys code
	 */
	public String getFinalSysCode() {
		return finalSysCode;
	}

	/**
	 * Sets the final sys code.
	 * 
	 * @param finalSysCode
	 *            the new final sys code
	 */
	public void setFinalSysCode(String finalSysCode) {
		this.finalSysCode = finalSysCode;
	}

	/**
	 * Gets the goods name.
	 * 
	 * @return the goods name
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * Sets the goods name.
	 * 
	 * @param goodsName
	 *            the new goods name
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * Gets the brand.
	 * 
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 * 
	 * @param brand
	 *            the new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the categorys.
	 * 
	 * @return the categorys
	 */
	public String getCategorys() {
		return categorys;
	}

	/**
	 * Sets the categorys.
	 * 
	 * @param categorys
	 *            the new categorys
	 */
	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}

	/**
	 * Gets the goods packed.
	 * 
	 * @return the goods packed
	 */
	public String getGoodsPacked() {
		return goodsPacked;
	}

	/**
	 * Sets the goods packed.
	 * 
	 * @param goodsPacked
	 *            the new goods packed
	 */
	public void setGoodsPacked(String goodsPacked) {
		this.goodsPacked = goodsPacked;
	}

	/**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
	public String getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 * 
	 * @param attributes
	 *            the new attributes
	 */
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the weight.
	 * 
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 * 
	 * @param weight
	 *            the new weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Gets the volume.
	 * 
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Sets the volume.
	 * 
	 * @param volume
	 *            the new volume
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}

	/**
	 * Gets the replay bill.
	 * 
	 * @return the replay bill
	 */
	public String getReplayBill() {
		return replayBill;
	}

	/**
	 * Sets the replay bill.
	 * 
	 * @param replayBill
	 *            the new replay bill
	 */
	public void setReplayBill(String replayBill) {
		this.replayBill = replayBill;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the package key.
	 * 
	 * @return the package key
	 */
	public String getPackageKey() {
		return packageKey;
	}

	/**
	 * Sets the package key.
	 * 
	 * @param packageKey
	 *            the new package key
	 */
	public void setPackageKey(String packageKey) {
		this.packageKey = packageKey;
	}

	/**
	 * Gets the hand key.
	 * 
	 * @return the hand key
	 */
	public String getHandKey() {
		return handKey;
	}

	/**
	 * Sets the hand key.
	 * 
	 * @param handKey
	 *            the new hand key
	 */
	public void setHandKey(String handKey) {
		this.handKey = handKey;
	}

	/**
	 * Gets the less goods note.
	 * 
	 * @return the less goods note
	 */
	public String getLessGoodsNote() {
		return lessGoodsNote;
	}

	/**
	 * Sets the less goods note.
	 * 
	 * @param lessGoodsNote
	 *            the new less goods note
	 */
	public void setLessGoodsNote(String lessGoodsNote) {
		this.lessGoodsNote = lessGoodsNote;
	}

	/**
	 * Gets the find place.
	 * 
	 * @return the find place
	 */
	public String getFindPlace() {
		return findPlace;
	}

	/**
	 * Sets the find place.
	 * 
	 * @param findPlace
	 *            the new find place
	 */
	public void setFindPlace(String findPlace) {
		this.findPlace = findPlace;
	}

	/**
	 * Gets the gaf time.
	 * 
	 * @return the gaf time
	 */
	public Date getGafTime() {

		return gafTime;
	}

	/**
	 * Sets the gaf time.
	 * 
	 * @param gafTime
	 *            the new gaf time
	 */
	public void setGafTime(Date gafTime) {
		this.gafTime = gafTime;
	}

	/**
	 * Gets the dept name.
	 * 
	 * @return the dept name
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * Sets the dept name.
	 * 
	 * @param deptName
	 *            the new dept name
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * Gets the dept orgid.
	 * 
	 * @return the dept orgid
	 */
	public String getDeptOrgid() {
		return deptOrgid;
	}

	/**
	 * Sets the dept orgid.
	 * 
	 * @param deptOrgid
	 *            the new dept orgid
	 */
	public void setDeptOrgid(String deptOrgid) {
		this.deptOrgid = deptOrgid;
	}

	/**
	 * Gets the notice objects.
	 * 
	 * @return the notice objects
	 */
	public String getNoticeObjects() {
		return noticeObjects;
	}

	/**
	 * Sets the notice objects.
	 * 
	 * @param noticeObjects
	 *            the new notice objects
	 */
	public void setNoticeObjects(String noticeObjects) {
		this.noticeObjects = noticeObjects;
	}

	/**
	 * Gets the image zm.
	 * 
	 * @return the image zm
	 */
	public String getImageZm() {
		return imageZm;
	}

	/**
	 * Sets the image zm.
	 * 
	 * @param imageZm
	 *            the new image zm
	 */
	public void setImageZm(String imageZm) {
		this.imageZm = imageZm;
	}

	/**
	 * Gets the image zt.
	 * 
	 * @return the image zt
	 */
	public String getImageZt() {
		return imageZt;
	}

	/**
	 * Sets the image zt.
	 * 
	 * @param imageZt
	 *            the new image zt
	 */
	public void setImageZt(String imageZt) {
		this.imageZt = imageZt;
	}

	/**
	 * Gets the image nw.
	 * 
	 * @return the image nw
	 */
	public String getImageNw() {
		return imageNw;
	}

	/**
	 * Sets the image nw.
	 * 
	 * @param imageNw
	 *            the new image nw
	 */
	public void setImageNw(String imageNw) {
		this.imageNw = imageNw;
	}

	/**
	 * Gets the event report.
	 * 
	 * @return the event report
	 */
	public String getEventReport() {
		return eventReport;
	}

	/**
	 * Sets the event report.
	 * 
	 * @param eventReport
	 *            the new event report
	 */
	public void setEventReport(String eventReport) {
		this.eventReport = eventReport;
	}

	/**
	 * Gets the no label way bill.
	 * 
	 * @return the no label way bill
	 */
	public String getNoLabelWayBill() {
		return noLabelWayBill;
	}

	/**
	 * Sets the no label way bill.
	 * 
	 * @param noLabelWayBill
	 *            the new no label way bill
	 */
	public void setNoLabelWayBill(String noLabelWayBill) {
		this.noLabelWayBill = noLabelWayBill;
	}

	/**
	 * Gets the no label serail.
	 * 
	 * @return the no label serail
	 */
	public String getNoLabelSerail() {
		return noLabelSerail;
	}

	/**
	 * Sets the no label serail.
	 * 
	 * @param noLabelSerail
	 *            the new no label serail
	 */
	public void setNoLabelSerail(String noLabelSerail) {
		this.noLabelSerail = noLabelSerail;
	}

	/**
	 * Gets the checks if is less goods.
	 * 
	 * @return the checks if is less goods
	 */
	public String getIsLessGoods() {
		return isLessGoods;
	}

	/**
	 * Sets the checks if is less goods.
	 * 
	 * @param isLessGoods
	 *            the new checks if is less goods
	 */
	public void setIsLessGoods(String isLessGoods) {
		this.isLessGoods = isLessGoods;
	}

	/**
	 * Gets the checks if is replay bill.
	 * 
	 * @return the checks if is replay bill
	 */
	public String getIsReplayBill() {
		return isReplayBill;
	}

	/**
	 * Sets the checks if is replay bill.
	 * 
	 * @param isReplayBill
	 *            the new checks if is replay bill
	 */
	public void setIsReplayBill(String isReplayBill) {
		this.isReplayBill = isReplayBill;
	}

}

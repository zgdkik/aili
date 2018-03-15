package org.hbhk.aili.org.share.entity;

import java.util.Date;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_org_dept")
public class DepartmentEntity extends BizBaseEntity {

	private static final long serialVersionUID = -8090529007475424877L;
	// 组织编号
	@Column("dept_code")
	private String deptCode;
	// 组织名称
	@Column(value="dept_name",like=true)
	private String deptName;

	@Column("parent_dept_code")
	private String parentDeptCode;
	// 组织拼音
	private String pinyin;
	// 组织负责人
	@Column("leader")
	private String leader;
	// 联系电话
	@Column("dept_telephone")
	private String deptTelephone;
	// 传真
	private String deptFax;
	// 邮编号码
	private String zipCode;
	// 部门地址
	@Column("address")
	private String address;
	// 启用日期
	private Date beginTime;
	// 作废日期
	private Date endTime;
	// 省份编码
	private String provCode;
	// 城市编码
	private String cityCode;
	// 区县
	private String countyCode;
	// 部门服务区坐标编号
	private String deptCoordinate;
	// 部门简称
	private String orgSimpleName;
	// 全路径
	private String fullPath;
	// 数据版本号
	private Long versionNo;
	// 地区编码默认拼音
	private String areaCode;
	// 组织邮箱
	private String deptEmail;
	// 组织性质
	private String deptAttribute;
	// 已封存系统
	private String canceledSystems;
	// EHR部门编码
	private String ehrDeptCode;
	// 部门英文简称
	private String orgEnSimple;
	// 部门地址
	private String orgLevel;
	// 部门面积
	private double deptArea;
	// 显示顺序
	private Integer displayOrder;
	// 部门层级
	private Integer deptLevel;
	// 是否叶子节点
	@Column("isLeaf")
	private String isLeaf;
	// 部门描述
	private String deptDesc;
	// 所属成本中心编码
	private String costCenterCode;
	// 是否事业部
	private String isDivision;
	// 是否大区
	private String isBigRegion;
	// 是否小区
	private String isSmallRegion;
	// 变动类型, 1-新增, 2-修改, 3-异动, 4-待撤销, 5-已撤销
	private String changeType;
	// 变动时间
	private Date changeDate;

	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getDeptTelephone() {
		return deptTelephone;
	}

	public void setDeptTelephone(String deptTelephone) {
		this.deptTelephone = deptTelephone;
	}

	public String getDeptFax() {
		return deptFax;
	}

	public void setDeptFax(String deptFax) {
		this.deptFax = deptFax;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getProvCode() {
		return provCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public String getDeptCoordinate() {
		return deptCoordinate;
	}

	public String getOrgSimpleName() {
		return orgSimpleName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getDeptEmail() {
		return deptEmail;
	}

	public String getDeptAttribute() {
		return deptAttribute;
	}

	public String getCanceledSystems() {
		return canceledSystems;
	}

	public String getEhrDeptCode() {
		return ehrDeptCode;
	}

	public String getOrgEnSimple() {
		return orgEnSimple;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public void setDeptCoordinate(String deptCoordinate) {
		this.deptCoordinate = deptCoordinate;
	}

	public void setOrgSimpleName(String orgSimpleName) {
		this.orgSimpleName = orgSimpleName;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setDeptEmail(String deptEmail) {
		this.deptEmail = deptEmail;
	}

	public void setDeptAttribute(String deptAttribute) {
		this.deptAttribute = deptAttribute;
	}

	public void setCanceledSystems(String canceledSystems) {
		this.canceledSystems = canceledSystems;
	}

	public void setEhrDeptCode(String ehrDeptCode) {
		this.ehrDeptCode = ehrDeptCode;
	}

	public void setOrgEnSimple(String orgEnSimple) {
		this.orgEnSimple = orgEnSimple;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

	public double getDeptArea() {
		return deptArea;
	}

	public void setDeptArea(double deptArea) {
		this.deptArea = deptArea;
	}

	public String getChangeType() {
		return changeType;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public String getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(String costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getIsDivision() {
		return isDivision;
	}

	public void setIsDivision(String isDivision) {
		this.isDivision = isDivision;
	}

	public String getIsBigRegion() {
		return isBigRegion;
	}

	public void setIsBigRegion(String isBigRegion) {
		this.isBigRegion = isBigRegion;
	}

	public String getIsSmallRegion() {
		return isSmallRegion;
	}

	public void setIsSmallRegion(String isSmallRegion) {
		this.isSmallRegion = isSmallRegion;
	}

	public String getParentDeptCode() {
		return parentDeptCode;
	}

	public void setParentDeptCode(String parentDeptCode) {
		this.parentDeptCode = parentDeptCode;
	}

}

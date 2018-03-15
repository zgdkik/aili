package org.hbhk.hls.org.share.entity;

import java.util.Date;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_org_emp")
public class EmployeeEntity extends BizBaseEntity {

	private static final long serialVersionUID = -3786055567600558333L;
	// 职员编号
	@Column("emp_code")
	private String empCode;
	
	// 组织标杆编码
	@Column("dept_code")
	private String deptCode;
	
	// 人员姓名
	@Column("emp_name")
	private String empName;
	// 性别
	@Column("gender")
	private String gender;
	// 职位
	@Column("position")
	private String position;
	// 生日
	@Column("birth_date")
	private Date birthDate;
	// 入职日期
	@Column("in_date")
	private Date inDate;
	// 离职日期
	@Column("out_date")
	private Date outDate;
	// 手机号码
	@Column("mobile_number")
	private String mobileNumber;
	
	// 办公电话
	private String offerTel;
	// 办公地址
	private String offerAddress;
	// 办公邮编
	private String offerZipCode;
	// 办公邮箱
	private String offerEmail;

	// 家庭电话
	private String homeTel;
	// 家庭地址
	private String homeAddress;
	// 家庭邮编
	private String homeZipCode;
	// 私人邮箱
	private String personEmail;
	// 工作描述
	private String workExp;
	// 备注
	private String remark;
	// 雇员拼音
	private String pinyin;

	// 身份证号
	private String identityCard;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmpCode() {
		return this.empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return this.outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOfferTel() {
		return this.offerTel;
	}

	public void setOfferTel(String offerTel) {
		this.offerTel = offerTel;
	}

	public String getOfferAddress() {
		return this.offerAddress;
	}

	public void setOfferAddress(String offerAddress) {
		this.offerAddress = offerAddress;
	}

	public String getOfferZipCode() {
		return this.offerZipCode;
	}

	public void setOfferZipCode(String offerZipCode) {
		this.offerZipCode = offerZipCode;
	}

	public String getOfferEmail() {
		return this.offerEmail;
	}

	public void setOfferEmail(String offerEmail) {
		this.offerEmail = offerEmail;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeZipCode() {
		return this.homeZipCode;
	}

	public void setHomeZipCode(String homeZipCode) {
		this.homeZipCode = homeZipCode;
	}

	public String getPersonEmail() {
		return this.personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getWorkExp() {
		return this.workExp;
	}

	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

/**
 * 用户信息表
 */
@Table("tb_ows_user")
public class OwsUser {
	@Id
	@Column("id")
	private String id;
	@Column("user_name")
	private String userName;//用户名
	@Column("password")
	private String password;//密码
	@Column("mobile")
	private String mobile;//手机
	@Column("email")
	private String email;//邮箱
	@Column("create_time")
	private Date createTime;//创建时间
	@Column("update_time")
	private Date updateTime;//修改时间
	@Column("status")
	private String status;//状态
	@Column("compCode")
	private String compCode;//公司
	@Column("realName")
	private String realName;//真实姓名
	@Column("companyName")
	private String companyName;//公司名称
	@Column("gender")
	private String gender;//性别
	@Column("areaCode")
	private String areaCode;//固定电话区号
	@Column("telephone")
	private String telephone;//固定电话号码
	@Column("checkAddress")
	private String checkAddress;//选择省市区
	@Column("concreteAddress")
	private String concreteAddress;//详细住址
	@Column("bankType")
	private String bankType;//银行类型
	@Column("bankNumber")
	private String bankNumber;//银行卡号
	@Column("mail_key")
	private String mailKey;//邮箱主键
	@Column("out_time")
	private String outTime;
	@Column("memberCard")
	private String memberCard;//会员卡卡号
	@Column("IDCard")
	private String IDCard;//身份证
	@Column("initialPhones")
	private String initialPhone;//初始手机号
	
	private String openid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCheckAddress() {
		return checkAddress;
	}
	public void setCheckAddress(String checkAddress) {
		this.checkAddress = checkAddress;
	}
	public String getConcreteAddress() {
		return concreteAddress;
	}
	public void setConcreteAddress(String concreteAddress) {
		this.concreteAddress = concreteAddress;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMemberCard() {
		return memberCard;
	}
	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}
	public String getMailKey() {
		return mailKey;
	}
	public void setMailKey(String mailKey) {
		this.mailKey = mailKey;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getInitialPhone() {
		return initialPhone;
	}
	public void setInitialPhone(String initialPhone) {
		this.initialPhone = initialPhone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}

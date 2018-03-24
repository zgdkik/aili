package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

/**
 * 投诉建议-客户回复
 * @author zhangm
 *
 */
@Table("tb_customer_reply")
public class CustomerReply {
	@Id
	@Column("id")
	private String id;//主键
	@Column("pid")
	private String pid;//父ID
	@Column("problem_types")
	private String problemTypes;//问题类型 1：咨询 2：建议 3：投诉
	@Column("name")
	private String name;//姓名
	@Column("mobile_phone")
	private String mobilePhone;//手机号
	@Column("email")
	private String email;//邮箱
	@Column("single_number")
	private String singleNumber;//单号
	@Column("problem_description")
	private String problemDescription;//问题描述
	@Column("customer_reply")
	private String customerReply;//客户回复
	@Column("problem_time")
	private Date problemTime;//提出问题时间
	@Column("userId")
	private String userId;//用户id
	@Column("create_user")
	private String createUser;//创建用户
	@Column("create_time")
	private Date createTime;//创建时间
	@Column("update_user")
	private String updateUser;//修改用户
	@Column("update_time")
	private Date updateTime;//修改时间
	@Column("status")
	private String status;//状态
	@Column("customer_reply_type")
	private String customerReplyType;//状态
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getProblemTypes() {
		return problemTypes;
	}
	public void setProblemTypes(String problemTypes) {
		this.problemTypes = problemTypes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSingleNumber() {
		return singleNumber;
	}
	public void setSingleNumber(String singleNumber) {
		this.singleNumber = singleNumber;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public String getCustomerReply() {
		return customerReply;
	}
	public void setCustomerReply(String customerReply) {
		this.customerReply = customerReply;
	}
	public Date getProblemTime() {
		return problemTime;
	}
	public void setProblemTime(Date problemTime) {
		this.problemTime = problemTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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
	public String getCustomerReplyType() {
		return customerReplyType;
	}
	public void setCustomerReplyType(String customerReplyType) {
		this.customerReplyType = customerReplyType;
	}
	
}

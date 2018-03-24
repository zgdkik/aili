package com.yimidida.ows.home.share.entity;

import java.io.Serializable;
import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

//招聘信息实体类
@Table("tb_recruit")
public class Recruit implements Serializable {

	private static final long serialVersionUID = 7946373522675173331L;
	@Id
	@Column("id")
	private String id;// 招聘信息ID自增长
	@Column("recruit_Title")
	private String recruitTitle;// 招聘标题
	@Column("recruit_Area")
	private String recruitArea;// 工作地点
	@Column("recruit_Category")
	private String recruitCategory;// 职业类别
	@Column("recruit_Mail")
	private String recruitMail;// 邮箱
	@Column("recruit_Count")
	private String recruitCount;// 招聘人数
	@Column("recruit_Job")
	private String recruitJob;// 工作职责内容
	@Column("recruit_Requirement")
	private String recruitRequirement;// 岗位要求
	@Column("recruit_Welfare")
	private String recruitWelfare;// 福利待遇
	@Column("recruit_Contact")
	private String recruitContact;// 联系方式
	@Column("recruit_Begindate")
	private Date recruitBegindate;// 招聘开始日期
	@Column("recruit_Enddate")
	private Date recruitEnddate;// 招聘结束日期
	@Column("recruit_Status")
	private Integer recruitStatus;// 1：显示；0：隐藏
	@Column("create_Date")
	private Date createDate;// 创建日期
	@Column("create_User")
	private String createUser;// 创建人
	@Column("change_Date")
	private Date changeDate;// 修改日期
	@Column("change_User")
	private String changeUser;// 修改人
	@Column("compCode")
	private String compCode;// 修改人
	
	private String recruitStart;//前台显示开始招聘时间
	
	private String recruitEnd;//前台显示结束招聘时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecruitTitle() {
		return recruitTitle;
	}
	public void setRecruitTitle(String recruitTitle) {
		this.recruitTitle = recruitTitle;
	}
	public String getRecruitArea() {
		return recruitArea;
	}
	public void setRecruitArea(String recruitArea) {
		this.recruitArea = recruitArea;
	}
	public String getRecruitCategory() {
		return recruitCategory;
	}
	public void setRecruitCategory(String recruitCategory) {
		this.recruitCategory = recruitCategory;
	}
	public String getRecruitMail() {
		return recruitMail;
	}
	public void setRecruitMail(String recruitMail) {
		this.recruitMail = recruitMail;
	}
	public String getRecruitCount() {
		return recruitCount;
	}
	public void setRecruitCount(String recruitCount) {
		this.recruitCount = recruitCount;
	}
	public String getRecruitJob() {
		return recruitJob;
	}
	public void setRecruitJob(String recruitJob) {
		this.recruitJob = recruitJob;
	}
	public String getRecruitRequirement() {
		return recruitRequirement;
	}
	public void setRecruitRequirement(String recruitRequirement) {
		this.recruitRequirement = recruitRequirement;
	}
	public String getRecruitWelfare() {
		return recruitWelfare;
	}
	public void setRecruitWelfare(String recruitWelfare) {
		this.recruitWelfare = recruitWelfare;
	}
	public String getRecruitContact() {
		return recruitContact;
	}
	public void setRecruitContact(String recruitContact) {
		this.recruitContact = recruitContact;
	}
	public Date getRecruitBegindate() {
		return recruitBegindate;
	}
	public void setRecruitBegindate(Date recruitBegindate) {
		this.recruitBegindate = recruitBegindate;
	}
	public Date getRecruitEnddate() {
		return recruitEnddate;
	}
	public void setRecruitEnddate(Date recruitEnddate) {
		this.recruitEnddate = recruitEnddate;
	}
	public Integer getRecruitStatus() {
		return recruitStatus;
	}
	public void setRecruitStatus(Integer recruitStatus) {
		this.recruitStatus = recruitStatus;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	public String getRecruitStart() {
		return recruitStart;
	}
	public void setRecruitStart(String recruitStart) {
		this.recruitStart = recruitStart;
	}
	public String getRecruitEnd() {
		return recruitEnd;
	}
	public void setRecruitEnd(String recruitEnd) {
		this.recruitEnd = recruitEnd;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	
	
	
	
	

}

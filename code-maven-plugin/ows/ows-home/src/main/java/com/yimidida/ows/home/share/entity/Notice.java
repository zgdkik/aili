package com.yimidida.ows.home.share.entity;

import java.io.Serializable;
import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;
//新闻动态
@Table("tb_notice")
public class Notice implements Serializable {

	private static final long serialVersionUID = 549292076797422652L;
	@Id
	@Column("id")
	private String id;//主键
	@Column("notice_title")
	private String noticeTitle;//标题
	@Column("notice_summary")
	private String noticeSummary;//摘要
	@Column("notice_type")
	private String noticeType;//类型
	@Column("notice_img")
	private String noticeImg;//图片
	@Column("notice_content")
	//用;split; 分割 前者纯文本 后者html内容
	private String noticeContent;//内容
	@Column("notice_status")
	private String noticeStatus;//状态
	@Column("create_date")
	private Date createDate;//创建时间
	@Column("create_user")
	private String createUser;//创建用户
	@Column("change_date")
	private Date changeDate;//修改时间
	@Column("change_user")
	private String changeUser;//修改用户
	@Column("notice_running")
	private String noticeRunning;//消息是否进行
	@Column("comp_code")
	private String compCode;//公司
	@Column("release_time")
	private Date releaseTime;//发布时间
	
	
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	private String preId;
	
	private String nextId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeSummary() {
		return noticeSummary;
	}
	public void setNoticeSummary(String noticeSummary) {
		this.noticeSummary = noticeSummary;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getNoticeImg() {
		return noticeImg;
	}
	public void setNoticeImg(String noticeImg) {
		this.noticeImg = noticeImg;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
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
	public String getNoticeRunning() {
		return noticeRunning;
	}
	public void setNoticeRunning(String noticeRunning) {
		this.noticeRunning = noticeRunning;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getPreId() {
		return preId;
	}
	public void setPreId(String preId) {
		this.preId = preId;
	}
	public String getNextId() {
		return nextId;
	}
	public void setNextId(String nextId) {
		this.nextId = nextId;
	}
	
	
	
}

package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

//首页banner
@Table("tb_banner")
public class HomePage {
	@Id
	@Column("id")
	private String id;  //主键
	@Column("banner_title")
	private String bannerTitle;//标题
	@Column("sub_title")
	private String subTitle;//子标题-banner类型选择-用于后台的下拉框
	@Column("title_desc")
	private String titleDesc;//标题描述
	@Column("banner_url")
	private String bannerUrl;//链接
	@Column("banner_pic")
	private String bannerPic;//图片
	@Column("pic2")
	private String pic2;//图片2
	@Column("content")
	private String content;//备注
	@Column("create_date")
	private Date createDate;//创建时间
	@Column("change_date")
	private Date changeDate;//修改时间
	@Column("create_user")
	private String createUser;//创建用户
	@Column("change_user")
	private String changeUser;//修改用户
	@Column("comp_code")
	private String compCode;//公司
	@Column("banner_type")
	private String bannerType;//类型1.首页banner 2.走进大道  3.产品服务 4.新闻资讯 5.客户中心 6.大道招聘
	@Column("banner_sort")
	private String bannerSort;//排序
	public String id() {
		return id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBannerTitle() {
		return bannerTitle;
	}
	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getTitleDesc() {
		return titleDesc;
	}
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getBannerPic() {
		return bannerPic;
	}
	public void setBannerPic(String bannerPic) {
		this.bannerPic = bannerPic;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getChangeUser() {
		return changeUser;
	}
	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}
	public String getCompCode() {
		return compCode;
	}
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	public String getBannerType() {
		return bannerType;
	}
	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}
	public String getBannerSort() {
		return bannerSort;
	}
	public void setBannerSort(String bannerSort) {
		this.bannerSort = bannerSort;
	}
	
}

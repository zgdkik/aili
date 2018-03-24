package com.yimidida.ows.home.share.entity;

import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

/**
 * 图片管理
 * @author zhangm
 *
 */
@Table("tb_img_manage")
public class ImgManage {
	
	@Id
	@Column("id")
	private String id;  //-主键
	
	@Column("type")
	private int type; //-类型
	
	@Column("sort")
	private String sort; //-顺序
	
	@Column("main_title")
	private String mainTitle; //-主标题

	@Column("sub_title")
	private String subTitle;  //-子标题

	@Column("img_url")
	private String imgUrl;    //图片地址url
	
	@Column("default_img")
	private String defaultImg;  //默认图片   (图片1)
	
	@Column("click_img")
	private String clickImg;    //鼠标放置上去转变的图片  (图片2)
	
	@Column("create_date")
	private Date createDate;  //创建时间
	
	@Column("change_date")
	private Date changeDate;  //修改时间

	@Column("create_user")
	private String createUser;  //创建用户

	@Column("change_user")
	private String changeUser;  //修改用户

	@Column("comp_code")
	private String compCode;    //所属公司

	@Column("status")
	private int status;      //状态
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public String getClickImg() {
		return clickImg;
	}

	public void setClickImg(String clickImg) {
		this.clickImg = clickImg;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

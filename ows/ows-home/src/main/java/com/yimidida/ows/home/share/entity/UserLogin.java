package com.yimidida.ows.home.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

//寄件人 、收货人 地址详情
@Table("tb_user_login")
public class UserLogin {
	@Id
	@Column("id")
	private String id;
	@Column("user_id")//用户id
	private String userId;
	@Column("openid")//微信号（加密后）
	private String openid;
	@Column("login_type")
	private Integer loginType;//登录类型 1官网 2微信
	@Column("is_loginout")//是否注销
	private Integer isLoginout;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	public Integer getIsLoginout() {
		return isLoginout;
	}
	public void setIsLoginout(Integer isLoginout) {
		this.isLoginout = isLoginout;
	}
	
	
	
	
}

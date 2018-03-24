package com.yimidida.ows.home.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

//地区-县级、区级
@Table("tb_area")
public class Area {
	
	@Id
	@Column("id")
	private String id;
	@Column("name")
	private String name;//区名，县名
	@Column("code")
	private String code;//编号
	@Column("cityCode")
	private String cityCode;//城市编号-市级
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}

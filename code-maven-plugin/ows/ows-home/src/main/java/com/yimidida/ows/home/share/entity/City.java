package com.yimidida.ows.home.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

//城市
@Table("tb_city")
public class City {
	@Id
	@Column("id")
	private String id;
	@Column("code")
	private String code;//编号
	@Column("name")
	private String name;//名字
	@Column("provinceCode")
	private String provinceCode;//对应省编号
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}

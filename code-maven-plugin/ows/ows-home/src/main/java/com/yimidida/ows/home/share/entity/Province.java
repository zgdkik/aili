package com.yimidida.ows.home.share.entity;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;
import org.mybatis.spring.annotation.Table;

//省
@Table("tb_province")
public class Province {
	@Id
	@Column("id")
	private String id;
	@Column("code")
	private String code;//编号
	@Column("name")
	private String name;//名字
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}

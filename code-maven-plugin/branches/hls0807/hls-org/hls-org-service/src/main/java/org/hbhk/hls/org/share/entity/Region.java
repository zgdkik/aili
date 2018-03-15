package org.hbhk.hls.org.share.entity;

import org.hbhk.aili.base.share.entity.BizBaseEntity;
import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_base_district")
public class Region extends BizBaseEntity {

	private static final long serialVersionUID = 3973097683439822626L;
	
	@Column("NAME")
	private String name;

	@Column("parent_id")
	private String parentId;

	@Column("LEVEL_TYPE")
	private String levelType;
	
	@Column("SHORT_NAME")
	private String shortName;
	
	@Column("CITY_CODE")
	private String cityCode;
	@Column("ZIP_CODE")
	private String zipCode;
	@Column("lng")
	private String lng;
	@Column("lat")
	private String lat;
	@Column("pinyin")
	private String pinyin;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
	
}

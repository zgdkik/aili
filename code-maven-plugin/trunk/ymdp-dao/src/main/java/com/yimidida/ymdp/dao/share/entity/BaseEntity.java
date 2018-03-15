package com.yimidida.ymdp.dao.share.entity;

import java.io.Serializable;
import java.util.Date;

import com.yimidida.ymdp.dao.server.annotation.Column;
import com.yimidida.ymdp.dao.server.annotation.Version;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 *
 */
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2837238245729082475L;
	/**
	 * 公司编码
	 */
	@Column("comp_code")
	private String compCode;

	/**
	 * 创建人
	 */
	@Column("creater")
	private String creater;
	/**
	 * 创建时间
	 */
	@Column("creater_time")
	private Date createrTime;
	/**
	 * 修改人
	 */
	@Column("modifier")
	private String modifier;
	/**
	 * 修改时间
	 */
	@Column("modifier_time")
	private Date modifierTime;

	/**
	 * 0是未删除 1是删除
	 */
	@Column("is_delete")
	private Integer isDelete;
	/**
	 * 最后修改时间
	 */
	@Column("latest_time")
	private Date latestTime;
	/**
	 * 数据版本号
	 */
	@Column("record_version")
	@Version
	private Long recordVersion;

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifierTime() {
		return modifierTime;
	}

	public void setModifierTime(Date modifierTime) {
		this.modifierTime = modifierTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(Date latestTime) {
		this.latestTime = latestTime;
	}

	public Long getRecordVersion() {
		return recordVersion;
	}

	public void setRecordVersion(Long recordVersion) {
		this.recordVersion = recordVersion;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

}

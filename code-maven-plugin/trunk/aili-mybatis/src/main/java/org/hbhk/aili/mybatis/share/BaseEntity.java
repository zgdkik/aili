package org.hbhk.aili.mybatis.share;

import java.io.Serializable;
import java.util.Date;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Version;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 *
 */
public class BaseEntity<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2837238245729082475L;

	private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();

	@Column("id")
	@Id
	private T id;
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
	@Column("create_time")
	private Date createTime;
	/**
	 * 修改人
	 */
	@Column("modifier")
	private String modifier;
	/**
	 * 修改时间
	 */
	@Column("modify_time")
	private Date modifyTime;

	/**
	 * 1是未删除 0是删除
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
	@Column("version")
	@Version
	private Long version;

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

}

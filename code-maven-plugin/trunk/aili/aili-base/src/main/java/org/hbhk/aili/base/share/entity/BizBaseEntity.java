package org.hbhk.aili.base.share.entity;

import java.util.Date;

import org.hbhk.aili.mybatis.server.annotation.Column;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24
 * 
 */
public class BizBaseEntity extends BaseEntity {
	private static final long serialVersionUID = 2088264869615355602L;
	/**
	 * 创建人
	 */
	@Column("create_user")
	private String createUser;
	/**
	 * 修改人
	 */
	@Column("update_user")
	private String updateUser;
	/**
	 * 修改时间
	 */
	@Column("update_time")
	private Date updateTime;
	/**
	 * 状态
	 */
	@Column("status")
	private Integer status = 1;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}

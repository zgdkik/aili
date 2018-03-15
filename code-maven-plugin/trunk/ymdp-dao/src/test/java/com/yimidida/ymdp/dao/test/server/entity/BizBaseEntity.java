package com.yimidida.ymdp.dao.test.server.entity;

import java.util.Date;

import com.yimidida.ymdp.dao.server.annotation.Column;
import com.yimidida.ymdp.dao.test.server.model.BaseEntity;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class BizBaseEntity<T> extends BaseEntity<T> {
	private static final long serialVersionUID = 2088264869615355602L;
	
	@Column("creat_user")
	private String creatUser;

	@Column("update_user")
	private String updateUser;
	@Column("update_time")
	private Date updateTime;
	@Column("status")
	private Integer status;
	
	public String getCreatUser() {
		return creatUser;
	}

	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
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

package org.mybatis.spring.entity;

import java.io.Serializable;
import java.util.Date;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Id;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 5009300140634580156L;
	@Column("id")
	@Id
	private Long id;
	
	@Column("create_time")
	private Date createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}

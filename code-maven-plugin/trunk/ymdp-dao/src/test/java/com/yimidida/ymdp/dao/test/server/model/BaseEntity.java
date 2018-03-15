package com.yimidida.ymdp.dao.test.server.model;

import java.io.Serializable;
import java.util.Date;

import com.yimidida.ymdp.dao.server.annotation.Column;
import com.yimidida.ymdp.dao.server.annotation.Id;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class BaseEntity<T> implements Serializable {
	private static final long serialVersionUID = 5009300140634580156L;
	@Column("id")
	@Id
	private T id;
	
	@Column("create_time")
	private Date createTime;
	
	
	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}

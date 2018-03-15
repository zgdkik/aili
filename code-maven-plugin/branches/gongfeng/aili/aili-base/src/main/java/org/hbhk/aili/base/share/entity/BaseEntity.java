package org.hbhk.aili.base.share.entity;

import java.io.Serializable;
import java.util.Date;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;

/**
 * 
 * @Description: mybatis增强处理
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 5009300140634580156L;
	/**
	 * 唯一标示
	 */
	@Column("id")
	@Id
	private String id;
	/**
	 * 创建时间
	 */
	@Column("create_time")
	private Date createTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}

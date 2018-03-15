package org.hbhk.aili.esb.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * The Class BaseEntity.
 * 
 * @Description 实体基类
 * @author HuangHua
 */
public abstract class BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1372509362360011358L;

	/** The id. */
	private String id;
	
	/** The create time. */
	private Date createTime;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the creates the time.
	 * 
	 * @return the creates the time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the creates the time.
	 * 
	 * @param createTime
	 *            the new creates the time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

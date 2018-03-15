package org.hbhk.aili.base.shared.domain;

import java.io.Serializable;
import java.util.Date;

public  class BaseEntity implements Serializable , IEntity, Comparable<IEntity> {

	private static final long serialVersionUID = 1372509362360011358L;

	private String id;// ID
	private Date createDate;// 创建日期
	private String createUser;// 创建人
	private Date modifyDate;// 修改日期
	private String modifyUser;// 修改人

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass().getPackage() != obj.getClass().getPackage()) {
			return false;
		}
		return false;
	}

	@Override
	public int compareTo(IEntity o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

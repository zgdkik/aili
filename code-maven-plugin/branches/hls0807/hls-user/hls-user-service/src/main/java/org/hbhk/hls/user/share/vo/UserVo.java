package org.hbhk.hls.user.share.vo;

import java.io.Serializable;
import java.util.Date;

import org.hbhk.hls.user.share.entity.UserEntity;

public class UserVo extends UserEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1190210408653007612L;

	private Date lastLoginTime;

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
	
}

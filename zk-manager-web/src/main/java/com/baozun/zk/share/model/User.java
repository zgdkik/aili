package com.baozun.zk.share.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -3681208566446207048L;
	private String user;
	private String pwd;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}

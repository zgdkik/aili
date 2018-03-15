package org.hbhk.home.core.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BaseModel;

@Table("t_home_user")
public class UserModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8424574960269728111L;
	@Column("userName")
	private String userName;
	@Column("name")
	private String name;
	@Column("pwd")
	private String pwd;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}
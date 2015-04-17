package org.hbhk.aili.workflow.share.model;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.model.BizBaseModel;

@Table(value="t_aili_user",dynamicInsert =true,dynamicUpdate=true)
public class UserInfo   extends BizBaseModel{

	private static final long serialVersionUID = 54122579931594962L;
	@Column("userCode")
	private String userCode;
	@Column("userName")
	private String userName;
	@Column("password")
	private String password;
	@Column("userHead")
	private String userHead;
	@Column("nickName")
	private String nickName;
	@Column("email")
	private String email;
	@Column("gender")
	private String gender;
	@Column("area")
	private String area;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}


	public String getUserHead() {
		return userHead;
	}

	public void setUserHead(String userHead) {
		this.userHead = userHead;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}
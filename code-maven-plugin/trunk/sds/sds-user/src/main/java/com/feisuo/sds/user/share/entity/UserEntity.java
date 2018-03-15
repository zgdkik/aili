package com.feisuo.sds.user.share.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.mybatis.spring.annotation.Column;
import org.mybatis.spring.annotation.Table;

import com.feisuo.sds.base.share.entity.BizBaseEntity;

/**
 * 
 * 用户实体对象
 */
@Table(value = "t_auth_user",dynamicUpdate=true)
public class UserEntity extends BizBaseEntity {

	private static final long serialVersionUID = 6334973378782503164L;
	
	// 用户登录名
	@Column("user_name")
	private String userName;
	
	// 用户登录密码
	@Column("password")
	private String password;
	
	//用户类型：1系统用户,2企业账户,3企业子账户
	@Column("type")
	private Integer type;
	
	// 用户最后登录时间
	@Column("last_login")
	private Date lastLogin;
	
	// 用户启用时间
	@Column("begin_time")
	private Date beginTime;
	
	// 用户禁用时间
	@Column("end_time")
	private Date endTime;
	
	//冻结状态1正常4冻结
	@Column("frozen_status")
	private int frozenStatus;
	/**
	 * 手机号
	 */
	@Column("phone")
	private String phone;
	
	// 用户所拥有的功能信息ID集合
	private Set<String> functionCodes;
	
	// 存入用户uri地址信息
	private Set<String> functionUris;

	// 用户所拥有的角色信息ID集合
	private Set<String> roleids;
	
	//角色信息
	private List<RoleEntity> roles;
	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public Set<String> getFunctionCodes() {
		return functionCodes;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public String getPassword() {
		return this.password;
	}

	public Set<String> getRoleids() {
		return roleids;
	}


	public String getUserName() {
		return this.userName;
	}

	public Set<String> queryAccessUris() {
		return this.functionUris;
	}

	public Set<String> getFunctionUris() {
		return functionUris;
	}

	public void setFunctionUris(Set<String> functionUris) {
		this.functionUris = functionUris;
	}


	public void setFunctionCodes(Set<String> functionCodes) {
		this.functionCodes = functionCodes;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleids(Set<String> roleids) {
		this.roleids = roleids;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getFrozenStatus() {
		return frozenStatus;
	}

	public void setFrozenStatus(int frozenStatus) {
		this.frozenStatus = frozenStatus;
	}

	@Override
	public String toString() {
		return "UserEntity [userName=" + userName + ", password=" + password
				+ ", type=" + type + ", lastLogin=" + lastLogin
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", frozenStatus=" + frozenStatus + ", phone=" + phone
				+ ", functionCodes=" + functionCodes + ", functionUris="
				+ functionUris + ", roleids=" + roleids + ", roles=" + roles
				+ "]";
	}
	
	
}

package org.hbhk.module.framework.shared.domain.security;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class UsersEntity implements java.io.Serializable {

	private Integer id;
	private Integer enable;
	private String password;
	private String username;
	private Set<RolesEntity> roles = new HashSet<RolesEntity>(0);

	public UsersEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolesEntity> roles) {
		this.roles = roles;
	}

}
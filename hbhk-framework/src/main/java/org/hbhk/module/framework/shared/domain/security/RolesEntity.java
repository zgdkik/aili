package org.hbhk.module.framework.shared.domain.security;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class RolesEntity implements java.io.Serializable {

	private Integer id;
	private Integer enable;
	private String name;
	private Set<UsersEntity> users = new HashSet<UsersEntity>(0);
	private Set<ResourcesEntity> resources = new HashSet<ResourcesEntity>(0);

	public RolesEntity() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<UsersEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UsersEntity> users) {
		this.users = users;
	}

	public Set<ResourcesEntity> getResources() {
		return resources;
	}

	public void setResources(Set<ResourcesEntity> resources) {
		this.resources = resources;
	}

}
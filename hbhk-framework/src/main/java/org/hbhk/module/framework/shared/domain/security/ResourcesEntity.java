package org.hbhk.module.framework.shared.domain.security;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class ResourcesEntity implements java.io.Serializable {

	private Integer id;
	private String url;
	private Integer priority;
	private Integer type;
	private String name;
	private String memo;
	private Set<RolesEntity> roles = new HashSet<RolesEntity>(0);

	public ResourcesEntity() {
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RolesEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RolesEntity> roles) {
		this.roles = roles;
	}

}
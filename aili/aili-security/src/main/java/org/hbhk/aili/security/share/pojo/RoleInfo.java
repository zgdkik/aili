package org.hbhk.aili.security.share.pojo;

import java.util.Set;

public class RoleInfo implements java.io.Serializable {

	private static final long serialVersionUID = 4218930427867063297L;
	private String id;
	private String code;
	private boolean enable;
	private String name;
	private Set<ResourceInfo> resources;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ResourceInfo> getResources() {
		return resources;
	}

	public void setResources(Set<ResourceInfo> resources) {
		this.resources = resources;
	}

}
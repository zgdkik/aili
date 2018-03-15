package org.hbhk.maikkr.core.shared.pojo;

import java.io.Serializable;

public class ProvinceInfo implements Serializable {

	private static final long serialVersionUID = -8138091418757937439L;
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

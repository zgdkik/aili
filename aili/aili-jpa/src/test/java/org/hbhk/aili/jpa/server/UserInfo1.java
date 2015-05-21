package org.hbhk.aili.jpa.server;

import org.hbhk.aili.jpa.server.annotation.Column;
import org.hbhk.aili.jpa.share.model.BaseModel;

public class UserInfo1 extends BaseModel {
	private static final long serialVersionUID = 6767860047837579053L;

	@Column("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

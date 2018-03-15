package com.yimidida.ymdp.dao.test.server;

import com.yimidida.ymdp.dao.server.annotation.Column;
import com.yimidida.ymdp.dao.test.server.model.BaseEntity;

public class UserInfo1 extends BaseEntity {
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

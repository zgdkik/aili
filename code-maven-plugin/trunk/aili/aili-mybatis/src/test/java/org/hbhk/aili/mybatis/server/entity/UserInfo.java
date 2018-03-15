package org.hbhk.aili.mybatis.server.entity;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table(value="t_aili_user",dynamicInsert =true,dynamicUpdate=true)
public class UserInfo extends BizBaseEntity {
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

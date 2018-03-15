package com.yimidida.ymdp.dao.test.server.entity;

import com.yimidida.ymdp.dao.server.annotation.Column;
import com.yimidida.ymdp.dao.server.annotation.Table;
import com.yimidida.ymdp.dao.server.annotation.Version;

@Table(value="t_aili_user",dynamicInsert =true,dynamicUpdate=true)
public class UserInfo extends BizBaseEntity<Long> {
	private static final long serialVersionUID = 6767860047837579053L;

	@Column("name")
	private String name;
	
	@Version
	@Column("version1")
	private String version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
}

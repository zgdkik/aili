package org.hbhk.aili.mybatis.server.entity;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;
import org.hbhk.aili.mybatis.share.BaseEntity;

@Table(value="t_aili_test",dynamicInsert =true,dynamicUpdate=true)
public class UserInfo extends BaseEntity<String> {
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

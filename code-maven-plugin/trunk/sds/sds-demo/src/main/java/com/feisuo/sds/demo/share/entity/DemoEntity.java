package com.feisuo.sds.demo.share.entity;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Table;

import com.feisuo.sds.base.share.entity.BizBaseEntity;

@Table("t_sds_demo")
public class DemoEntity extends BizBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7992559924364953256L;

	@Column(value="name",like=true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package org.hbhk.aili.jpa.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hbhk.aili.jpa.share.model.BizBaseModel;

@Entity
@Table
public class UserInfo extends BizBaseModel {
	private static final long serialVersionUID = 6767860047837579053L;

	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

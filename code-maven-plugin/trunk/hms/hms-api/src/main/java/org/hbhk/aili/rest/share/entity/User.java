package org.hbhk.aili.rest.share.entity;

import java.io.Serializable;

import org.hbhk.aili.mybatis.server.annotation.Column;
import org.hbhk.aili.mybatis.server.annotation.Id;
import org.hbhk.aili.mybatis.server.annotation.Table;

@Table("t_hms_test")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6716505264184063293L;
	@Id
	@Column("id")
	private  String id;
	
	@Column("name")
	private  String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	

}

package com.feisuo.yhhc.module.bseservice_gis.api.shared.dto;

import java.io.Serializable;


public class CityDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**城市Id*/
	private String id;
	/**城市名称*/
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

package com.yimidida.ows.base.share.vo;

import java.io.Serializable;

public class SearchSelectVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4419188480151612558L;
	
	private String id;
	
	private String name;

	private String descp;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package com.yimidida.ows.user.share.vo;

import java.io.Serializable;

public class TreeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8490904335779922143L;

	private String id;

	private String name;

	private boolean isParent;

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

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

}

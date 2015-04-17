package com.baozun.zk.share.model;

import java.io.Serializable;

public class Ztree implements Serializable {
	private static final long serialVersionUID = -6491361558567682227L;
	public int id;
	public Integer pId;
	public String name;
	public boolean open;
	public String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

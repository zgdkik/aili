package org.hbhk.aili.cache.encache;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -3383897298362291845L;
	
	private Integer id;
	
	private String name;
	
	private String no;

	
	public User(Integer id, String name, String no) {
		super();
		this.id = id;
		this.name = name;
		this.no = no;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	
	

}

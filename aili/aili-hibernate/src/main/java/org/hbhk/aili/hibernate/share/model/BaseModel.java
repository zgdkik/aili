package org.hbhk.aili.hibernate.share.model;

import java.io.Serializable;

public class BaseModel implements Serializable {
	private static final long serialVersionUID = 5009300140634580156L;
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}

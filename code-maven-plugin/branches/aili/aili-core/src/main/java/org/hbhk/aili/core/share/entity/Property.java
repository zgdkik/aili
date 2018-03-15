package org.hbhk.aili.core.share.entity;

import java.io.Serializable;

public class Property implements Serializable {
	private static final long serialVersionUID = 6720479979359573162L;
	
	private String name;
	
	private String value;
	
	private String ref;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	

}

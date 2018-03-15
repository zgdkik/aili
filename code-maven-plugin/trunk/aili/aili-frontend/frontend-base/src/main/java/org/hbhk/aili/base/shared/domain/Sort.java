package org.hbhk.aili.base.shared.domain;

import java.io.Serializable;

public class Sort implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 545597386837897868L;

	private String property;
	
	private String direction;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}

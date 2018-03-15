package org.hbhk.aili.route.jms.share;

import java.io.Serializable;

import org.apache.camel.Component;

public class ComponentInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5007850688325923720L;
	
	private Component component;
	
	private String name;

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

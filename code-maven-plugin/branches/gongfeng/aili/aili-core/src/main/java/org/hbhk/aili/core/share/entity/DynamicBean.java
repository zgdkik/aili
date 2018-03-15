package org.hbhk.aili.core.share.entity;

import java.io.Serializable;
import java.util.List;

public class DynamicBean implements Serializable{

	private static final long serialVersionUID = 7715603457323000899L;
	
	private String id;
	
	private Class<?> cls;

	private List<Property> properties;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	
	

}

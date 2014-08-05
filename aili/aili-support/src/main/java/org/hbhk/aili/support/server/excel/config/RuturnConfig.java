package org.hbhk.aili.support.server.excel.config;

import java.util.HashMap;
import java.util.Map;

import org.hbhk.aili.support.server.excel.entity.RuturnPropertyParam;

public class RuturnConfig {
	private String className = null;

	private Map<String, RuturnPropertyParam> propertyMap = new HashMap<String, RuturnPropertyParam>();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, RuturnPropertyParam> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<String, RuturnPropertyParam> propertyMap) {
		this.propertyMap = propertyMap;
	}

}

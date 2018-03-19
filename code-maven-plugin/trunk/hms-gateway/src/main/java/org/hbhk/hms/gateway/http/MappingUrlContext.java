package org.hbhk.hms.gateway.http;

import java.util.Map;

public class MappingUrlContext {

	private String app;
	
	private Map<String, String> urlMap;
	
	public Map<String, String> getUrlMap() {
		return urlMap;
	}

	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	
}

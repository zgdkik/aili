package org.hbhk.hms.gateway.http.bean;

import java.io.Serializable;
import java.util.List;

public class HttpRouteInfo implements Serializable {

	private static final long serialVersionUID = 1878396409479801850L;

	private List<HttpAppInfo> apps;

	public List<HttpAppInfo> getApps() {
		return apps;
	}

	public void setApps(List<HttpAppInfo> apps) {
		this.apps = apps;
	}
	
	

}

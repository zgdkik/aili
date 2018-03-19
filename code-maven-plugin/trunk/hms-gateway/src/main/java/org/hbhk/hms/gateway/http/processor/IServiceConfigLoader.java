package org.hbhk.hms.gateway.http.processor;

import java.util.List;

import org.hbhk.hms.gateway.http.bean.HttpAppInfo;

public interface IServiceConfigLoader {
	
	List<HttpAppInfo> loadALl();
	
	String load(String url);

}

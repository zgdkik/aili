package org.hbhk.aili.route.http.processor;

import java.util.List;

import org.hbhk.aili.route.http.bean.HttpAppInfo;

public interface IServiceConfigLoader {
	
	List<HttpAppInfo> loadALl();
	
	String load(String url);

}

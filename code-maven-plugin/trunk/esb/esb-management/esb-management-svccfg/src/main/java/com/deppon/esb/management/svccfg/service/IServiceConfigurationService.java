package com.deppon.esb.management.svccfg.service;

import java.util.List;

import com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfig;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute;

public interface IServiceConfigurationService {
	
	/** 新增服务 */
	public boolean addServiceConfig(
			List<ESBServiceConfig> esbServiceConfigs,List<ESBServiceRoute> esbServiceRoutes);
	
}

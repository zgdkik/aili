package com.deppon.esb.management.svccfg.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.esb.management.svccfg.dao.IRouteConfigurationDao;
import com.deppon.esb.management.svccfg.dao.IServiceConfigurationDao;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfig;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute;
import com.deppon.esb.management.svccfg.service.IServiceConfigurationService;

@Service
@Transactional
public class ServiceConfigurationService implements IServiceConfigurationService{

	@Resource
	private IServiceConfigurationDao serviceConfigurationDao;
	
	@Resource
	private IRouteConfigurationDao routeConfigurationDao;

	@Override
	public boolean addServiceConfig(List<ESBServiceConfig> esbServiceConfigs,
			List<ESBServiceRoute> esbServiceRoutes) {
		boolean flag = false;
		int config_result = 0;
		int route_result = 0;
		
		//新增服务配置
		for(ESBServiceConfig esbServiceConfig : esbServiceConfigs){
			config_result += serviceConfigurationDao.addServiceConfig(esbServiceConfig);
		}
		
		
		//新增路由配置
		for(ESBServiceRoute esbServiceRoute : esbServiceRoutes){
			route_result += routeConfigurationDao.addRouteConfig(esbServiceRoute);
		}
		
		
		
		
		if((config_result == 1 && route_result == 2)|| (config_result == 2 && route_result == 2)){
			flag = !flag;
		}
		
		return flag;
	}

}

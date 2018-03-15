package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfig;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute;

public interface IServiceConfigurationDao {
	
	/** 新增服务配置 */
	public int addServiceConfig(ESBServiceConfig esbServiceConfig);
	
}

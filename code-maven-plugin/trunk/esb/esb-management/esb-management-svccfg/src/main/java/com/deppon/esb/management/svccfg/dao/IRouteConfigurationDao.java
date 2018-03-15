package com.deppon.esb.management.svccfg.dao;

import com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute;

public interface IRouteConfigurationDao {

	/** 新增路由配置 */
	public int addRouteConfig(ESBServiceRoute esbServiceRoute);
}

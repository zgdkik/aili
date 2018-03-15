package com.deppon.esb.management.svccfg.dao.impl;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IRouteConfigurationDao;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute;

@Repository
public class RouteConfigurationDao extends IBatis3DaoImpl implements IRouteConfigurationDao{

	@Override
	public int addRouteConfig(ESBServiceRoute esbServiceRoute) {
		return getSqlSession().insert("com.deppon.esb.management.svccfg.domain.rest.ESBServiceRoute.insertRoute", esbServiceRoute);
	}
}

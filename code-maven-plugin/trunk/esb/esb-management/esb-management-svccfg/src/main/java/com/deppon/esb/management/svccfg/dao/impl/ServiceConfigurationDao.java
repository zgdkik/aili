package com.deppon.esb.management.svccfg.dao.impl;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IServiceConfigurationDao;
import com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfig;

@Repository
public class ServiceConfigurationDao extends IBatis3DaoImpl implements IServiceConfigurationDao{

	@Override
	public int addServiceConfig(ESBServiceConfig esbServiceConfig) {
		return getSqlSession().insert("com.deppon.esb.management.svccfg.domain.rest.ESBServiceConfig.insertSvcpoint", esbServiceConfig);
	}

}

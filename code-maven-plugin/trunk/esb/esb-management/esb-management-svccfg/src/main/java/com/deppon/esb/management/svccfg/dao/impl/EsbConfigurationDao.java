package com.deppon.esb.management.svccfg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.IEsbConfigurationDao;
import com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration;
import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;

/**
 * ESB服务配置信息处理接口实现.
 */
@Repository
public class EsbConfigurationDao extends IBatis3DaoImpl implements IEsbConfigurationDao {

	/** 
	 * 查询所有的服务配置信息，方便ESB运行时使用 需要连接路由表和服务信息配置表查询.
	 * @author HuangHua
	 * @date 2012-12-29 上午11:46:13
	 * @see com.deppon.esb.management.svccfg.dao.IEsbConfigurationDao#loadESBRuntimeConfiguration()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ESBRuntimeConfiguration> loadESBRuntimeConfiguration() {
		return getSqlSession().selectList(
				"com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration.loadESBRuntimeConfiguration");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsbRuntimeConfigAddrBean> selectBackendWsAddr() {
		return getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration.selectBackendWsAddr");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EsbSvcPoint> selectEsbSvcPoint() {
		return getSqlSession().selectList("com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration.selectEsbSvcPoint");
	}
	
}

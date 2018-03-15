package com.deppon.esb.management.svccfg.dao;

import java.util.List;

import com.deppon.esb.management.svccfg.domain.ESBRuntimeConfiguration;
import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;
import com.deppon.esb.management.svccfg.domain.view.EsbRuntimeConfigAddrBean;

/**
 * ESB服务配置信息DAO处理接口.
 */
public interface IEsbConfigurationDao {

	/**
	 * 查询所有的服务配置信息，方便ESB运行时使用 需要连接路由表和服务信息配置表查询.
	 * 
	 * @return List<ESBRuntimeConfiguration>
	 */
	public List<ESBRuntimeConfiguration> loadESBRuntimeConfiguration();

	/**
	 * 查询所有后端服务编码和地址
	 * 
	 * @return key--value:后端服务编码--后端服务地址
	 */
	public List<EsbRuntimeConfigAddrBean> selectBackendWsAddr();
	
	
	/**
	 * 
	 * 
	 */
	
	public List<EsbSvcPoint> selectEsbSvcPoint();
}

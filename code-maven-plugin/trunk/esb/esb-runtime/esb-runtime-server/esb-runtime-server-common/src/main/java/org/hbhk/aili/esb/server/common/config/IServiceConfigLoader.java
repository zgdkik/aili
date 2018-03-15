package org.hbhk.aili.esb.server.common.config;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.config.SvcPointInfo;

/**
 * 服务配置信息接口类.
 * 
 */
public interface IServiceConfigLoader {

	/**
	 * 获取所有的配置信息.
	 */
	Map<String, List<ESBRuntimeConfiguration>> getAll();

	/**
	 * 通过服务编码获取服务信息.
	 * 
	 * @param code
	 *            the code
	 * @return the SvcPointInfo
	 */
	SvcPointInfo getSvcPointInfo(String code);
	
	/**
	 * 获取所有的服务信息.
	 * 
	 */
	Map<String, SvcPointInfo> getAllSvcPoint();

	/**
	 * 通过服务编码获取配置信息.
	 * 
	 * @param code
	 *            the code
	 * @return the list
	 */
	List<ESBRuntimeConfiguration> get(String code);

	/**
	 * 获取jmsComponent ID.
	 * 
	 * @return the jms component
	 */
	//String getJmsComponent();

	void refresh();

}

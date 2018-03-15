package org.hbhk.aili.route.jms.server.common.config;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.config.SvcPointInfo;

/**
 * 服务配置信息接口类.
 * 
 */
public interface IServiceConfigLoader {

	/**
	 * 获取所有的配置信息.
	 * 
	 * @date 2012-12-25 下午4:26:59
	 */
	Map<String, List<ESBRuntimeConfiguration>> getAll();

	/**
	 * 通过服务编码获取服务信息.
	 * 
	 * @param code
	 *            the code
	 * @return the SvcPointInfo
	 * @date 2012-12-25 下午4:27:08
	 */
	SvcPointInfo getSvcPointInfo(String code);
	
	/**
	 * 获取所有的服务信息.
	 * 
	 * @return the all
	 * @date 2012-12-25 下午4:26:59
	 */
	Map<String, SvcPointInfo> getAllSvcPoint();

	/**
	 * 通过服务编码获取配置信息.
	 * 
	 * @param code
	 *            the code
	 * @return the list
	 * @date 2012-12-25 下午4:27:08
	 */
	List<ESBRuntimeConfiguration> get(String code);

	/**
	 * 获取jmsComponent ID.
	 * 
	 * @return the jms component
	 * @date 2012-12-25 下午4:27:22
	 */
	String getJmsComponent();

	void refresh();

}

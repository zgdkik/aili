package com.deppon.esb.management.svccfg.dao.extend;

import java.util.List;

import com.deppon.esb.management.svccfg.domain.extend.ESBServiceConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration;

/**
 * ESB扩展需要的服务配置信息DAO接口.
 * 
 * @author HuangHua
 * @date 2012-12-29 上午11:45:01
 */
public interface IExtendConfigurationDao {

	/**
	 * 查询系统的共同通道信息.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the ESB system configuration
	 */
	public ESBSystemConfiguration loadAllSystemChannel(String systemCode);

	/**
	 * 查询系统的配置信息列表.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the list< esb service configuration>
	 */
	public List<ESBServiceConfiguration> loadServiceConfiguration(String systemCode);

}

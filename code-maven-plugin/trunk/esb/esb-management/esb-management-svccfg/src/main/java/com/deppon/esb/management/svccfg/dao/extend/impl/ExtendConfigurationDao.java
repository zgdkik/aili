package com.deppon.esb.management.svccfg.dao.extend.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deppon.esb.management.common.dataaccess.ibatis.IBatis3DaoImpl;
import com.deppon.esb.management.svccfg.dao.extend.IExtendConfigurationDao;
import com.deppon.esb.management.svccfg.domain.extend.ESBServiceConfiguration;
import com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration;

/**
 * ESB扩展需要的服务配置信息DAO接口实现.
 */
@Repository
public class ExtendConfigurationDao extends IBatis3DaoImpl implements
		IExtendConfigurationDao {

	/**
	 * 查询系统的共同通道信息.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the ESB system configuration
	 * @author HuangHua
	 * @date 2012-12-29 上午11:47:21
	 * @see com.deppon.esb.management.svccfg.dao.extend.IExtendConfigurationDao#loadAllSystemChannel(java.lang.String)
	 */
	@Override
	public ESBSystemConfiguration loadAllSystemChannel(String systemCode) {
		return (ESBSystemConfiguration) getSqlSession()
				.selectOne(
						"com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration.loadAllSystemChannel",
						systemCode);
	}

	/**
	 * 查询系统的配置信息列表.
	 * 
	 * @param systemCode
	 *            the system code
	 * @return the list< esb service configuration>
	 * @author HuangHua
	 * @date 2012-12-29 上午11:47:21
	 * @see com.deppon.esb.management.svccfg.dao.extend.IExtendConfigurationDao#loadServiceConfiguration(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ESBServiceConfiguration> loadServiceConfiguration(
			String systemCode) {
		List<com.deppon.esb.management.svccfg.domain.extend.r.ESBServiceConfiguration> configurations = getSqlSession()
				.selectList(
						"com.deppon.esb.management.svccfg.domain.extend.ESBSystemConfiguration.loadServiceConfiguration",
						systemCode);
		List<ESBServiceConfiguration> cfgs = new ArrayList<ESBServiceConfiguration>();
		for (com.deppon.esb.management.svccfg.domain.extend.r.ESBServiceConfiguration cfg : configurations) {
			ESBServiceConfiguration configuration = new ESBServiceConfiguration();
			configuration.setServiceCode(cfg.getCode());
			if ("B".equals(cfg.getFrntOrbck())) {
				configuration.setClientOrServer("S");
			} else if ("F".equals(cfg.getFrntOrbck())) {
				configuration.setClientOrServer("C");
			}
			if (!"JMS".equals(cfg.getAgrmt())) {
				configuration.setAccessChannel(0);
			} else {
				configuration.setAccessChannel(1);
			}
			configuration.setExchangePattern(cfg.getExpattern());
			configuration.setMessageFormat(cfg.getMessageFormat());
			configuration.setServiceTimeout(cfg.getTimeout());
			configuration.setRequestURL(cfg.getRequestAddr());
			configuration.setResponseURL(cfg.getResponseAddr());
			configuration.setReqConvertorClass(cfg.getReqConvertorClass());
			configuration.setResConvertorClass(cfg.getResConvertorClass());
			configuration.setPersistentLevel(0);
			cfgs.add(configuration);
		}
		return cfgs;
	}

}

package com.deppon.esb.management.rbackqe.config.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.deppon.esb.management.rbackqe.config.IServiceConfigLoader;
import com.deppon.esb.management.svccfg.domain.EsbSvcPoint;
import com.deppon.esb.management.svccfg.service.IConfigurationService;

/**
 * 通过JMS加载配置信息.
 * 
 * @author HuangHua
 */
@Repository
public class ServiceConfigLoader implements IServiceConfigLoader {

	/** LOGGER. */
	public static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfigLoader.class);

	/** 配置信息缓存. */
	private static Map<String, List<EsbSvcPoint>> esbConfig;
	
	@Resource
	private IConfigurationService configurationService;

	/**
	 * 默认构造方法.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:51:23
	 */
	private ServiceConfigLoader() {
	}

	/**
	 * 调用这个方法会触发静态代码块，完成初始化.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:52:05
	 */
	public void start() {
		LOGGER.info("ServiceConfigLoader load service configuration complete!");
	}

	/**
	 * 获取服务编码相应的服务配置信息，如果对应的服务配置信息只有一个（P2P）,可以调用之后用get(0)来拿到配置信息.
	 * 
	 * @param serviceCode
	 *            服务编码
	 * @return the list
	 */
	public List<EsbSvcPoint> get(String serviceCode) {
		return esbConfig.get(serviceCode);
	}

	/**
	 * 返回当前配置.
	 * 
	 * @return the all
	 */
	public Map<String, List<EsbSvcPoint>> getAll() {
		return esbConfig;
	}

	/**
	 * 初始化实现.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:52:28
	 */
	private synchronized void init() {
		Map<String, List<EsbSvcPoint>> configMap = new HashMap<String, List<EsbSvcPoint>>();
		List<EsbSvcPoint> configurations = configurationService.selectEsbSvcPoint();
		for (EsbSvcPoint esbSvcPoint : configurations) {
			String code = esbSvcPoint.getCode();
			if (configMap.containsKey(code)) {
				configMap.get(code).add(esbSvcPoint);
			} else {
				List<EsbSvcPoint> list = new ArrayList<EsbSvcPoint>();
				list.add(esbSvcPoint);
				configMap.put(code, list);
			}
		}
		esbConfig = configMap;
		LOGGER.info("load Configuration successed! configuration:/n");
		LOGGER.info(configurations.toString());
	}

	/**
	 * 刷新配置.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:52:44
	 */
	public synchronized void refresh() {
		init();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("ServiceConfigLoader load service configuration starting ...");
		init();
	}

}

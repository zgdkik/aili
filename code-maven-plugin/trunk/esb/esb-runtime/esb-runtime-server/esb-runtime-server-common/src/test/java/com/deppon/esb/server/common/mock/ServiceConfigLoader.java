package com.deppon.esb.server.common.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.config.SvcPointInfo;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;


/**
 * 服务配置信息，just for test.
 * 
 * @author HuangHua
 * @date 2012-12-25 下午4:38:14
 */
public class ServiceConfigLoader implements IServiceConfigLoader{
	
	/** The esb config. */
	private Map<String, List<ESBRuntimeConfiguration>> esbConfig;
	

	/**
	 * 模拟getAll方法
	 * 
	 * @return the all
	 * @author HuangHua
	 * @date 2012-12-25 下午5:31:02
	 * @see org.hbhk.aili.esb.server.common.config.IServiceConfigLoader#getAll()
	 */
	@Override
	public Map<String, List<ESBRuntimeConfiguration>> getAll() {
		return esbConfig;
	}

	/**
	 * 模拟get方法
	 * 
	 * @param code
	 *            the code
	 * @return the list
	 * @author HuangHua
	 * @date 2012-12-25 下午5:31:02
	 * @see org.hbhk.aili.esb.server.common.config.IServiceConfigLoader#get(java.lang.String)
	 */
	@Override
	public List<ESBRuntimeConfiguration> get(String code) {
		return esbConfig.get(code);
	}

	/**
	 * 模拟JMSCOMPONENT
	 * 
	 * @return the jms component
	 * @author HuangHua
	 * @date 2012-12-25 下午5:31:02
	 * @see org.hbhk.aili.esb.server.common.config.IServiceConfigLoader#getJmsComponent()
	 */
//	@Override
//	public String getJmsComponent() {
//		return "ESBMQ";
//	}

	/**
	 * 服务配置信息，just for test.Instantiates a new service config loader.
	 */
	public ServiceConfigLoader() {
		esbConfig = new HashMap<String, List<ESBRuntimeConfiguration>>();
		List<ESBRuntimeConfiguration> configurations = new ArrayList<ESBRuntimeConfiguration>();
		ESBRuntimeConfiguration configuration = new ESBRuntimeConfiguration();
		configuration.setCreateTime(new Date());
		configuration.setEsbServiceCode("esbServiceCode");
		configuration.setId("12345679");
		configuration.setTargetServiceAddr("TARGETADDR");
		configuration.setTargetServiceCode("");
		configuration.setTargetServiceName("");
		configuration.setTargetSystem("");
		configurations.add(configuration);
		esbConfig.put("esbServiceCode", configurations);
	}

	/** 
	 * 刷新配置信息，模拟添加一条数据
	 * @author HuangHua
	 * @date 2012-12-26 上午9:23:57
	 * @see org.hbhk.aili.esb.server.common.config.IServiceConfigLoader#refresh()
	 */
	@Override
	public void refresh() {
		List<ESBRuntimeConfiguration> configurations = new ArrayList<ESBRuntimeConfiguration>();
		String code = UUID.randomUUID().toString();
		ESBRuntimeConfiguration configuration = new ESBRuntimeConfiguration();
		configuration.setCreateTime(new Date());
		configuration.setEsbServiceCode(code);
		configuration.setId("12345679");
		configuration.setTargetServiceAddr("");
		configuration.setTargetServiceCode("");
		configuration.setTargetServiceName("");
		configuration.setTargetSystem("");
		configurations.add(configuration);
		esbConfig.put(code, configurations);
	}

	@Override
	public SvcPointInfo getSvcPointInfo(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, SvcPointInfo> getAllSvcPoint() {
		// TODO Auto-generated method stub
		return null;
	}
}

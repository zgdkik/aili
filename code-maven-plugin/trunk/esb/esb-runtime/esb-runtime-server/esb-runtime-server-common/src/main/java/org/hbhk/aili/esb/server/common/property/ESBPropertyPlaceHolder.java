package org.hbhk.aili.esb.server.common.property;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * 
 * 在spring中使用自定义占位符，设置后端cxfEndpoint的后端地址。 使用方法：
 * 在需要使用站位符的地方使用“#{xxx.addr}”，占位符里写的内容是后端服务地址+".ADDR"即可；
 * eg：订单与运单关联接口后端endpoint配置 <cxf:cxfEndpoint id="FOSS_ESB2FOSS_BINDORDER"
 * address="#{FOSS_ESB2FOSS_BINDORDER.ADDR}"
 * wsdlURL="com/deppon/esb/client/foss/METE-INF/ws/foss4crm/FossService.wsdl">
 * </cxf:cxfEndpoint>
 * 
 * @author qiancheng
 * @date 2012-12-20 下午4:54:36
 */
public class ESBPropertyPlaceHolder extends PropertyPlaceholderConfigurer {

	/** 自定义 占位符前缀. */
	public static final String PREFIX = "#{";
	
	public static final String SUFFIX = ".ADDR";
	
	/** logger. */
	private static final Logger LOGGER = Logger
			.getLogger(ESBPropertyPlaceHolder.class);
	
	/** 服务配置信息. */
	private IServiceConfigLoader configLoader;

	/**
	 * Instantiates a new eSB property place holder.
	 * 
	 * @author Administrator
	 * @date 2012-12-20 下午5:12:49
	 */
	public ESBPropertyPlaceHolder() {
		super();
		setPlaceholderPrefix(PREFIX);
	}

	/**
	 * 重写loadProperties方法，添加后端地址properties.
	 * 
	 * @param props
	 *            the props
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @author qiancheng
	 * @date 2012-12-20 下午4:55:31
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#loadProperties(java.util.Properties)
	 */
	@Override
	protected void loadProperties(Properties props) throws IOException {
		Map<String, String> addressMap = getAddressConfig();
		props.putAll(addressMap);
	}

	/**
	 * 从ServiceConfigLoader 获取后端地址.
	 * 
	 * @return the address config
	 * @author qiancheng
	 * @date 2012-12-20 下午4:55:07
	 */
	private Map<String, String> getAddressConfig() {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, List<ESBRuntimeConfiguration>> configs = configLoader
				.getAll();
		Collection<List<ESBRuntimeConfiguration>> coll = configs.values();
		for (List<ESBRuntimeConfiguration> list : coll) {
			for (ESBRuntimeConfiguration config : list) {
				String code = config.getTargetServiceCode();
				String address = config.getTargetServiceAddr();
				if (address != null) {
					LOGGER.debug("add spring property key:[" + code
							+ SUFFIX + "],value:[" + address + "]");
					map.put(code + SUFFIX, address);
				}
			}
		}
		return map;
	}

	/**
	 * getConfigLoader.
	 * 
	 * @return the 服务配置信息
	 * @author HuangHua
	 * @date 2012-12-25 下午3:23:08
	 */
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	/**
	 * setConfigLoader.
	 * 
	 * @param configLoader
	 *            the new 服务配置信息
	 * @author HuangHua
	 * @date 2012-12-25 下午3:23:11
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
}

package org.hbhk.aili.esb.server.agent.support;

import java.util.List;

import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;


public class HttpAgrmtSupport implements HttpSupport{
	
	private static final Logger LOGGER = Logger.getLogger(HttpAgrmtSupport.class);
	
	private IServiceConfigLoader configLoader;

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
	
	@Override
	public boolean support(String serviceCode) {
		List<ESBRuntimeConfiguration> codes = configLoader.get(serviceCode);
		if (codes == null || codes.isEmpty()) {
			return false;
		}
		ESBRuntimeConfiguration configuration = codes.get(0);
		String agrmt = configuration.getAgrmt();
		if (agrmt == null || !EsbRouteConstant.HTTP_REST_FUL.equalsIgnoreCase(agrmt)) {
			LOGGER.error("SC000002:服务编码serviceCode=[" + serviceCode + "],不属于restFul服务");
			return false;
		}
		return true;
	}

}

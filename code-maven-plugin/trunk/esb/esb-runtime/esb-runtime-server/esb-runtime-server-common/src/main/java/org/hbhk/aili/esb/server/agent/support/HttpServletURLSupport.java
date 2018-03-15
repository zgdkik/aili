package org.hbhk.aili.esb.server.agent.support;

import java.util.List;

import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;

public class HttpServletURLSupport implements HttpSupport {

	private static final Logger LOGGER = Logger.getLogger(HttpServletURLSupport.class);

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
		String adder = configuration.getTargetServiceAddr();
		if (adder == null || !adder.startsWith(EsbRouteConstant.HTTP_TYPE)) {
			LOGGER.error("SC000001:服务编码serviceCode=[" + serviceCode + "],设置的地址为[" + adder + "]不正确");
			return false;
		}
		return true;
	}

}

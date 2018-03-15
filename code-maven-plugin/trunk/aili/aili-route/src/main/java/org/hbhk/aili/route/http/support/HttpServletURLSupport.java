package org.hbhk.aili.route.http.support;

import java.util.List;

import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.constant.EsbRouteConstant;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServletURLSupport implements HttpSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletURLSupport.class);

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

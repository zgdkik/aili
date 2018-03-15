package org.hbhk.aili.route.server.jms;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;

/**
 * 一对多处理.
 */
public class MultiServerProcessor implements Processor {

	/** 服务配置信息. */
	private IServiceConfigLoader configLoader;

	@Override
	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {
		Object body = exchange.getProperty(ESBServiceConstant.EXCHANGE_BODY);
		exchange.getIn().setBody(body);
		List<String> backServiceCodes = (List<String>) exchange.getProperty(ESBServiceConstant.BACK_SERVICE_CODE);
		List<String> targetSystem = (List<String>) exchange.getProperty(ESBServiceConstant.TARGET_SYSTEM);
		List<String> targetAdress = (List<String>) exchange.getProperty(ESBServiceConstant.TARGET_SERVICE_ADDR);

		int index = (Integer) exchange.getProperty("CamelLoopIndex");
//		exchange.getIn().getHeaders().put(ESBServiceConstant.BACK_SERVICE_CODE, backServiceCodes.get(index));
//		exchange.getIn().getHeaders().put(ESBServiceConstant.TARGET_SYSTEM, targetSystem.get(index));
		
		String backServiceAddr = backServiceCodes.get(index);
		String endpoint = "jms" + ":" + backServiceAddr + "?disableReplyTo=true";
		exchange.setProperty("DPESB_FINBANK_SYNC_ADDRESS", endpoint);
	}

	/**
	 * getConfigLoader.
	 * 
	 * @return the 服务配置信息
	 */
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	/**
	 * setConfigLoader.
	 * 
	 * @param configLoader
	 *            the new 服务配置信息
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
}

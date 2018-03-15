package org.hbhk.aili.esb.server.foss.sync;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;

/**
 * 一对多处理.
 */
public class MultiServerProcessor implements Processor {
	
	/** 服务配置信息. */
	private IServiceConfigLoader configLoader;

	/**
	 * 针对一对多情况的特殊处理.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) throws Exception {
		Object body = exchange.getProperty(ESBServiceConstant.EXCHANGE_BODY);
		exchange.getIn().setBody(body);
		List<String> backServiceCodes = (List<String>) exchange.getProperty(ESBServiceConstant.BACK_SERVICE_CODE);
		List<String> targetSystem = (List<String>) exchange.getProperty(ESBServiceConstant.TARGET_SYSTEM);
		List<String> targetAdress = (List<String>) exchange.getProperty(ESBServiceConstant.TARGET_SERVICE_ADDR);

		int index = (Integer) exchange.getProperty("CamelLoopIndex");
		exchange.getIn().getHeaders().put(ESBServiceConstant.BACK_SERVICE_CODE, backServiceCodes.get(index));
		exchange.getIn().getHeaders().put(ESBServiceConstant.TARGET_SYSTEM, targetSystem.get(index));
		
		String backServiceAddr = targetAdress.get(index);
		String esbServiceCode  = (String) exchange.getProperty(ESBServiceConstant.ESB_SERVICE_CODE);
		// example： ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
		String endpoint = configLoader.getSvcPointInfo(esbServiceCode).getJmsComponent() + ":" + backServiceAddr + "?disableReplyTo=true";
		exchange.setProperty("DPESB_FOSS_SYNC_ADDRESS", endpoint);
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

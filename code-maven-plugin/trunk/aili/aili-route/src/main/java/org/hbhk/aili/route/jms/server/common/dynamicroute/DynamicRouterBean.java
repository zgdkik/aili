package org.hbhk.aili.route.jms.server.common.dynamicroute;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;

/**
 * 根据ESB服务编码，获取相应的服务配置，然后返回的服务地址 仅供JMS使用.
 * 
 * @author HuangHua
 */
public class DynamicRouterBean {
	
	/** 服务配置信息. */
	private IServiceConfigLoader configLoader;

	/**
	 * 路由.
	 * 
	 * @param requestOrCallBack
	 *            the request or call back
	 * @param backServiceCode
	 *            the back service code
	 * @param esbServiceCode
	 *            the esb service code
	 * @param previous
	 *            the previous
	 * @return the string
	 * @author HuangHua
	 * @date 2012-12-25 下午3:20:22
	 */
	public String route(@Header(ESBServiceConstant.RT_REQUEST_OR_CALLBACK) String requestOrCallBack,
			@Header(ESBServiceConstant.BACK_SERVICE_CODE) String backServiceCode,
			@Header(ESBServiceConstant.ESB_SERVICE_CODE) String esbServiceCode,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		return whereToGo(requestOrCallBack, backServiceCode, esbServiceCode, previous);
	}

	/**
	 * Method which computes where to go next.
	 * 
	 * @param requestOrCallBack
	 *            前端或者后端，see
	 * @param backServiceCode
	 *            后端服务编码
	 * @param esbServiceCode
	 *            ESB服务编码
	 * @param previous
	 *            SLIP_ENDPOINT
	 * @return 路由URI
	 *         {@link org.hbhk.aili.route.jms.common.constant.ESBServiceConstant.RT_REQUEST_OR_CALLBACK}
	 */
	private String whereToGo(String requestOrCallBack, String backServiceCode, String esbServiceCode, String previous) {
		if (previous == null) {
			if(requestOrCallBack.equals(ESBServiceConstant.RT_REQUEST)){
				ESBRuntimeConfiguration configInfo = configLoader.get(esbServiceCode).get(0);
				String backServiceAddr = configInfo.getTargetServiceAddr();
				// example： ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
				return configLoader.getJmsComponent() + ":" + backServiceAddr + "?disableReplyTo=true";
			}else if(requestOrCallBack.equals(ESBServiceConstant.RT_CALLBACK)){
				ESBRuntimeConfiguration configInfo = configLoader.get(backServiceCode).get(0);
				String backServiceAddr = configInfo.getTargetServiceAddr();
				// example： ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
				return configLoader.getJmsComponent() + ":" + backServiceAddr + "?disableReplyTo=true";
			}
		}
		// no more, so return null to indicate end of dynamic router
		return null;

	}

	/**
	 * getConfigLoader.
	 * 
	 * @return the 服务配置信息
	 * @author HuangHua
	 * @date 2012-12-25 下午3:20:09
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
	 * @date 2012-12-25 下午3:20:14
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
}

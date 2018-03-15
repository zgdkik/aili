package org.hbhk.aili.esb.server.foss.sync;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Property;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;

/**
 * FOSS动态路由.
 */
public class FossDynamicRouterBean {
	
	/** 路由名字. */
	private static final String MULTI_SERVER = "direct:multi_server"; // one to many
	
	/** 服务配置信息. */
	private IServiceConfigLoader configLoader;

	/**
	 * 动态路由.
	 * 
	 * @param targetEndpoint
	 *            the target endpoint
	 * @param previous
	 *            the previous
	 * @return the string  
	 */
	public String route2(@Property("DPESB_FOSS_SYNC_ADDRESS") String targetEndpoint,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		if (previous == null) {
			return targetEndpoint;
		}
		// no more, so return null to indicate end of dynamic router
		return null;
	}


	/**
	 * 动态路由.
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
	 */
	public String route1(@Header(ESBServiceConstant.RT_REQUEST_OR_CALLBACK) String requestOrCallBack,
			@Header(ESBServiceConstant.BACK_SERVICE_CODE) Object backServiceCode,
			@Header(ESBServiceConstant.ESB_SERVICE_CODE) String esbServiceCode,
			@Header(Exchange.SLIP_ENDPOINT) String previous,
			@Header("DPESB_FOSS_SYNC_ADDRESS_SMS") String syn_address) {
		return whereToGo(requestOrCallBack, backServiceCode, esbServiceCode, previous, syn_address);
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
	 *         {@link org.hbhk.aili.esb.common.constant.ESBServiceConstant.RT_REQUEST_OR_CALLBACK}
	 */
	private String whereToGo(String requestOrCallBack, Object backServiceCode, String esbServiceCode, String previous, String syn_address) {
		if (previous == null) {
			if ((backServiceCode instanceof String)) {
				String bServiceCode = (String) backServiceCode;
				if (requestOrCallBack.equals(ESBServiceConstant.RT_REQUEST)) {
					ESBRuntimeConfiguration configInfo = configLoader.get(esbServiceCode).get(0);
					String backServiceAddr = configInfo.getTargetServiceAddr();
					// example：
					// ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
					String endpoint = configInfo.getJmsComponent() + ":" + backServiceAddr
							+ "?disableReplyTo=true";
					return endpoint;
				} else if (requestOrCallBack.equals(ESBServiceConstant.RT_CALLBACK)) {
					ESBRuntimeConfiguration configInfo = configLoader.get(bServiceCode).get(0);
					String backServiceAddr = configInfo.getTargetServiceAddr();
					// example：
					// ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
					String endpoint = configInfo.getJmsComponent() + ":" + backServiceAddr
							+ "?disableReplyTo=true";
					return endpoint;
				}
			} else if (backServiceCode instanceof List) {
				return MULTI_SERVER;
			}
			
			if(syn_address != null){
				return syn_address;
			}
		}
		// no more, so return null to indicate end of dynamic router
		return null;

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

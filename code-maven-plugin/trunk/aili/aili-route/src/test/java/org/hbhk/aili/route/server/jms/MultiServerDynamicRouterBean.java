package org.hbhk.aili.route.server.jms;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Property;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.common.constant.EsbRouteConstant;

/**
 * @author HuangHua
 *
 */
public class MultiServerDynamicRouterBean {
	
	/** 路由名字. */
	private static final String MULTI_SERVER = "direct:multi_server"; // one to many
	

	/**
	 * 动态路由.
	 * 
	 * @param targetEndpoint
	 *            the target endpoint
	 * @param previous
	 *            the previous
	 * @return the string  
	 */
	public String route2(@Property("DPESB_FINBANK_SYNC_ADDRESS") String targetEndpoint,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		if (previous == null) {
			return targetEndpoint;
		}
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
	private String whereToGo(String requestOrCallBack, Object backServiceCode, String esbServiceCode, String previous) {
		if (previous == null) {
			if ((backServiceCode instanceof String)) {
				String bServiceCode = (String) backServiceCode;
				if (requestOrCallBack.equals(ESBServiceConstant.RT_REQUEST)) {
					String backServiceAddr = "hbhk1";
					// example：
					// ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
					String endpoint = "jms" + ":" + backServiceAddr
							+ "?disableReplyTo=true";
					return endpoint;
				} else if (requestOrCallBack.equals(ESBServiceConstant.RT_CALLBACK)) {
					String backServiceAddr ="hbhk2";
					// example：
					// ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
					String endpoint = "jms" + ":" + backServiceAddr
							+ "?disableReplyTo=true";
					return endpoint;
				}
			} else if (backServiceCode instanceof List) {
				return MULTI_SERVER;
			}
		}
		// no more, so return null to indicate end of dynamic router
		return null;

	}

}

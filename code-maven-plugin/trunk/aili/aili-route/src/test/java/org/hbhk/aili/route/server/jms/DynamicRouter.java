package org.hbhk.aili.route.server.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;

public class DynamicRouter {

	public String route(@Header(ESBServiceConstant.RT_REQUEST_OR_CALLBACK) String requestOrCallBack,
			@Header(ESBServiceConstant.BACK_SERVICE_CODE) String backServiceCode,
			@Header(ESBServiceConstant.ESB_SERVICE_CODE) String esbServiceCode,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		return whereToGo(requestOrCallBack, backServiceCode, esbServiceCode, previous);
	}
	
	private String whereToGo(String requestOrCallBack, String backServiceCode, String esbServiceCode, String previous) {
		if (previous == null) {
			if(requestOrCallBack.equals(ESBServiceConstant.RT_REQUEST)){
				String backServiceAddr ="hbhk1";
				// example： ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
				return "jms" + ":" + backServiceAddr + "?disableReplyTo=true";
			}else if(requestOrCallBack.equals(ESBServiceConstant.RT_CALLBACK)){
				String backServiceAddr = "hbhk2";
				// example： ESBMQPUT:QU_BHO2CRM_ORDER_ADD?disableReplyTo=true
				return "jms" + ":" + backServiceAddr + "?disableReplyTo=true";
			}
		}
		return null;

	}
}

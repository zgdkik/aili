package org.hbhk.aili.route.jms.server.route.impl;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.Properties;
import org.hbhk.aili.route.jms.server.route.IDynamicRouter;
import org.hbhk.aili.route.jms.share.RouteConstant;

public class DefaultDynamicRouter implements IDynamicRouter {
	/**
	 * 
	* @Description: 一对一
	* @author hbhk 
	* @date 2015年9月11日 下午3:27:29 
	  @param previous
	  @return
	 */
	public String route1(@Header(Exchange.SLIP_ENDPOINT) String previous
			,@Header(RouteConstant.interfaceCode) String interfaceCode
			,@Headers Map<String,Object> headers
			,@Properties Map<String,Object> properties) {
		if (previous == null) {
			return "jms:queue:hbhk2";
		}
		return null;

	}
	/**
	 * 
	* @Description: 一对多
	* @author hbhk 
	* @date 2015年9月11日 下午3:27:41 
	  @param previous
	  @return
	 */
	public String[] route2(@Header(Exchange.SLIP_ENDPOINT) String previous
			,@Header(RouteConstant.interfaceCode) String interfaceCode
			,@Headers Map<String,Object> headers
			,@Properties Map<String,Object> properties) {
		if (previous == null) {
			return new String[] { "jms:queue:hbhk2", "jms:queue:hbhk3" };
		}
		return null;

	}
	@Override
	public String[] route(@Header(Exchange.SLIP_ENDPOINT) String previous
			,@Header(RouteConstant.interfaceCode) String interfaceCode
			,@Headers Map<String,Object> headers
			,@Properties Map<String,Object> properties) {
		if (previous == null) {
			return new String[] { "jms:queue:hbhk2", "jms:queue:hbhk3" };
		}
		return null;
	}
	
}

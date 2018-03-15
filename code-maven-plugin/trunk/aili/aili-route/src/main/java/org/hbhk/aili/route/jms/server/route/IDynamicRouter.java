package org.hbhk.aili.route.jms.server.route;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.camel.Properties;
import org.hbhk.aili.route.jms.share.RouteConstant;

public interface IDynamicRouter {
	
	
	String[] route(@Header(Exchange.SLIP_ENDPOINT) String previous
			,@Header(RouteConstant.interfaceCode) String interfaceCode
			,@Headers Map<String,Object> headers
			,@Properties Map<String,Object> properties);

}

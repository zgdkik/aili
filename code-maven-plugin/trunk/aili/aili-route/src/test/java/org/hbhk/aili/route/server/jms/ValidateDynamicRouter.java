package org.hbhk.aili.route.server.jms;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;

public class ValidateDynamicRouter {

	public String route(@Headers Map<String, Object> header,
			@Header(Exchange.SLIP_ENDPOINT) String previous) {
		
		return "direct:normal";
	}
}

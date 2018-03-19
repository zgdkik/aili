package org.hbhk.hms.gateway.http.processor.router;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.hbhk.hms.gateway.common.router.IHttpRouter;
import org.hbhk.hms.gateway.http.processor.IServiceConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHttpRouter implements IHttpRouter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DefaultHttpRouter.class);

	private static final String options = "bridgeEndpoint=true&throwExceptionOnFailure=false";

	private IServiceConfigLoader configLoader;

	private static final String HTTP_CLIENT_TIMEOUT = "httpClient.soTimeout";

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	@Override
	public String getDestination(
			@Header(Exchange.SLIP_ENDPOINT) String previous,
			@Headers Map<String, Object> headers) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("message routing, is routed to the [");
		}
		if (previous == null) {
			// //当URL中没有超时设置时，添加超时设置，默认值为120秒
			Long timeout = 120000L;
			String destination = headers.get("CamelHttpUrl").toString();
			if(supportEndWith(destination)){
				
			}
			// + headers.get(Exchange.HTTP_QUERY);*/
			headers.put(Exchange.HTTP_PATH, "");
			headers.put("bridgeEndpoint", "true");
			headers.put("throwExceptionOnFailure", false);
			// headers.remove(EsbRouteConstant.MESSAGE_BOLB);
			headers.put(Exchange.HTTP_URI, destination);
			// 当URL中没有超时设置时，添加超时设置，默认值为120秒
			if (destination.contains("?")
					&& destination.indexOf(HTTP_CLIENT_TIMEOUT) < 0) {

				destination = destination + "&" + HTTP_CLIENT_TIMEOUT + "="
						+ timeout;
			} else if (!destination.contains("?")
					&& destination.indexOf(HTTP_CLIENT_TIMEOUT) < 0) {
				destination = destination + "?" + HTTP_CLIENT_TIMEOUT + "="
						+ timeout;
			}
			return destination;
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" null ],case pervious is null");
		}
		return null;
	}

	protected boolean supportEndWith(String adder) {
		return adder.substring(adder.length() - 1).matches("^[A-Za-z0-9]+$");
	}
}

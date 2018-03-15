package org.hbhk.aili.esb.server.agent.processor.router;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;
import org.hbhk.aili.esb.common.constant.ExpressConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;

public class DefaultEsbRouter implements Router {

	private static final Logger LOGGER = Logger.getLogger(DefaultEsbRouter.class);

	private static final String options = "bridgeEndpoint=true&throwExceptionOnFailure=false";

	private IServiceConfigLoader configLoader;
	
	private static final String HTTP_CLIENT_TIMEOUT="httpClient.soTimeout";
	
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

	@Override
	public String getDestination(@Header(Exchange.SLIP_ENDPOINT) String previous, @Headers Map<String, Object> headers) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Message routing, is routed to the [");
		}
		if (previous == null) {
			ESBRuntimeConfiguration configuration = configLoader.get(
					(String) headers.get(ExpressConstant.EXPRESS_ESB_SERVICE_CODE)).get(0);
			String destination = configuration.getTargetServiceAddr();
			if (!supportEndWith(destination)) {
				destination = destination.substring(0, destination.length() - 1);
			}
			String url = headers.get("CamelHttpUrl").toString();
			//获取URL中居于服务编码之后的内容
			int index = url.indexOf(configuration.getEsbServiceCode())+configuration.getEsbServiceCode().length();
			if(headers.get(Exchange.HTTP_QUERY)!=null){
				destination += url.substring(index)+ "?" + headers.get(Exchange.HTTP_QUERY);
			}else{
				destination += url.substring(index);
			}
			
			/*destination += headers.get(EsbRouteConstant.MESSAGE_BOLB) + "?" + options + "&"
					+ headers.get(Exchange.HTTP_QUERY);*/
			headers.put(Exchange.HTTP_PATH, "");
			headers.put("bridgeEndpoint", "true");
			headers.put("throwExceptionOnFailure", false);
			headers.remove(EsbRouteConstant.MESSAGE_BOLB);
			headers.put(Exchange.HTTP_URI, destination);
			//当URL中没有超时设置时，添加超时设置，默认值为120秒
			Long timeout = configuration.getTimeout();
			if(timeout == null){
				timeout = 120000L;				
			}
			if(destination.contains("?")&& destination.indexOf(HTTP_CLIENT_TIMEOUT)<0){
				
				destination= destination+"&"+HTTP_CLIENT_TIMEOUT+"="+timeout;				
			}
			else if(!destination.contains("?")&& destination.indexOf(HTTP_CLIENT_TIMEOUT)<0){
				destination= destination+"?"+HTTP_CLIENT_TIMEOUT+"="+timeout;	
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

package org.hbhk.aili.route.http.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicRouter {
    private IServiceConfigLoader configLoader;
	//private final static String options="?bridgeEndpoint=true&throwExceptionOnFailure=false";
    private final static String options="?bridgeEndpoint=true&throwExceptionOnFailure=false";
	private static Logger LOG = LoggerFactory.getLogger(DynamicRouter.class);
	public String getDestination(@Header(Exchange.SLIP_ENDPOINT) String previous,@Header(Exchange.HTTP_URL) String url,@Headers Map<String,Object> headers){
		LOG.debug("路由设置目的地址");
		if(previous==null){
			//TODO 根据url路由目标地址
			LOG.info("请求url:"+url);
			String destination = "https://www.baidu.com";
			destination =destination+options;
			//将CamelHttpPath设置为空，否则会将客户端调用的短地址加在destionation后，导致404错误
			headers.put(Exchange.HTTP_PATH, "");
			return destination;
		}
		return null;
	}
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
}

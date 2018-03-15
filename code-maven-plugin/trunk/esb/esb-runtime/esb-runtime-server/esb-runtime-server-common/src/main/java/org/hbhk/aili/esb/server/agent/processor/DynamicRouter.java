package org.hbhk.aili.esb.server.agent.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ExpressConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;

public class DynamicRouter {
    private IServiceConfigLoader configLoader;
	//private final static String options="?bridgeEndpoint=true&throwExceptionOnFailure=false";
    private final static String options="?bridgeEndpoint=true&throwExceptionOnFailure=false";
	private static Logger LOG = Logger.getLogger(DynamicRouter.class);
	public String getDestination(@Header(Exchange.SLIP_ENDPOINT) String previous,@Header(ExpressConstant.EXPRESS_ESB_SERVICE_CODE) String esbServiceCode,@Headers Map<String,Object> headers){
		LOG.debug("路由设置目的地址");
		if(previous==null){
			//将CamelHttpPath设置为空，否则会将客户端调用的短地址加在destionation后，导致404错误
			//eg:http://192.168.10.131:8083/tfr-agent-itf/ws/ldpService/queryLdpHandOverBillList/queryLdpHandOverBillList
			headers.put(Exchange.HTTP_PATH, "");
			ESBRuntimeConfiguration config = configLoader.get(esbServiceCode).get(0);
			String	destination = config.getTargetServiceAddr();
			destination =destination+options;
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

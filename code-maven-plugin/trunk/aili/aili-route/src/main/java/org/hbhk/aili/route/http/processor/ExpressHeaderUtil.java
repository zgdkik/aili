package org.hbhk.aili.route.http.processor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.camel.Exchange;
import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.constant.EsbRouteConstant;
import org.hbhk.aili.route.jms.common.constant.ExpressConstant;
import org.hbhk.aili.route.jms.server.common.AuthInfo;
import org.hbhk.aili.route.jms.server.common.ESBHeader;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;

public class ExpressHeaderUtil {

	private static IServiceConfigLoader serviceConfigLoader;

	public static ESBHeader getHeaderFromHeaders(Exchange exchange) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		ESBHeader header = new ESBHeader();
		AuthInfo info = new AuthInfo();
		String esbServiceCode = object2String(headers.get(ExpressConstant.EXPRESS_ESB_SERVICE_CODE));
		String version = object2String(headers.get(ExpressConstant.EXPRESS_VERSION));
		info.setUsername(object2String(headers.get(ExpressConstant.EXPRESS_USERNAME)));
		info.setPassword(object2String(headers.get(ExpressConstant.EXPRESS_PASSWORD)));
		header.setEsbServiceCode(esbServiceCode);
		header.setAuthentication(info);
		// header.setBusinessId(object2String(headers.get(ESBServiceConstant.BUSINESSID)));
		header.setBusinessId(UUID.randomUUID().toString());
		header.setVersion(version == null ? "0.1" : version);
		// 获取用户usercode并天机哎到businessDesc2
		header.setBusinessDesc2(object2String(headers.get(ExpressConstant.EXPRESS_USERNAME)));
		// 获取客户端ip地址并添加到businessDesc3
		header.setBusinessDesc3(object2String(headers.get("X-Forwarded-For")));
		header.setBusinessDesc1(object2String(headers.get(EsbRouteConstant.HTTP_REMOTE_URL)));
		// header.setTargetSystem(object2String(headers.get(ESBServiceConstant.TARGET_SYSTEM)));
		// 设置SourceSystem,只有落地配与FOSS交互,所以SourceSystem为LDP
		List<ESBRuntimeConfiguration> configurations = serviceConfigLoader.get(esbServiceCode);
		header.setSourceSystem(configurations == null ? "" : configurations.get(0).getSourceSystem());
		header.setExchangePattern(1);
		header.setRequestId(UUID.randomUUID().toString());
		return header;
	}

	public static String object2String(Object obj) {

		if (obj != null && obj instanceof String) {
			return (String) obj;
		}
		return null;
	}

	public static ESBHeader getResponseHeader(Exchange exchange, ESBHeader responseHeader) {
		Map<String, Object> headers = exchange.getIn().getHeaders();
		String esbServiceCode = responseHeader.getEsbServiceCode();
		ESBRuntimeConfiguration configuration = serviceConfigLoader.get(esbServiceCode).get(0);
		responseHeader.setTargetSystem(configuration.getTargetSystem());
		responseHeader.setResponseId(UUID.randomUUID().toString());
		responseHeader.setBackServiceCode(configuration.getTargetServiceCode());
		String resultCode = object2String(headers.get(ExpressConstant.EXPRESS_RESUTLCODE));
		if (resultCode == null) {
			responseHeader.setResultCode(0);
		} else {
			responseHeader.setResultCode(Integer.valueOf(resultCode));
		}
		return responseHeader;
	}

	public IServiceConfigLoader getServiceConfigLoader() {
		return serviceConfigLoader;
	}

	public void setServiceConfigLoader(IServiceConfigLoader serviceConfigLoader) {
		ExpressHeaderUtil.serviceConfigLoader = serviceConfigLoader;
	}
}

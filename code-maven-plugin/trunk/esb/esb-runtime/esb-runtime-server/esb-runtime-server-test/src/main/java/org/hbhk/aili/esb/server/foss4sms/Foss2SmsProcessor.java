/*
 * PROJECT NAME: esb-runtime-server-foss
 * PACKAGE NAME: com.deppon.esb.server.foss4sms
 * FILE    NAME: Foss2SmsProcessor.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.esb.server.foss4sms;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;

/**
 * 
 * @author HuangHua
 * @date 2013-6-1 下午4:03:21
 */
public class Foss2SmsProcessor implements Processor {

	/** The Constant ESB_FOSS2ESB_VOICEMESSAGE. */
	private static final String ESB_FOSS2ESB_VOICEMESSAGE = "ESB_FOSS2ESB_VOICEMESSAGE";

	/** The sms send service. */
	private SmsSendService smsSendService;
	
	/** The config loader. */
	private IServiceConfigLoader configLoader;

	@Override
	public void process(Exchange exchange) throws Exception {
		// 此处可以根据服务编码做一些处理
		Map<String, Object> headers = exchange.getIn().getHeaders();
		// Object body = exchange.getIn().getBody();

		String esbServiceCode = (String) headers
				.get(ESBServiceConstant.ESB_SERVICE_CODE);

		if (ESB_FOSS2ESB_VOICEMESSAGE.equals(esbServiceCode)) {// 短信语音
			String jsonMsg = exchange.getIn().getBody(String.class);
			SmsSendRequest rq = SmsRequestJSONConverter.json2Java(jsonMsg);
			String jsonSource = SmsRequestJSONConverter.pojo2Json(rq
					.getSmsInfos());
			String user = rq.getSmsAuthority().getUsername();
			String passwd = rq.getSmsAuthority().getPassword();
			ESBRuntimeConfiguration config = getConfigLoader().get(
					esbServiceCode).get(0);
			String url = config.getTargetServiceAddr();
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_RESP_SENT, new Date().getTime());
			
			String result = smsSendService.send(user, passwd, jsonSource, url);
			
			exchange.getIn().setBody(result);
			exchange.getIn().getHeaders()
					.put(ESBServiceConstant.RESULT_CODE, 1);// 成功
			exchange.getIn().getHeaders().put(ESBServiceConstant.RESPONSE_ID, UUID.randomUUID().toString());
			exchange.getProperties().put(
					ESBServiceConstant.RT_REQUEST_OR_CALLBACK,
					ESBServiceConstant.RT_CALLBACK);
			
			
			// 把header放在exchange的property里.
			// camel的header不是普通的HashMap，不能使用beanUtils
			Map<String, Object> headers1 = new HashMap<String, Object>();
			Map<String, Object> map = exchange.getIn().getHeaders();
			Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Object> entry = iter.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				headers1.put(key, value);
			}
			exchange.setProperty(ESBServiceConstant.RT_HEADERS, headers1);
		}
	}

	public SmsSendService getSmsSendService() {
		return smsSendService;
	}

	public void setSmsSendService(SmsSendService smsSendService) {
		this.smsSendService = smsSendService;
	}

	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}
}

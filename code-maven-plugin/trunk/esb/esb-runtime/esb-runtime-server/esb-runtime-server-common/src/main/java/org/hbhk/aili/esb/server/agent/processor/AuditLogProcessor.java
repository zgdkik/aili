package org.hbhk.aili.esb.server.agent.processor;

import java.io.ByteArrayOutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.StreamCache;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.common.constant.EsbRouteConstant;
import org.hbhk.aili.esb.server.agent.exception.AgentBodyException;
import org.hbhk.aili.esb.server.agent.exception.AgentExceptionConstant;

public class AuditLogProcessor implements Processor {
	private static Logger LOG = Logger.getLogger(AuditLogProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		if (EsbRouteConstant.HTTP_POST_METHOD.equalsIgnoreCase((String) exchange.getIn().getHeaders().get(Exchange.HTTP_METHOD))) {
			StreamCache input = exchange.getIn().getBody(StreamCache.class);
			if (input == null) {
				throw new AgentBodyException("请求消息为空", AgentExceptionConstant.TYPE_SYS,
						AgentExceptionConstant.REQUEST_BODY_NULL);
			}
			ByteArrayOutputStream ous = new ByteArrayOutputStream();
			input.writeTo(ous);
			byte[] array = ous.toByteArray();
			String str = new String(array, "UTF8");
			LOG.debug("审计日志：" + str);
			ous.close();
			input.reset();
			exchange.getProperties().put(ESBServiceConstant.REQUEST_SOAP_BODY, str);
		}
	}
}

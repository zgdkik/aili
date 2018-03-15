package org.hbhk.aili.route.jms.server.common.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.server.common.exception.ESBDestinationNotFoundException;

public class DestinationNotFoundProcessor  implements Processor{
	@Override
	public void process(Exchange exchange )throws Exception{
		throw new ESBDestinationNotFoundException("the esbServiceCode["+exchange.getProperty(ESBServiceConstant.ESB_SERVICE_CODE)+"] is not right,please set right esbServiceCode ");
	}
}

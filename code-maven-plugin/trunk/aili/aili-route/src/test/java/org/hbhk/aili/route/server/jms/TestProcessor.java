package org.hbhk.aili.route.server.jms;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;

public class TestProcessor implements Processor{

	@Override
	public void process(Exchange e) throws Exception {
		
		String fServiceCode = (String) e.getIn().getHeader(ESBServiceConstant.ESB_SERVICE_CODE);
		String bServiceCode = (String) e.getIn().getHeader(ESBServiceConstant.BACK_SERVICE_CODE);

		System.out.println("fServiceCode:"+fServiceCode +"  bServiceCode:" +bServiceCode);
	}

}

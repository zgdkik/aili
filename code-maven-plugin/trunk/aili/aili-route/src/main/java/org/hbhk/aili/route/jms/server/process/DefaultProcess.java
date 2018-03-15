package org.hbhk.aili.route.jms.server.process;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.hbhk.aili.route.jms.share.RouteConstant;

public class DefaultProcess implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
//		Message msg1= exchange.getIn();
//		exchange.setProperty(RouteConstant.interfaceCode, "hbhk");
//		//exchange.getIn().setHeader(RouteConstant.interfaceCode, "hbhk");
//		exchange.getIn().getHeaders().put(RouteConstant.interfaceCode, "hbhk");
//		System.out.println(msg1.getBody());
	}

}

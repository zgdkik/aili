package org.hbhk.aili.route.jms.server.common.distribution;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;

/**
 * 复制body处理类
 * 
 */
public class BodyCopyProcessor implements Processor {

	/**
	 * 复制body.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("hbhk1");
		list.add("hbhk2");
		exchange.setProperty("backServiceCode", list);
		exchange.setProperty(ESBServiceConstant.EXCHANGE_BODY, exchange.getIn()
				.getBody());
	}

}

package org.hbhk.aili.esb.server.foss.sync;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;

/**
 * 复制处理类.
 * 
 */
public class MultiServerCopyProcessor implements Processor{

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
		exchange.setProperty(ESBServiceConstant.EXCHANGE_BODY, exchange.getIn().getBody());
	}

}

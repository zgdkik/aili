package org.hbhk.aili.esb.server.common.distribution;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;

/**
 * 复制body处理类
 * 
 * @author HuangHua
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
		exchange.setProperty(ESBServiceConstant.EXCHANGE_BODY, exchange.getIn()
				.getBody());
	}

}

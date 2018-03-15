package org.hbhk.aili.route.jms.server.common.processor;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.server.common.log.status.IStatusService;
import org.hbhk.aili.route.jms.server.common.utils.ESBHeaderCutOffUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 状态处理类.
 * 
 * @author HuangHua
 * @date 2012-12-21 上午10:47:10
 */
public class StatusProcess implements Processor {

	/** The log. */
	private static final Logger LOGGER = LoggerFactory.getLogger(StatusProcess.class);

	/** The status service. */
	private IStatusService statusService;

	/**
	 * 处理方法.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:47:21
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, Object> headers = (Map<String, Object>) exchange.getProperty(ESBServiceConstant.RT_HEADERS);

		LOGGER.debug("status logging ...");
		statusService.saveStatus(headers);
	}

	/**
	 * Gets the status service.
	 * 
	 * @return the status service
	 */
	public IStatusService getStatusService() {
		return statusService;
	}

	/**
	 * Sets the status service.
	 * 
	 * @param statusService
	 *            the new status service
	 */
	public void setStatusService(IStatusService statusService) {
		this.statusService = statusService;
	}

}

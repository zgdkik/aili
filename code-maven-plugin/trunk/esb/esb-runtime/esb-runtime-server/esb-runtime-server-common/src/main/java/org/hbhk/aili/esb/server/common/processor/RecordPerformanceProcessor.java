package org.hbhk.aili.esb.server.common.processor;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.utils.ESBDomHelper;


/**
 * 
 * 性能日志记录类:在ESB发送消息到后端之前、ESB收到后端响应消息的时间戳，并发送到性能日志队列.
 * @author qiancheng
 * @date 2012-12-20 下午5:07:31
 */
public class RecordPerformanceProcessor implements Processor{
	
	/** logger. */
	private static final Logger LOGGER = Logger.getLogger(RecordPerformanceProcessor.class);
	
	/**
	 * 根据ESB2.0接入规范，状态规范；同步调用分别用值ST_202,ST_208,ST_402,ST_408,应对ESB收到客户端请求消息时，ESB转发消息之前，ESB收到后端响应消息,返回响应消息的状态；
	 */
	private String status;

	/**
	 * 从Exchange中获取esbHeader信息，以及记录时间戳，并发送到性能日志队列。.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author qiancheng
	 * @date 2012-12-20 下午5:09:09
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		LOGGER.debug("performance log");
		try {
			if (ESBServiceConstant.STATUS_AT_ESB_RESP_RECEIVED
					.equals(status)) {
				ESBHeader targetHeader = (ESBHeader) exchange.getIn().getHeader(
						ESBServiceConstant.ESB_HEADER);
				ESBHeader esbHeader = ESBDomHelper.getESBHeaderFromExchange(exchange);
				if (esbHeader != null) {
					exchange.getIn().setHeader(ESBServiceConstant.RESPONSE_ID,
							esbHeader.getResponseId());
					exchange.getIn().setHeader(ESBServiceConstant.RESULT_CODE,
							esbHeader.getResultCode());
					exchange.getIn().setHeader(ESBServiceConstant.ESB_HEADER,
							esbHeader);
					targetHeader.setResponseId(esbHeader.getResponseId());
					targetHeader.setResultCode(esbHeader.getResultCode());
				}
			}
			exchange.getProperties().put(status, (new Date()).getTime()+"");
		} catch (Exception e) {
			LOGGER.error("send PerformanceInfo error:" + e.getMessage(), e);
		}
	}

	/**
	 * getStatus.
	 * 
	 * @return the 根据ESB2
	 * @author qiancheng
	 * @date 2012-12-20 下午5:09:45
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * setStatus.
	 * 
	 * @param status
	 *            the new 根据ESB2
	 * @author qiancheng
	 * @date 2012-12-20 下午5:09:45
	 */
	public void setStatus(String status) {
		this.status = status;
	}	
}

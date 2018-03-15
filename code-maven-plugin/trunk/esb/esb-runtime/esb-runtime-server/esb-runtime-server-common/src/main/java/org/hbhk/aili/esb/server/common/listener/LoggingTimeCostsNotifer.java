package org.hbhk.aili.esb.server.common.listener;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Date;
import java.util.EventObject;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeCreatedEvent;
import org.apache.camel.management.event.ExchangeFailedEvent;
import org.apache.camel.support.EventNotifierSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.esb.server.common.entity.CommonExceptionInfo;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.jmsservice.ISendMessageService;
import org.hbhk.aili.esb.server.common.log.exception.IExceptionService;

/**
 * 我先删除所有的，暂时保留添加状态到camel的properties和记录性能日志的功能.
 * @author HuangHua
 * @date 2012-12-21 上午10:56:25
 */
public class LoggingTimeCostsNotifer extends EventNotifierSupport {
	
	/** The log. */
	private Log log = LogFactory.getLog(this.getClass());

	/** The exception service. */
	private IExceptionService exceptionService;//异常处理服务

	/** The performance service. */
	private ISendMessageService performanceService;//性能处理服务
	
	/**
	 * 服务信息加载器
	 */
	private IServiceConfigLoader serviceConfigLoader;

	/**
	 * 是否启用.
	 * 
	 * @param event
	 *            the event
	 * @return true, if is enabled
	 * @author HuangHua
	 * @date 2012-12-21 上午10:50:00
	 * @see org.apache.camel.spi.EventNotifier#isEnabled(java.util.EventObject)
	 */
	public boolean isEnabled(EventObject event) {
		return true;
	}

	/**
	 * 事件处理方法.
	 * 
	 * @param event
	 *            the event
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:50:17
	 * @see org.apache.camel.spi.EventNotifier#notify(java.util.EventObject)
	 */
	@Override
	public void notify(EventObject event) throws Exception {
		if (event instanceof ExchangeFailedEvent) {
			ExchangeFailedEvent exchangeFailedEvent = (ExchangeFailedEvent) event;
			Exchange exchange = exchangeFailedEvent.getExchange();
			//发送ws状态日志
		
			if(exchange.getFromEndpoint() instanceof CxfEndpoint &&needStatus(exchange)){
				processWsStatus(exchange);
			}
			//如果异常已经在failureProccessor中保存过，在这里不进行处理
			if(exchange.getProperties().containsKey(ESBServiceConstant.EXCEPTION_PROCCESSED)){
				return ;
			}
			StringWriter writer = new StringWriter();
			Exception cause = null;
			cause = (Exception)exchange.getProperty(Exchange.EVALUATE_EXPRESSION_RESULT);
			if (cause != null) {
				cause.printStackTrace(new PrintWriter(writer));
			} else if(cause == null){
				cause = exchange.getException();
				if (cause != null) {
					cause.printStackTrace(new PrintWriter(writer));
				}
			}
			CommonExceptionInfo excption = new CommonExceptionInfo();
			excption.setCreatedTime(new Date());
			excption.setDetailedInfo(writer.toString());
			excption.setExceptioncode("SYS_OTHER");
			excption.setExceptiontype(ESBServiceConstant.SYSTEM_EXCEPTION);
			excption.setMessage(cause.getMessage());
			if (!exchange.getIn().getHeaders().isEmpty()) {
				if (log.isDebugEnabled()) {
					log.debug("save exception ...");
				}
				exceptionService.saveException(exchange.getIn().getHeaders(),
						excption);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("save exception ......");
				}
				@SuppressWarnings("unchecked")
				Map<String, Object> header = (Map<String, Object>) exchange
						.getProperty(ESBServiceConstant.RT_HEADERS);
				exceptionService.saveException(header, excption);
			}
		}
		if (event instanceof ExchangeCompletedEvent) {
			//发送ws状态日志
			Exchange exchange = ((ExchangeCompletedEvent) event).getExchange();
			if(exchange.getFromEndpoint() instanceof CxfEndpoint && needStatus(exchange)){
				processWsStatus(exchange);
			}
		}
		if(event instanceof ExchangeCreatedEvent){
			//设置cxf节点值
			Exchange exchange = ((ExchangeCreatedEvent) event).getExchange();
			changeCxfInvokeAddress(exchange);
		}
	}
	
	private void processWsStatus(Exchange exchange){
		ESBHeader header = (ESBHeader )exchange.getProperties().get(ESBServiceConstant.ESB_HEADER);
		StringBuffer buffer = new StringBuffer();
		//
		if(exchange.getProperty(Exchange.EXCEPTION_CAUGHT)==null){
			Date startDate = (Date) exchange.getProperty(Exchange.CREATED_TIMESTAMP);
			//ST_202
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_REQ_RECEIVED);
			buffer.append("@");
			buffer.append(startDate.getTime());
			buffer.append(":");
			//ST_208
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT);
			buffer.append("@");
			buffer.append(exchange.getProperty(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT));
			buffer.append(":");
			//ST_402
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_RESP_RECEIVED);
			buffer.append("@");
			buffer.append(exchange.getProperty(ESBServiceConstant.STATUS_AT_ESB_RESP_RECEIVED));
			buffer.append(":");
			//ST_408
			Date endTime = new Date();
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_RESP_SENT);
			buffer.append("@");
			buffer.append(endTime.getTime());
			buffer.append(":");
			//ST_999
			buffer.append(ESBServiceConstant.STATUS_FINISHED);
			buffer.append("@");
			buffer.append(endTime.getTime());
		}else{
			Date startDate = (Date) exchange.getProperty(Exchange.CREATED_TIMESTAMP);
			//ST_202
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_REQ_RECEIVED);
			buffer.append("@");
			buffer.append(startDate.getTime());
			buffer.append(":");
			//ST_208
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT);
			buffer.append("@");
			buffer.append(exchange.getProperty(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT));
			buffer.append(":");
			Date endTime = new Date();
			//ST_402
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_RESP_RECEIVED);
			buffer.append("@");
			buffer.append(endTime.getTime());
			buffer.append(":");
			//ST_408
			buffer.append(ESBServiceConstant.STATUS_AT_ESB_RESP_SENT);
			buffer.append("@");
			buffer.append(endTime.getTime());
			buffer.append(":");
			//ST_999
			buffer.append(ESBServiceConstant.STATUS_FINISHED);
			buffer.append("@");
			buffer.append(endTime.getTime());
		}
		
		performanceService.send(header,buffer.toString());
	}
	
	private boolean needStatus(Exchange exchange ){
		String code = (String)exchange.getProperty(ESBServiceConstant.ESB_SERVICE_CODE);
		ESBRuntimeConfiguration config = (ESBRuntimeConfiguration)serviceConfigLoader.get(code).get(0);
		if(config.isNeedStatus()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 修改CXF Address
	 * @param exchange
	 */
	private void changeCxfInvokeAddress(Exchange exchange){
		// 遍历当前路由所有的节点，把所有的cxf节点的地址都换成最新的地址
		Collection<Endpoint> endpoints = exchange.getContext()
				.getEndpoints();
		for (Endpoint endpoint : endpoints) {
			if (endpoint instanceof CxfEndpoint) {
				Map<String, Object> properties = ((CxfEndpoint) endpoint).getProperties();
				if(!properties.containsKey(ESBServiceConstant.CXF_SERVICE_CODE)){
					continue;
				}
				String targetSvcCode = (String) properties.get(ESBServiceConstant.CXF_SERVICE_CODE);
//				log.info(targetSvcCode);
				String addr = getServiceConfigLoader().getSvcPointInfo(targetSvcCode).getSvcAddr();
				((CxfEndpoint) endpoint).setAddress(addr.trim());
			}
		}
	}

	/**
	 * 启动方法，过滤掉不需要触发的事件
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-25 下午5:31:02
	 * @see org.apache.camel.support.ServiceSupport#doStart()
	 */
	protected void doStart() throws Exception {
		setIgnoreExchangeCreatedEvent(false);
		setIgnoreExchangeCompletedEvent(false);
		setIgnoreExchangeFailedEvents(false);
		setIgnoreCamelContextEvents(true);
		setIgnoreServiceEvents(true);
		setIgnoreRouteEvents(true);
		setIgnoreExchangeRedeliveryEvents(true);
		setIgnoreExchangeSendingEvents(true);
		setIgnoreExchangeSentEvents(true);
	}

	/**
	 * doStop.
	 * 
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:56:02
	 * @see org.apache.camel.support.ServiceSupport#doStop()
	 */
	protected void doStop() throws Exception {
	}

	/**
	 * getExceptionService.
	 * 
	 * @return the exception service
	 * @author HuangHua
	 * @date 2012-12-21 上午10:56:22
	 */
	public IExceptionService getExceptionService() {
		return exceptionService;
	}

	/**
	 * setExceptionService.
	 * 
	 * @param exceptionService
	 *            the new exception service
	 * @author HuangHua
	 * @date 2012-12-21 上午10:56:28
	 */
	public void setExceptionService(IExceptionService exceptionService) {
		this.exceptionService = exceptionService;
	}



	public ISendMessageService getPerformanceService() {
		return performanceService;
	}

	public void setPerformanceService(ISendMessageService performanceService) {
		this.performanceService = performanceService;
	}

	public IServiceConfigLoader getServiceConfigLoader() {
		return serviceConfigLoader;
	}

	public void setServiceConfigLoader(IServiceConfigLoader serviceConfigLoader) {
		this.serviceConfigLoader = serviceConfigLoader;
	}

}

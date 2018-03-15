package org.hbhk.aili.route.jms.server.common.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.common.exception.ESBRunTimeException;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;
import org.hbhk.aili.route.jms.server.common.log.audit.IAuditLogService;


/**
 * 规范处理类。继承自camel的Processor。 记录审计日志、添加状态信息：接收、转换、发送 仅适用于from是JMS的.
 * 
 * @author HuangHua
 * @see org.apache.camel.Processor
 */
public class SpecificationsProcess implements Processor {

	/** The Constant LOGGER. */
	private static final Log LOGGER = LogFactory.getLog(SpecificationsProcess.class);

	/** The audit log service. */
	private IAuditLogService auditLogService;
	
	/** The config loader. */
	private IServiceConfigLoader configLoader;

	/**
	 * 处理方法.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 * @author HuangHua
	 * @date 2012-12-21 上午10:46:56
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		beforeProcess(exchange);
		long transformingTime = new Date().getTime();

		String serviceCode;
		String fServiceCode = (String) exchange.getIn().getHeader(ESBServiceConstant.ESB_SERVICE_CODE);
		String bServiceCode = (String) exchange.getIn().getHeader(ESBServiceConstant.BACK_SERVICE_CODE);

		if (bServiceCode == null || "".equals(bServiceCode)) {
			serviceCode = fServiceCode;
			List<ESBRuntimeConfiguration> cfgs = configLoader.get(serviceCode);
			if (cfgs.size() == 1) {// P2P
				String backServiceCode = cfgs.get(0).getTargetServiceCode();
				String targetSystem = cfgs.get(0).getTargetSystem();
				exchange.getIn().getHeaders().put(ESBServiceConstant.BACK_SERVICE_CODE, backServiceCode);// 设置服务编码
				exchange.getIn().getHeaders().put(ESBServiceConstant.TARGET_SYSTEM, targetSystem);
			} else {// multicast
				List<String> backServiceCode = new ArrayList<String>();
				List<String> targetSystem = new ArrayList<String>();
				List<String> targetAddr = new ArrayList<String>();
				for (ESBRuntimeConfiguration cfg : cfgs) {
					backServiceCode.add(cfg.getTargetServiceCode());
					targetSystem.add(cfg.getTargetSystem());
					targetAddr.add(cfg.getTargetServiceAddr());
				}
				//把多个目标系统的服务编码、系统名、地址放入property
				exchange.setProperty(ESBServiceConstant.BACK_SERVICE_CODE, backServiceCode);
				exchange.setProperty(ESBServiceConstant.TARGET_SYSTEM, targetSystem);
				exchange.setProperty(ESBServiceConstant.TARGET_SERVICE_ADDR, targetAddr);
			}
		}

		processWithStatus(exchange);

		// 添加转换状态
		if (exchange.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK).equals(ESBServiceConstant.RT_REQUEST)) {
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_REQ_TRANSFORMING, transformingTime);
		} else if (exchange.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK).equals(
				ESBServiceConstant.RT_CALLBACK)) {
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_RESP_TRANSFORMING, transformingTime);
		}

		afterProcess(exchange);
	}

	/**
	 * 接收到请求消息之后，处理请求消息之前 的校验和状态信息处理.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws ESBRunTimeException
	 *             the eSB run time exception
	 * @throws JMSException 
	 */
	public void beforeProcess(Exchange exchange) throws ESBRunTimeException, JMSException {
		long recievedTime = new Date().getTime();
		// 记录审计日志
		LOGGER.debug("audit logging ...");
		auditLogService.saveAudit(exchange);

		// 前端是不能传输后端服务编码的，所以如果存在后端服务编码，则表示回调
		String backServiceCode = (String) exchange.getIn().getHeader(ESBServiceConstant.BACK_SERVICE_CODE);
		if (backServiceCode != null && !"".equals(backServiceCode)) {
			// 回调
			exchange.getProperties().put(ESBServiceConstant.RT_REQUEST_OR_CALLBACK, ESBServiceConstant.RT_CALLBACK);
			//如果是回调,则把以前的状态删除
			exchange.getIn().getHeaders().remove(ESBServiceConstant.STATUS_AT_CONSUMER_CALLED);
			exchange.getIn().getHeaders().remove(ESBServiceConstant.STATUS_AT_CONSUMER_CREATED);
			exchange.getIn().getHeaders().remove(ESBServiceConstant.STATUS_AT_CONSUMER_SENT);
			exchange.getIn().getHeaders().remove(ESBServiceConstant.STATUS_AT_ESB_REQ_RECEIVED);
			exchange.getIn().getHeaders().remove(ESBServiceConstant.STATUS_AT_ESB_REQ_TRANSFORMING);
			exchange.getIn().getHeaders().remove(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT);
		} else {
			// 请求
			exchange.getProperties().put(ESBServiceConstant.RT_REQUEST_OR_CALLBACK, ESBServiceConstant.RT_REQUEST);
		}

		// 添加接收状态
		if (exchange.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK).equals(ESBServiceConstant.RT_REQUEST)) {
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_REQ_RECEIVED, recievedTime);
		} else if (exchange.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK).equals(
				ESBServiceConstant.RT_CALLBACK)) {
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_RESP_RECEIVED, recievedTime);
		}
	}

	/**
	 * 请求消息处理完成后的 处理.
	 * 
	 * @param exchange
	 *            the exchange
	 */
	private void afterProcess(Exchange exchange) {
		long sendTime = new Date().getTime();
		// 添加发送状态
		if (exchange.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK).equals(ESBServiceConstant.RT_REQUEST)) {
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_REQ_SENT, sendTime);
		} else if (exchange.getProperty(ESBServiceConstant.RT_REQUEST_OR_CALLBACK).equals(
				ESBServiceConstant.RT_CALLBACK)) {
			exchange.getIn().getHeaders().put(ESBServiceConstant.STATUS_AT_ESB_RESP_SENT, sendTime);
		}
		// 把header放在exchange的property里.
		// camel的header不是普通的HashMap，不能使用beanUtils
		Map<String, Object> headers = new HashMap<String, Object>();
		Map<String, Object> map = exchange.getIn().getHeaders();
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			headers.put(key, value);
		}
		exchange.setProperty(ESBServiceConstant.RT_HEADERS, headers);
	}

	/**
	 * 处理请求消息.
	 * 
	 * @param exchange
	 *            the exchange
	 * @throws Exception
	 *             the exception
	 */
	public void processWithStatus(Exchange exchange) throws Exception {
	}

	/**
	 * Gets the audit log service.
	 * 
	 * @return the audit log service
	 */
	public IAuditLogService getAuditLogService() {
		return auditLogService;
	}

	/**
	 * Sets the audit log service.
	 * 
	 * @param auditLogService
	 *            the new audit log service
	 */
	public void setAuditLogService(IAuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	/**
	 * Gets the config loader.
	 * 
	 * @return the config loader
	 */
	public IServiceConfigLoader getConfigLoader() {
		return configLoader;
	}

	/**
	 * Sets the config loader.
	 * 
	 * @param configLoader
	 *            the new config loader
	 */
	public void setConfigLoader(IServiceConfigLoader configLoader) {
		this.configLoader = configLoader;
	}

}

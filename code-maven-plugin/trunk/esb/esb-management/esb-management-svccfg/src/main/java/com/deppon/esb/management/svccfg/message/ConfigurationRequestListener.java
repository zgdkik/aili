package com.deppon.esb.management.svccfg.message;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.esb.management.svccfg.service.IConfigurationService;

/**
 * 对获取服务配置信息的请求的监听。接收请求==》处理请求==》发送响应.
 * 
 * @author HuangHua
 * @date 2013-1-8 下午3:50:37
 */
public class ConfigurationRequestListener implements MessageListener {

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(ConfigurationRequestListener.class);

	/** 配置信息服务. */
	private IConfigurationService configurationService;

	/** 公共队列名. */
	private String commonQueueName;

	/** ESB队列名. */
	private String esbQueueName;

	/** 响应消息发送器. */
	private ConfigurationResponseSender responseSender;

	/**
	 * 消息接收处理.
	 * 
	 * @param message
	 *            the message
	 * @author HuangHua
	 * @date 2013-1-8 下午3:50:09
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		try {
			String systemCode = message.getStringProperty("SYSTEMCODE");
			Map<String, String> header = new HashMap<String, String>();
			header.put("SYSTEMCODE", systemCode);
			String content = configurationService
					.loadAllSystemConfiguration(systemCode);
			if ("ESB".equals(systemCode)) {
				responseSender.send(esbQueueName, header, content);
				log.info("config systemCode:[" + systemCode
						+ "[, send queueName:[" + esbQueueName + "],content:"
						+ content);
			}
			//判断ESBUSER
			else if("ESBUSER".equals(systemCode)){
				responseSender.send(esbQueueName, header, content);
				log.info("config systemCode:[" + systemCode
						+ "[, send queueName:[" + esbQueueName + "],content:"
						+ content);
			} 
			//判断ESB_USER_INTERFACE_RELATION
			else if("ESB_USER_INTERFACE_RELATION".equals(systemCode)){
				responseSender.send(esbQueueName, header, content);
				log.info("config systemCode:[" + systemCode
						+ "[, send queueName:[" + esbQueueName + "],content:"
						+ content);
			} 
			//判断ESB_USER_INTERFACE_COUNT
			else if("ESB_USER_INTERFACE_COUNT".equals(systemCode)){
				responseSender.send(esbQueueName, header, content);
				log.info("config systemCode:[" + systemCode
						+ "[, send queueName:[" + esbQueueName + "],content:"
						+ content);
			}
			//判断ESB_INTERFACE_COUNT
			else if("ESB_INTERFACE_COUNT".equals(systemCode)){
				responseSender.send(esbQueueName, header, content);
				log.info("config systemCode:[" + systemCode
						+ "[, send queueName:[" + esbQueueName + "],content:"
						+ content);
			} 
			else {
				responseSender.send(commonQueueName, header, content);
				log.info("config systemCode:[" + systemCode
						+ "], send queueName:[" + commonQueueName
						+ "],content:" + content);
			}
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取 配置信息服务.
	 * 
	 * @return the 配置信息服务
	 */
	public IConfigurationService getConfigurationService() {
		return configurationService;
	}

	/**
	 * 设置 配置信息服务.
	 * 
	 * @param configurationService
	 *            the new 配置信息服务
	 */
	public void setConfigurationService(
			IConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	/**
	 * 获取 公共队列名.
	 * 
	 * @return the 公共队列名
	 */
	public String getCommonQueueName() {
		return commonQueueName;
	}

	/**
	 * 设置 公共队列名.
	 * 
	 * @param commonQueueName
	 *            the new 公共队列名
	 */
	public void setCommonQueueName(String commonQueueName) {
		this.commonQueueName = commonQueueName;
	}

	/**
	 * 获取 eSB队列名.
	 * 
	 * @return the eSB队列名
	 */
	public String getEsbQueueName() {
		return esbQueueName;
	}

	/**
	 * 设置 eSB队列名.
	 * 
	 * @param esbQueueName
	 *            the new eSB队列名
	 */
	public void setEsbQueueName(String esbQueueName) {
		this.esbQueueName = esbQueueName;
	}

	/**
	 * 获取 响应消息发送器.
	 * 
	 * @return the 响应消息发送器
	 */
	public ConfigurationResponseSender getResponseSender() {
		return responseSender;
	}

	/**
	 * 设置 响应消息发送器.
	 * 
	 * @param responseSender
	 *            the new 响应消息发送器
	 */
	public void setResponseSender(ConfigurationResponseSender responseSender) {
		this.responseSender = responseSender;
	}

}

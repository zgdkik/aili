package org.hbhk.aili.route.jms.server.common.config.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hbhk.aili.route.jms.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.route.jms.common.config.SvcPointInfo;
import org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 通过JMS加载配置信息.
 * 
 */
public class ServiceConfigLoader implements IServiceConfigLoader{
	
	/** LOGGER. */
	public static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfigLoader.class);
	
	/** 属性文件位置. */
	private static final String PROP_LOCATION = "com/deppon/esb/server/common/META-INF/jms/jms.properties";
	
	/** 配置信息缓存. */
	private static Map<String, List<ESBRuntimeConfiguration>> esbConfig;
	
	/**
	 * 服务信息缓存。目前只有后端服务的服务编码和服务地址
	 */
	private static Map<String, SvcPointInfo> svcPointCache;
	
	/** jmsComponent ID. */
	private static String jmsComponent;

	/**
	 * 默认构造方法.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:51:23
	 */
	private ServiceConfigLoader() {
	}
	
	//静态代码块，实现初始化
	static {
		LOGGER.info("ServiceConfigLoader load service configuration starting ...");
		init();
	}

	/**
	 * 调用这个方法会触发静态代码块，完成初始化.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:52:05
	 */
	public void start() {
		LOGGER.info("ServiceConfigLoader load service configuration complete!");
	}

	/**
	 * 获取服务编码相应的服务配置信息，如果对应的服务配置信息只有一个（P2P）,可以调用之后用get(0)来拿到配置信息.
	 * 
	 * @param serviceCode
	 *            服务编码
	 * @return the list
	 */
	public List<ESBRuntimeConfiguration> get(String serviceCode) {
		return esbConfig.get(serviceCode);
	}

	/**
	 * 返回当前配置.
	 * 
	 * @return the all
	 */
	public Map<String, List<ESBRuntimeConfiguration>> getAll() {
		return esbConfig;
	}
	
	/**
	 * 获取服务信息.目前只有后端服务的服务编码和服务地址
	 * @param serviceCode
	 * @return
	 */
	public SvcPointInfo getSvcPointInfo(String serviceCode){
		return svcPointCache.get(serviceCode);
	}
	
	/**
	 * 获取所有的服务信息.目前只有后端服务的服务编码和服务地址
	 * @return
	 */
	public Map<String, SvcPointInfo> getAllSvcPoint(){
		return svcPointCache;
	}

	/**
	 * 初始化实现.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:52:28
	 */
	private static synchronized void init() {
		ConnectionFactory connectionFactory = null;
		Connection requestConnection = null;
		Connection responseConnection = null;
		Session requestSession = null;
		Session receiveSession = null;
		Destination requestQueue = null;
		Destination responseQueue = null;
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(PROP_LOCATION));
			jmsComponent = properties.getProperty("jms_component");
			String jndiName = properties.getProperty("connection_factory_jndi");
			String requestQueueJndi = properties
					.getProperty("request_queue_jndi");
			String responseQueueJndi = properties
					.getProperty("response_queue_jndi");
			InitialContext context = new InitialContext();
			connectionFactory = (ConnectionFactory) context.lookup(jndiName);
			requestQueue = (Destination) context.lookup(requestQueueJndi);
			responseQueue = (Destination) context.lookup(responseQueueJndi);
			String timeout = properties.getProperty("receive_timeout");
			requestConnection = connectionFactory.createConnection();
			responseConnection = connectionFactory.createConnection();
			requestSession = requestConnection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			receiveSession = responseConnection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			String filter = "SYSTEMCODE='ESB'";
			MessageConsumer consumer = receiveSession
					.createConsumer(responseQueue,filter);
			MessageProducer producer = requestSession
					.createProducer(requestQueue);
			// 发请求
			TextMessage requestMessage = requestSession.createTextMessage();
			requestMessage.setStringProperty("SYSTEMCODE", "ESB");
			requestMessage.setText("ESB request configuration!");
			producer.send(requestMessage);
			LOGGER.info("send request 4 getConfiguration,data:["
					+ ToStringBuilder.reflectionToString(requestMessage) + "]");
			// 接收响应
			responseConnection.start();
			Message message = consumer.receive(Long.parseLong(timeout) * 1000);
			if (message != null) {
				TextMessage response = (TextMessage) message;
				ObjectMapper mapper = new ObjectMapper();
				ESBRuntimeConfiguration[] cfgs = mapper.readValue(
						response.getText(), ESBRuntimeConfiguration[].class);
				Map<String, List<ESBRuntimeConfiguration>> configMap = new HashMap<String, List<ESBRuntimeConfiguration>>();
				Map<String, SvcPointInfo> svcPointMap = new HashMap<String, SvcPointInfo>();
				if (cfgs != null) {
					for (int i = 0; i < cfgs.length; i++) {
						String serviceCode = cfgs[i].getEsbServiceCode();
						if (configMap.containsKey(serviceCode)) {// 如果存在，则说明是一对多的传输方式
							configMap.get(serviceCode).add(cfgs[i]);
						} else {
							List<ESBRuntimeConfiguration> cfgList = new ArrayList<ESBRuntimeConfiguration>();
							cfgList.add(cfgs[i]);
							configMap.put(serviceCode, cfgList);
						}
						String targetSvcCode = cfgs[i].getTargetServiceCode();
						String targetSvcAddr = cfgs[i].getTargetServiceAddr();
						SvcPointInfo svcPointInfo = new SvcPointInfo();
						svcPointInfo.setSvcCode(targetSvcCode);
						svcPointInfo.setSvcAddr(targetSvcAddr);
						svcPointMap.put(targetSvcCode, svcPointInfo);
					}
				}
				esbConfig = configMap;
				svcPointCache = svcPointMap;
				LOGGER.info("load Configuration successed! configuration:");
				LOGGER.info(response.getText());
			} else {
				LOGGER.error("load Configuration failed!");
			}

		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NamingException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (receiveSession != null) {
					receiveSession.close();
				}
				if (requestSession != null) {
					requestSession.close();
				}
				if (requestConnection != null) {
					requestConnection.close();
				}
				if (responseConnection != null) {
					responseConnection.close();
				}
			} catch (JMSException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * 刷新配置.
	 * 
	 * @author HuangHua
	 * @date 2012-12-21 上午10:52:44
	 */
	public synchronized void refresh() {
		init();
	}

	/**
	 * getJmsComponent.
	 * 
	 * @return the jmsComponent ID
	 * @author HuangHua
	 * @date 2012-12-25 下午3:41:42
	 * @see org.hbhk.aili.route.jms.server.common.config.IServiceConfigLoader#getJmsComponent()
	 */
	public String getJmsComponent() {
		return jmsComponent;
	}
}

package org.hbhk.aili.esb.server.common.config.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hbhk.aili.esb.common.config.ESBRuntimeConfiguration;
import org.hbhk.aili.esb.common.config.SvcPointInfo;
import org.hbhk.aili.esb.server.common.config.IServiceConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 通过JMS加载配置信息.
 */
public class ServiceConfigLoader implements IServiceConfigLoader ,InitializingBean {

	/** LOGGER. */
	public static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceConfigLoader.class);

	/** 属性文件位置. */
	private static final String PROP_LOCATION = "org/hbhk/aili/esb/server/common/META-INF/jms/jms.properties";

	/** 配置信息缓存. */
	private static Map<String, List<ESBRuntimeConfiguration>> esbConfig;

	/**
	 * 服务信息缓存。目前只有后端服务的服务编码和服务地址
	 */
	private static Map<String, SvcPointInfo> svcPointCache;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/** jmsComponent ID. */
	// private static String jmsComponent;

	/**
	 * 默认构造方法.
	 */
	private ServiceConfigLoader() {
	}

	/**
	 * 调用这个方法会触发静态代码块，完成初始化.
	 * 
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
	 * 
	 * @param serviceCode
	 * @return
	 */
	public SvcPointInfo getSvcPointInfo(String serviceCode) {
		return svcPointCache.get(serviceCode);
	}

	/**
	 * 获取所有的服务信息.目前只有后端服务的服务编码和服务地址
	 * 
	 * @return
	 */
	public Map<String, SvcPointInfo> getAllSvcPoint() {
		return svcPointCache;
	}

	/**
	 * 初始化实现.
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
			// jmsComponent = properties.getProperty("jms_component");
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
			MessageConsumer consumer = receiveSession.createConsumer(
					responseQueue, filter);
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

	private synchronized void init1() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT A.FID,A.ESBSVCCODE,A.COMPONENT,A.TARGETSVCCODE,A.SYSID TARGETSYSTEM,");
		sql.append("T.SYSID SOURCESYSTEM, A.AGRMT,A.BACKADDR ,A.NEEDAUDIT, A.NEEDEXPN,");
		sql.append("A.NEEDSTATUS,  A.TIMEOUT");
		sql.append(" FROM ( ");
		sql.append(" SELECT TR.FID,TR.ESBSVCCODE,TR.TARGETSVCCODE,TS.SYSID ,TS.AGRMT,");
		sql.append(" TS.NEEDAUDIT, TS.NEEDEXPN,TS.NEEDSTATUS,TS.TIMEOUT,TS.COMPONENT,");
		sql.append(" CASE WHEN TS.FRNTORBCK='B' THEN TS.ESBREQUESTADDR ELSE TS.ESBRESPONSEADDR END AS BACKADDR");
		sql.append(" FROM T_ESB_ROUTE TR,T_ESB_SVCPOINT TS");
		sql.append(" WHERE TR.TARGETSVCCODE=TS.CODE");
		sql.append(" )   A  ");
		sql.append(" LEFT JOIN T_ESB_SVCPOINT T ON A.ESBSVCCODE=T.CODE");
		LOGGER.info("sql:"+sql);
		List<ESBRuntimeConfiguration> list = jdbcTemplate.query(sql.toString(),
				new RowMapper<ESBRuntimeConfiguration>() {
					@Override
					public ESBRuntimeConfiguration mapRow(ResultSet rs,
							int index) throws SQLException {
						ESBRuntimeConfiguration config = new ESBRuntimeConfiguration();
						String id = rs.getString("FID");
						config.setId(id);
						String esbServiceCode = rs.getString("ESBSVCCODE");
						config.setEsbServiceCode(esbServiceCode);
						String jmsComponent = rs.getString("COMPONENT");
						config.setJmsComponent(jmsComponent);
						String sourceSystem = rs.getString("SOURCESYSTEM");
						config.setSourceSystem(sourceSystem);
						String targetServiceCode = rs.getString("TARGETSVCCODE");
						config.setTargetServiceCode(targetServiceCode);
						String targetSystem = rs.getString("TARGETSYSTEM");
						config.setTargetSystem(targetSystem);
						String targetServiceAddr = rs.getString("BACKADDR");
						config.setTargetServiceAddr(targetServiceAddr);
						try {
							String targetPortName = rs.getString("WSPORTNAME");
							config.setTargetPortName(targetPortName);
						} catch (Exception e) {
							LOGGER.error(e.getMessage(),e);
						}
						try {
							String targetServiceName= rs.getString("WSSERVICENAME");
							config.setTargetServiceName(targetServiceName);
						} catch (Exception e) {
							LOGGER.error(e.getMessage(),e);
						}
						try {
						String targetNameSpace = rs.getString("WSTARGETNAMESPACE");
						config.setTargetNameSpace(targetNameSpace);
						} catch (Exception e) {
							LOGGER.error(e.getMessage(),e);
						}
						boolean needAudit = rs.getBoolean("NEEDAUDIT");
						config.setNeedAudit(needAudit);
						boolean needExpn = rs.getBoolean("NEEDEXPN");
						config.setNeedExpn(needExpn);
						boolean needStatus = rs.getBoolean("NEEDSTATUS");
						config.setNeedStatus(needStatus);
						Long timeout = rs.getLong("TIMEOUT");
						config.setTimeout(timeout);
						String agrmt = rs.getString("AGRMT");
						config.setAgrmt(agrmt);
						return config;
					}
				});

//		list = new ArrayList<ESBRuntimeConfiguration>();
//		ESBRuntimeConfiguration c1 = new ESBRuntimeConfiguration();
//		c1.setEsbServiceCode("hbhk1");
//		c1.setTargetServiceCode("thbhk1");
//		c1.setJmsComponent("ESBMQ");
//		c1.setTargetServiceAddr("QU_HBHK_REQUEST_COM_IN");
//		ESBRuntimeConfiguration c2 = new ESBRuntimeConfiguration();
//		c2.setEsbServiceCode("hbhk2");
//		c2.setTargetServiceCode("thbhk1");
//		c2.setAgrmt("restful");
//		c2.setTargetServiceAddr("http://www.baidu.com");
//		list.add(c1);
//		list.add(c2);
		Map<String, List<ESBRuntimeConfiguration>> configMap = new HashMap<String, List<ESBRuntimeConfiguration>>();
		Map<String, SvcPointInfo> svcPointMap = new HashMap<String, SvcPointInfo>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				ESBRuntimeConfiguration config = list.get(i);
				String serviceCode = config.getEsbServiceCode();
				if (configMap.containsKey(serviceCode)) {// 如果存在，则说明是一对多的传输方式
					configMap.get(serviceCode).add(config);
				} else {
					List<ESBRuntimeConfiguration> cfgList = new ArrayList<ESBRuntimeConfiguration>();
					cfgList.add(config);
					configMap.put(serviceCode, cfgList);
				}
				String targetSvcCode = config.getTargetServiceCode();
				String targetSvcAddr = config.getTargetServiceAddr();
				SvcPointInfo svcPointInfo = new SvcPointInfo();
				svcPointInfo.setSvcCode(targetSvcCode);
				svcPointInfo.setSvcAddr(targetSvcAddr);
				svcPointMap.put(targetSvcCode, svcPointInfo);
			}
		}
		esbConfig = configMap;
		svcPointCache = svcPointMap;
		LOGGER.info("load Configuration successed! configuration:");
	}

	/**
	 * 刷新配置.
	 */
	public synchronized void refresh() {
		init();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("ServiceConfigLoader load service configuration starting ...");
		init1();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * getJmsComponent.
	 */
	// public String getJmsComponent() {
	// return jmsComponent;
	// }
	
	
}

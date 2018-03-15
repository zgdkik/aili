package org.hbhk.aili.route.jms.server.security.access;

import java.io.IOException;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JmsUtil{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(JmsUtil.class);
	private static final String PROP_LOCATION = "com/deppon/esb/server/security/META-INF/jms.properties";
	/**
	 * 获取配置信息
	 * @author k
	 */
	public  static String getConfig(String configType) {
		//TODO 每次发送请求之前，讲MQ链接信息，以及 请求信息打印一次；
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
			//过滤出自己该拿的消息
			String filter = "SYSTEMCODE='"+configType+"'";
			MessageConsumer consumer = receiveSession
					.createConsumer(responseQueue,filter);
			MessageProducer producer = requestSession
					.createProducer(requestQueue);
			// 发请求
			TextMessage requestMessage = requestSession.createTextMessage();
			requestMessage.setStringProperty("SYSTEMCODE", configType);
			requestMessage.setText(" user_interface configuration!");
			producer.send(requestMessage);
			LOGGER.info("send request for get config,data:[" +requestMessage.getText()+ "]"+"property:SYSTEMCODE"+configType);
			// 接收响应
			responseConnection.start();
			Message message = consumer.receive(Long.parseLong(timeout) * 1000);
			if (message != null) {
				TextMessage response = (TextMessage) message;
				LOGGER.info(response.getText());
				return response.getText();
			} else {
				LOGGER.error("waited  for "+Long.parseLong(timeout) +"seconds,received nothting,requestCode:"+configType);
			}
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
		}  catch (IOException e) {
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
		return null;
	}
}

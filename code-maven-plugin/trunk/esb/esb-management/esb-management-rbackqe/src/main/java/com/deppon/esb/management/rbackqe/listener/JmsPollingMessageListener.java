package com.deppon.esb.management.rbackqe.listener;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.support.JmsUtils;

import com.deppon.esb.management.rbackqe.intface.process.IJmsMessageProcess;

public class JmsPollingMessageListener extends JmsMessageConsumerOrigin {

	private final static int DEFAULT_REFESH_TIME = 1000;

	private final static Logger LOGGER = Logger
			.getLogger(JmsPollingMessageListener.class);

	private IJmsMessageProcess jmsMessageProcess;

	private boolean start = true;

	private boolean shutdown = false;

	private int refreshTimeOut = DEFAULT_REFESH_TIME;

	public IJmsMessageProcess getJmsMessageProcess() {
		return jmsMessageProcess;
	}

	public void setJmsMessageProcess(IJmsMessageProcess jmsMessageProcess) {
		this.jmsMessageProcess = jmsMessageProcess;
	}

	public int getRefreshTimeOut() {
		return (refreshTimeOut <= DEFAULT_REFESH_TIME ? DEFAULT_REFESH_TIME
				: refreshTimeOut);
	}

	public void setRefreshTimeOut(int refreshTimeOut) {
		this.refreshTimeOut = refreshTimeOut;
	}

	protected void doInitialize() {
		Session session = null;
		Connection connection = null;
		try {
			refreshLink();
			afterPropertiesSet();
			connection = createConnection();
			session = createSession(connection);
			connection.start();
			MessageConsumer consumer = createMessageConsumer(session);
			int i = 1;
			while (start && !shutdown) {
				Message message = receiveMessage(consumer);
				// 是否发送成功
				boolean success = messageProcess(message, session);
				if (success) {
					if (i % 100 == 0) {
						JmsUtils.commitIfNecessary(session);
					}
				} else {
					JmsUtils.rollbackIfNecessary(session);
					Thread.sleep(refreshTimeOut/2);
				}
				if (message == null) {
					JmsUtils.commitIfNecessary(session);
					Thread.sleep(refreshTimeOut * 5);
				}
			}
		} catch (InterruptedException e) {
			/** 线程等待失败 */
			Thread.currentThread().interrupt();
		} catch (JMSException e) {
			OnException(session, e);
		} catch (RuntimeException e) {
			OnException(session, e);
		} catch (Exception e) {
			OnException(session, e);
		} finally {
			try {
				if (session != null) {
					JmsUtils.commitIfNecessary(session);
				}
			} catch (JMSException e) {
			}
		}
	}

	protected void OnException(Session session, Exception e) {
		LOGGER.info("refresh JMS ConnectionFactory" + getRefreshTimeOut()
				+ "ms------>" + e);
		if (session == null) {
			refreshLink();
			LOGGER.info("JMS ConnectionFactory refresh SUCCESS!!");
		} else {
			try {
				JmsUtils.rollbackIfNecessary(session);
			} catch (JMSException e1) {
				LOGGER.error("出现异常的时候回滚失败,重新进行刷session,");
				OnException(session, e1);
			}
		}
	}

	protected boolean messageProcess(Message message, Session session)
			throws JMSException {
		return jmsMessageProcess.process(session, message);
	}

	protected void accessResources(Connection connection, Session session)
			throws JMSException {

	}

	// 刷新
	protected void refreshLink() {
		boolean refreshLink = true;
		while (refreshLink) {
			if (getConnectionFactory() != null) {
				refreshLink = false;
				continue;
			}
			try {
				Thread.sleep(getRefreshTimeOut() * 5);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	protected void doshutdown() {
		setShutdown(true);
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

}

/*
 * PROJECT NAME: esb-runtime-common
 * PACKAGE NAME: com.deppon.esb.common.jms
 * FILE    NAME: EsbJmsSender.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.route.jms.server.common.utils.jms;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.hbhk.aili.route.jms.common.constant.ESBServiceConstant;
import org.hbhk.aili.route.jms.server.common.ESBHeader;
import org.hbhk.aili.route.jms.server.common.exception.LogSendException;
import org.hbhk.aili.route.jms.server.common.log.ISender;
import org.hbhk.aili.route.jms.server.common.utils.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jms.support.JmsUtils;

/**
 * 
 * @author HuangHua
 * @date 2013-4-8 下午7:14:56
 */
public class EsbJmsSender implements ISender, InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(EsbJmsSender.class);

	/** The queue. */
	private BlockingQueue<EsbLogMessage> queueToTrans;
	/** The thread name. */
	private String threadName;
	/** The queue. */
	private BlockingQueue<EsbLogMessage> queue;
	private ConnectionFactory connectionFactory;
	private String queueName;
	private Connection connection;
	private List<Session> threadSessionList = new ArrayList<Session>();
	private List<JmsSendThread> sendThreads = new ArrayList<JmsSendThread>();
	private List<TransformThread> transThreads = new ArrayList<TransformThread>();

	private int threadCount = 5;
	private int commitSize;
	protected long recoveryInterval = 5000l;
	private int queueCapacity = 200000;

	/**
	 * 先读取esbheader里的属性,然后读取map里的属性.注意:map里的属性可能覆盖esbheader里的属性
	 * 
	 * @author HuangHua
	 * @date 2013-4-15 下午5:21:35
	 * @exception LogSendException
	 *                使用的时候注意会跑出此异常
	 */
	public void send(ESBHeader esbHeader, Map<String, Object> header, String body) {
		Map<String, Object> headerToUse = HeaderUtil.esbHeader2Map(esbHeader);

		if (headerToUse == null) {
			headerToUse = new HashMap<String, Object>();
		}
		if (header != null) {
			headerToUse.putAll(header);
		}

		if (headerToUse.get(ESBServiceConstant.ESB_LOGMSG_CREATETIME) == null) {
			headerToUse.put(ESBServiceConstant.ESB_LOGMSG_CREATETIME, new Date());
		}

		EsbLogMessage message = new EsbLogMessage();
		message.setHeader(headerToUse);
		message.setBody(body);
		try {
			queueToTrans.add(message);
		} catch (IllegalStateException e) {

			LOGGER.error("jmsSender queueToTrans full", e);
			throw new LogSendException("jmsSender queueToTrans full", e);

		}
	}

	/**
	 * 初始化.
	 */
	public void init() {
		// if (connection == null) {
		// try {
		// session = createSession(true, Session.SESSION_TRANSACTED);
		// } catch (JMSException e) {
		// onJmsException(null, null);
		// }
		// }
		queue = new ArrayBlockingQueue<EsbLogMessage>(queueCapacity);
		queueToTrans = new ArrayBlockingQueue<EsbLogMessage>(queueCapacity);
		while (sendThreads.size() < threadCount) {
			JmsSendThread thread = createSendThread();
			TransformThread transformThread = new TransformThread(queueToTrans, queue, 0, getThreadName());
			transformThread.start();
			if (thread != null) {
				sendThreads.add(thread);
				transThreads.add(transformThread);
			}
		}
	}

	protected Connection createConnection() throws JMSException {
		return getConnectionFactory().createConnection();
	}

	protected Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
		if (connection == null) {
			connection = createConnection();
		}
		return connection.createSession(transacted, acknowledgeMode);
	}

	protected JmsSendThread createSendThread() {
		MessageProducer producer = null;
		Session session = null;
		try {
			session = createSession(true, Session.SESSION_TRANSACTED);
			Destination destination = session.createQueue(getQueueName());
			producer = session.createProducer(destination);
			threadSessionList.add(session);
			JmsSendThread sendThread = new JmsSendThread(queue, producer, session, getCommitSize(),
					getConnectionFactory(), getQueueName(), getThreadName());
			sendThread.start();
			return sendThread;
		} catch (JMSException e) {
			LOGGER.error(e.getMessage(), e);
			onJmsException(session, producer);
		}
		return null;
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-4-9 上午10:06:04
	 */
	private void onJmsException(Session session, MessageProducer producer) {
		JmsUtils.closeMessageProducer(producer);
		JmsUtils.closeSession(session);
		for (Session sn : threadSessionList) {
			JmsUtils.closeSession(sn);
		}
		JmsUtils.closeConnection(connection, true);
		connection = null;
		refreshConnectionUntilSuccessful();
		init();
	}

	protected void refreshConnectionUntilSuccessful() {
		while (true) {
			try {
				Connection con = createConnection();
				JmsUtils.closeConnection(con);
				LOGGER.info("Successfully refreshed JMS Connection");
				break;
			} catch (Exception ex) {
				StringBuilder msg = new StringBuilder();
				msg.append("Could not refresh JMS Connection for destination '");
				msg.append(getQueueName()).append("' - retrying in ");
				msg.append(this.recoveryInterval).append(" ms. Cause: ");
				msg.append(ex instanceof JMSException ? JmsUtils.buildExceptionMessage((JMSException) ex) : ex
						.getMessage());
				LOGGER.error(msg.toString(), ex);
			}
			try {
				Thread.sleep(this.recoveryInterval);
			} catch (InterruptedException e) {
				// Re-interrupt current thread, to allow other threads to react.
				Thread.currentThread().interrupt();
			}
		}
	}

	@Override
	public void afterPropertiesSet() {
		if (getConnectionFactory() == null) {
			throw new IllegalArgumentException("Property 'connectionFactory' is required");
		}
		if (getQueueName() == null) {
			throw new IllegalArgumentException("Property 'queueName' is required");
		}
		Thread initThread = new Thread() {
			public void run() {
				init();
			};
		};
		initThread.setDaemon(true);
		initThread.start();
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public int getCommitSize() {
		return commitSize;
	}

	public void setCommitSize(int commitSize) {
		this.commitSize = commitSize;
	}

	public long getRecoveryInterval() {
		return recoveryInterval;
	}

	public void setRecoveryInterval(long recoveryInterval) {
		this.recoveryInterval = recoveryInterval;
	}

	public int getQueueToTransSize() {
		return queueToTrans.size();
	}

	public int getQueueSize() {
		return queue.size();
	}

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

}
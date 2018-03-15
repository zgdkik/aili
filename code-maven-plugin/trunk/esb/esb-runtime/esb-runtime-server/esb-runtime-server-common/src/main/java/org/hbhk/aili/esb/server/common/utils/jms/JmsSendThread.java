/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils.jms
 * FILE    NAME: JmsSendThread.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.esb.server.common.utils.jms;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hbhk.aili.esb.server.common.utils.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.JmsUtils;

/**
 * 
 * @author HuangHua
 * @date 2013-4-16 下午3:16:53
 */
public class JmsSendThread extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(JmsSendThread.class);

	/** The commit size. */
	private int commitSize = 100;

	/** The queue. */
	private BlockingQueue<EsbLogMessage> queue;

	/** The connection factory. */
	private ConnectionFactory connectionFactory;

	/** The connection. */
	private Connection connection;

	/** The queue name. */
	private String queueName;

	/** The session. */
	private Session session;

	private MessageProducer messageProducer;

	private boolean start = false;

	private boolean shutdown = false;

	/** 刷新动作--true：无线刷新，false：不刷新. */
	private boolean refreshState = true;

	/**
	 * Instantiates a new jMS send thread.
	 * 
	 * @param queue
	 *            the queue
	 * @param messageProducer
	 *            the message producer
	 * @param session
	 *            the session
	 * @param destination
	 *            the destination
	 * @param commitSize
	 *            the commit size
	 */
	public JmsSendThread(BlockingQueue<EsbLogMessage> queue, MessageProducer messageProducer, Session session,
			int commitSize, ConnectionFactory connectionFactory, String queueName, String threadName) {
		if (queue == null || messageProducer == null || session == null || connectionFactory == null
				|| queueName == null) {
			throw new IllegalArgumentException("参数不合法，请重新检查参数！");
		}
		if (threadName == null) {
			this.setName("JMS-SEND-THREAD" + this.getId());
		} else {
			this.setName("JMS-SEND-" + threadName + "-" + this.getId());
		}
		this.setDaemon(true);
		this.queue = queue;
		this.session = session;
		this.messageProducer = messageProducer;
		this.connectionFactory = connectionFactory;
		this.queueName = queueName;
		if (commitSize > 0) {
			this.commitSize = commitSize;
		}
	}

	public boolean isStart() {
		return start;
	}

	public void shutdown() {
		this.shutdown = true;
	}

	@Override
	public synchronized void start() {
		super.start();
		start = true;
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-4-3 下午3:38:39
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (start && !shutdown) {
			this.refreshState = true;
			EsbLogMessage esbMsg = null;
			ArrayList<EsbLogMessage> messages = new ArrayList<EsbLogMessage>(commitSize * 10);
			try {
				esbMsg = queue.take();
				TextMessage msg = session.createTextMessage();
				HeaderUtil.map2jmsProperties(esbMsg.getHeader(), msg);
				msg.setText(esbMsg.getBody());
				messageProducer.send(msg);
				esbMsg = null;
				queue.drainTo(messages, commitSize * 10);
				int index = 0;
				for (EsbLogMessage esbMessage : messages) {
					index++;
					TextMessage txtMsg = session.createTextMessage();
					//如果消息内容是空的，则跳过这个消息
					if(txtMsg.getText() == null ||"".equals(txtMsg.getText())){
						continue;
					}
					HeaderUtil.map2jmsProperties(esbMessage.getHeader(), txtMsg);
					msg.setText(esbMessage.getBody());
					messageProducer.send(txtMsg);
					if (index % commitSize == 0) {// 第一次不会提交,当最后一次提交后,如果还有没提交的,交由后续的提交
						JmsUtils.commitIfNecessary(session);
					}
				}
				JmsUtils.commitIfNecessary(session);
			} catch (JMSException e) {
				if (esbMsg != null) {
					messages.add(esbMsg);
				}
				onException(e, messages);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		start = false;
	}

	public void onException(Exception je, ArrayList<EsbLogMessage> messages) {
		// 把数据返回到内存队列中
		queue.addAll(messages);
		// this.shutdown();
		try {
			JmsUtils.rollbackIfNecessary(session);
		} catch (JMSException e) {
			// ignore this exception
		}
		JmsUtils.closeMessageProducer(messageProducer);
		JmsUtils.closeSession(session);
		LOGGER.warn(je.getMessage(), je);
		while (refreshState) {
			try {
				refresh();
				refreshState = false;
			} catch (JMSException e) {
				LOGGER.error("\n" + e.getMessage() + "线程:" + this.getName() + "刷新失败!", e);
			}
		}
	}

	public void refresh() throws JMSException {
		session = createSession(true, Session.SESSION_TRANSACTED);
		Destination destination = session.createQueue(queueName);
		messageProducer = session.createProducer(destination);
	}

	protected Connection createConnection() throws JMSException {
		return connectionFactory.createConnection();
	}

	protected Session createSession(boolean transacted, int acknowledgeMode) throws JMSException {
		if (connection == null) {
			connection = createConnection();
		}
		return connection.createSession(transacted, acknowledgeMode);
	}

}

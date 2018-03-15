/*
 * PROJECT NAME: esb-runtime-server-common
 * PACKAGE NAME: com.deppon.esb.server.common.utils.db
 * FILE    NAME: DbSender.java
 * COPYRIGHT: Copyright(c) 2012 Deppon All Rights Reserved.
 */
package org.hbhk.aili.esb.server.common.log.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.esb.common.constant.ESBServiceConstant;
import org.hbhk.aili.esb.server.common.entity.ESBHeader;
import org.hbhk.aili.esb.server.common.exception.LogSendException;
import org.hbhk.aili.esb.server.common.log.ISender;
import org.hbhk.aili.esb.server.common.utils.ESBHeaderCutOffUtil;
import org.hbhk.aili.esb.server.common.utils.HeaderUtil;
import org.hbhk.aili.esb.server.common.utils.jms.EsbLogMessage;
import org.springframework.beans.factory.InitializingBean;

/**
 * 数据库日志发送器，注意，是阻塞发送器，如果当前发送器容量已经满则则阻塞发送方法 TODO（描述类的职责）
 * 
 * @author davidcun
 * @date 2013-4-17 下午05:33:28
 */
public class DbSender implements ISender, InitializingBean {

	private final Log LOG = LogFactory.getLog(DbSender.class);
	private volatile int queueCapacity = 200000;
	private int threadCount = 5;
	private ArrayBlockingQueue<EsbLogMessage> msgQueue;
	private ExecutorService executorService;
	private IEsbLogPersistence esbLogPersistence;
	static final AtomicInteger senderThreadNumber = new AtomicInteger(1);

	private void init() {
		msgQueue = new ArrayBlockingQueue<EsbLogMessage>(queueCapacity);
		executorService = Executors.newFixedThreadPool(threadCount,
				new DbSenderThreadFactory());
		Thread thread = new Thread(Thread.currentThread().getThreadGroup(),
				new LogMessageRunnable(msgQueue, executorService),
				"DbSender-LogMessageRunnable-thread-"
						+ senderThreadNumber.getAndIncrement());
		thread.setDaemon(true);
		thread.start();
	}

	class LogMessageRunnable implements Runnable {
		public LogMessageRunnable(ArrayBlockingQueue<EsbLogMessage> queue,
				ExecutorService executorService) {
			this.queue = queue;
			this.executorService = executorService;
		}

		private ArrayBlockingQueue<EsbLogMessage> queue;
		private ExecutorService executorService;

		@Override
		public void run() {
			// TODO 设置启停
			while (true) {
				try {
					ArrayList<EsbLogMessage> messages = new ArrayList<EsbLogMessage>();

					if (queue.size() < 1) {
						Thread.sleep(500);
					} else if (queue.size() > 10000) {// TODO 10000可配置
						queue.drainTo(messages, 10000);
					} else {
						queue.drainTo(messages);
					}
					if (messages.size() > 0) {
						executorService.submit(new EsbLogPersistenceRunnable(
								esbLogPersistence, messages));
					}

				} catch (InterruptedException e) {
					LOG.error("LogMessageRunnable run error", e);
				}
			}
		}
	}

	class EsbLogPersistenceRunnable implements Runnable {

		private IEsbLogPersistence esbLogPersistence;
		private List<EsbLogMessage> messages;

		public EsbLogPersistenceRunnable(IEsbLogPersistence esbLogPersistence,
				List<EsbLogMessage> messages) {
			this.esbLogPersistence = esbLogPersistence;
			this.messages = messages;
		}

		@Override
		public void run() {
			try {
				esbLogPersistence.save(messages);
			} catch (Throwable e) {
				LOG.error("save message error", e);
			}
		}

	}

	static class DbSenderThreadFactory implements ThreadFactory {
		static final AtomicInteger poolNumber = new AtomicInteger(1);
		final ThreadGroup group;
		final String namePrefix;
		final AtomicInteger threadNumber = new AtomicInteger(1);

		DbSenderThreadFactory() {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread()
					.getThreadGroup();
			namePrefix = "DbSender" + "-pool-" + poolNumber.getAndIncrement()
					+ "-thread-";
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix
					+ threadNumber.getAndIncrement(), 0);
			if (!t.isDaemon())
				t.setDaemon(true);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}

	/**
	 * 会阻塞发送线程，如果线程被中断会跑出{@link LogSendException}异常 TODO（方法详细描述说明、方法参数的具体涵义）
	 * 
	 * @author davidcun
	 * @date 2013-4-18 下午03:58:12
	 * @see com.deppon.esb.server.common.utils.db.IDbSender#send(org.hbhk.aili.esb.server.common.entity.ESBHeader,
	 *      java.util.Map, java.lang.String)
	 * @exception LogSendException
	 */
	@Override
	public void send(ESBHeader esbHeader, Map<String, Object> header,
			String body) {

		Map<String, Object> headerToUse = HeaderUtil.esbHeader2Map(esbHeader);

		if (headerToUse == null) {
			headerToUse = new HashMap<String, Object>();
		}

		if (header != null) {
			headerToUse.putAll(header);
		}

		if (headerToUse.get(ESBServiceConstant.ESB_LOGMSG_CREATETIME) == null) {
			headerToUse.put(ESBServiceConstant.ESB_LOGMSG_CREATETIME,
					new Date());
		}

		EsbLogMessage message = new EsbLogMessage();
		
		
		ESBHeaderCutOffUtil.truncEsbHeaderProperties(headerToUse);
		
		message.setHeader(headerToUse);
		message.setBody(body);
		try {
			msgQueue.put(message);
		} catch (InterruptedException e) {
			LOG.error(String.format("com.deppon.esb.server.common.utils.db.DbSender put message error"),e);
			throw new LogSendException("DbSender msgQueue full", e);
		}
	}

	/**
	 * 
	 * @author HuangHua
	 * @date 2013-4-16 下午8:02:05
	 * @see com.deppon.esb.server.common.utils.db.IDbSender#getQueueSize()
	 */
	@Override
	public int getQueueSize() {
		return msgQueue.size();
	}

	public IEsbLogPersistence getEsbLogPersistence() {
		return esbLogPersistence;
	}

	public void setEsbLogPersistence(IEsbLogPersistence esbLogPersistence) {
		this.esbLogPersistence = esbLogPersistence;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

}

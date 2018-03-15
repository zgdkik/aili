package com.deppon.esb.management.common.adapter.mail;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 一期移植，邮件发送执行器
 * @author HuangHua
 * @date 2012-12-27 上午9:52:45
 */
public class EmailSenderExecutor  implements InitializingBean, DisposableBean {

	protected Log logger = LogFactory.getLog(this.getClass());
	// 线程池
	private ThreadPoolExecutor threadPool;
	// 核心线程数
	private int corePoolSize = 5;
	// 最大线程数
	private int maxPoolSize = 50;

	/**
	 * 初始化线程池
	 */
	private void initThreadPool() {
		if (threadPool == null) {
			threadPool = new ThreadPoolExecutor(corePoolSize, // corePoolSize
					corePoolSize, // maximumPoolSize
					30, // 60 seconds keepAliveTime
					TimeUnit.SECONDS, // TimeUnit
					new ArrayBlockingQueue<Runnable>(maxPoolSize), // workQueue
					new ThreadPoolExecutor.CallerRunsPolicy()); // RejectedExecutionHandler
		}
	}

	@Override
	public void destroy() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("the EmailSenderExecutor will destroy....");
		}
		// 释放线程池
		if (threadPool != null) {
			threadPool.shutdown();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initThreadPool();
	}

	public ThreadPoolExecutor getThreadPool() {
		return threadPool;
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

}

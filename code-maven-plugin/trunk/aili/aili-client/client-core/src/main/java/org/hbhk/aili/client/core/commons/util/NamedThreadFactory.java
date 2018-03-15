package org.hbhk.aili.client.core.commons.util;

import java.util.concurrent.ThreadFactory;

/**
 *创建指定名字的线程。
 */
public class NamedThreadFactory implements ThreadFactory {
	// 线程名称
	private final String name;
	
	// 是否守护
	private final boolean daemon;

	/**
	 * 
	 * <p>Title: NamedThreadFactory</p>
	 * <p>Description: 构造函数，用于初始化类成员变量</p>
	 * @param name
	 * @param daemon
	 */
	public NamedThreadFactory(String name, boolean daemon) {
		this.name = name;
		this.daemon = daemon;
	}

	@Override
	public Thread newThread(Runnable task) {
		Thread thread = new Thread(task, name);
		thread.setDaemon(daemon);
		return thread;
	}
}
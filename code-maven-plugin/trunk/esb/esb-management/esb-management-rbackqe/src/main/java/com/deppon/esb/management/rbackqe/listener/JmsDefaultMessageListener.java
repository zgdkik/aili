package com.deppon.esb.management.rbackqe.listener;

import java.util.UUID;

import com.deppon.esb.management.rbackqe.listener.thread.JmsRunable;


public class JmsDefaultMessageListener extends JmsPollingMessageListener{

	private static final String DEFAULTNAME = "JMS-";

	private String threadName = DEFAULTNAME + UUID.randomUUID();

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public void start() {
		dostart();
	}
	
	private void dostart() {
		JmsRunable runnable = new JmsRunable() {
			@Override
			public void run() {
				doInitialize();
			}
		};
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.setName(threadName);
		thread.start();
	}

	protected void doInitialize() {
		super.doInitialize();
	}

	public void shutdown() {
		doshutdown();
	}

	protected void doshutdown() {
		super.doshutdown();
	}

}

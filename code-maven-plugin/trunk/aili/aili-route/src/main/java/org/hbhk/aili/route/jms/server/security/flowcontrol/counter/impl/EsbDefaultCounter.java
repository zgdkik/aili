package org.hbhk.aili.route.jms.server.security.flowcontrol.counter.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.hbhk.aili.route.jms.server.security.flowcontrol.counter.ICounter;

public class EsbDefaultCounter implements ICounter{
	/**
	 * 计数次数
	 */
	private AtomicLong counter ;
	/**
	 * 计数器 key,即接口前端服务编码
	 */
	private String key;
	
	private long initValue =Long.MAX_VALUE;
	
	/**
	 * 默认最大计数值 =Long.MAX_VALUE
	 * @param key
	 */
	public EsbDefaultCounter(String key){
		this.key=key;
		this.counter = new AtomicLong(initValue);
	}
	
	public EsbDefaultCounter(String key,long maxLimit){
		this.key=key;
		this.counter=new AtomicLong(maxLimit);
		this.initValue=maxLimit;
	}
	@Override
	public long get() {
		return counter.get();
	}

	@Override
	public long getAndDecrement() {
		
		return counter.getAndDecrement();
	}
	
	@Override
	public long getAndIncrement() {
		return counter.getAndIncrement();
	}
	@Override
	public long decrementandGet() {
		return counter.decrementAndGet();
	}

	@Override
	public long incrementAndGet() {
		return counter.incrementAndGet();
	}
	
	@Override
	public void reset() {
		counter.set(initValue);
	}

	@Override
	public String getKey() {
		return key;
	}
	@Override
	public long getInitValue() {
		return initValue;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("counter:").append(this.counter.get())
			.append("  initValue:").append(this.initValue)
			.append("  key:").append(this.key);
		return buffer.toString();
	}
}

package org.hbhk.aili.route.jms.server.security.flowcontrol.counter;

/**
 * 计数器
 * @author qiancheng
 * 
 */
public interface ICounter {

	/**
	 * 获取当前计数器值
	 * 
	 * @return
	 */
	long get();

	/**
	 * 返回当前计数器值，然后计数器递减
	 * @return
	 */
	long getAndDecrement();

	/**
	 * 计数器递减，然后返回当前计数器值
	 * @return
	 */
	long decrementandGet();
	
	/**
	 * 返回当前计数器值，然后计数器递加
	 * @return
	 */
	long getAndIncrement();

	/**
	 * 计数器递加，然后返回当前计数器值
	 * @return
	 */
	long incrementAndGet();

	/**
	 * 计数器清零
	 */
	void reset();

	/**
	 * 获取计数器key
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * 计数器初始值
	 * 
	 * @return
	 */
	long getInitValue();
}

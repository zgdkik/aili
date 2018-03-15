package org.hbhk.aili.route.jms.server.security.flowcontrol.container;

import java.util.List;

import org.hbhk.aili.route.jms.server.security.flowcontrol.counter.ICounter;

/**
 * 
 * @author qiancheng
 *
 */
public interface ICountContainer {
	
	void init();
	/**
	 * 遍历容器
	 * @return
	 */
	List<ICounter> traversal();
	/**
	 * 获取key指定的计数器
	 */
	ICounter get(String key);
	/**s
	 * 添加计数器
	 */
	int add(String key,ICounter counter);
	
	/**
	 * 删除计数器
	 */
	int delete(String key);
	/**
	 * 清空整个容器
	 */
	void clean();
	/**
	 * 重置所有计数器的值
	 */
	void resetAllCounter();
	/**
	 * 判断是否存在key的计数器
	 * @param key
	 * @return
	 */
	boolean exists(String key);
	
	/**
	 * 获取容器中有多少个计数器
	 */
	int size();
}

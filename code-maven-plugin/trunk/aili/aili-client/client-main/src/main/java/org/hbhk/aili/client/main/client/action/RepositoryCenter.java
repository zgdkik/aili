package org.hbhk.aili.client.main.client.action;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RepositoryCenter {

	// Logger
	private static final Log LOG = LogFactory.getLog(RepositoryCenter.class);

	// 数据center
	private static RepositoryCenter repositoryCenter;

	// Logger
	private static ReentrantLock lock = new ReentrantLock();

	// 数据仓库
	private Map<String, Object> repository;

	static {
		if (repositoryCenter == null) {
			try {

				lock.lock();
				if (ObjectUtils.NULL.equals(ObjectUtils.defaultIfNull(
						repositoryCenter, ObjectUtils.NULL))) {
					repositoryCenter = new RepositoryCenter();
				}
			} catch (Exception e) {
				LOG.error("lock exception", e);
			}
			lock.unlock();
		}
	}

	/**
	 * 构造方法
	 */
	private RepositoryCenter() {
		repository = new ConcurrentHashMap<String, Object>();
	}

	/**
	 * 单例
	 * 
	 * @return
	 */
	public static RepositoryCenter getInstance() {
		return repositoryCenter;
	}

	/**
	 * 向数据仓库中注册
	 * 
	 * @param key
	 * @param value
	 */
	public void register(String key, Object value) {
		repository.put(key, value);
	}

	/**
	 * 获得数据仓库中的值
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return repository.get(key);
	}
}
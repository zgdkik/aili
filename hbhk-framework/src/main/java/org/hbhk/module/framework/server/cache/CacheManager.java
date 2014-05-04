package org.hbhk.module.framework.server.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 缓存管理器
 */
public final class CacheManager {

	/**
	 * 自己
	 */
	private static final CacheManager INSTANCE = new CacheManager();

	/**
	 * 保存所有缓存实例
	 */
	private final Map<String, ICache> uuidCaches = new ConcurrentHashMap<String, ICache>();

	/**
	 *  禁止从外部拿到实例
	  * 创建一个新的实例 CacheManager.
	  * @since 0.6
	 */
	private CacheManager() {
	}

	public static CacheManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 系统启动后自动注册所有的缓存类别
	 * 
	 * @param cache
	 * @throws Exception 
	 */
	public <K,V> void registerCacheProvider(ICache<K, V> cache) throws Exception {
		// 不允许UUID重复，应用必须在实现的Cache接口确保命名不重复
		String cacheId = cache.getCacheId();
		if (uuidCaches.containsKey(cacheId)) {
			throw new Exception("CacheId:[" + cacheId
					+ "] to Class:[" + cache.getClass().getName()
					+ "] and Class:["+ uuidCaches.get(cacheId).getClass().getName() +"]");
		}
		uuidCaches.put(cacheId, cache);
	}

	/**
	 * 根据uuid获取缓存实例
	 * getCache
	 * @param cacheId
	 * @return ICache<K,V>
	 * @throws Exception 
	 * @since:
	 */
	public <K,V> ICache<K, V> getCache(String cacheId) throws Exception {
		ICache<K, V> cache = uuidCaches.get(cacheId);
		if (cache == null) {
			throw new Exception("CacheId:["+cacheId+"]");
		}
		return cache;
	}
	
	/**
	 * 根据cacheId，目标Class获取缓存实例
	 * @param t
	 * @param cacheId
	 * @return
	 * @throws Exception 
	 * @see
	 */
	public <T> T getCache(Class t,String cacheId) throws Exception {
		try {
			return (T) getCache(cacheId);
		} catch(ClassCastException e) {
			throw new Exception("CacheId:["+cacheId+"] to Class:["+t.getName()+"]");
		}
	}


	/**
	 * 销毁
	 * @see
	 */
	public void shutdown() {
		uuidCaches.clear();
	}
}
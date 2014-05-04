package org.hbhk.module.framework.server.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 提供支持缓存类
 * 
 * @since
 * @version
 */
public abstract class CacheSupport<V> extends CacheBase<String, V> {

	private static final Log LOG = LogFactory.getLog(CacheSupport.class);

	private ICacheTemplet<String, V> cacheTemplet;

	@Override
	public void set(String key, V value) {
		cacheTemplet.set(key, value);
	}

	@Override
	public V get(String key) {
		if (!cacheTemplet.isExitKey(key)) {
			V v = doSet(key);
			set(key, v);
			return v;
		}
		return cacheTemplet.get(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		CacheManager.getInstance().registerCacheProvider(this);
	}

	@Override
	public void invalid(String... keys) {
		cacheTemplet.removeMulti(keys);
	}

	public void setCacheTemplet(ICacheTemplet<String, V> cacheTemplet) {
		this.cacheTemplet = cacheTemplet;
	}

}

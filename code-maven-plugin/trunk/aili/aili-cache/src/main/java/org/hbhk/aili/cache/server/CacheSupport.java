package org.hbhk.aili.cache.server;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.cache.server.serialize.ICacheSerialize;
import org.hbhk.aili.cache.server.serialize.impl.JsonCacheSerialize;
import org.hbhk.aili.cache.server.templet.ICacheTemplet;
import org.hbhk.aili.cache.server.templet.impl.MemoryCacheTemplet;
import org.hbhk.aili.core.share.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.RowMapper;

/**
 * 提供支持缓存类
 * 
 * @since
 * @version
 */
public abstract class CacheSupport<V> extends CacheBase<String, V> {

	private static final Log log = LogFactory.getLog(CacheSupport.class);

	@Autowired(required = false)
	private ICacheTemplet<String, V> cacheTemplet;

	private ICacheSerialize<?> cacheSerialize;

	private boolean json = false;

	private boolean reconnect = true;

	private ExecutorService taskExecutor = Executors.newCachedThreadPool();

	private MemoryCacheTemplet<String> notCachetKey = new MemoryCacheTemplet<String>();
	/**
	 * 分钟
	 * 
	 * @return
	 */
	public Integer getExpire() {
		return null;
	}

	public String getCacheKey(String key) {
		key = getCacheId() + "-" + key;
		return key;
	}

	public String[] getCacheKey(String... key) {
		String[] newKey = null;
		if (key != null) {
			newKey = new String[key.length];
			for (int i = 0; i < key.length; i++) {
				String basename = key[i];
				newKey[i] = getCacheId() + "-" + basename;
			}
		}
		return newKey;
	}

	@Override
	public void set(String key, V value) {
		try {
			cacheTemplet.set(getCacheKey(key), value);
		} catch (DataAccessResourceFailureException e) {
			log.warn(e.getMessage(), e);
		}

	}

	@Override
	public void set(String key, V value, int expire) {
		try {
			cacheTemplet.set(getCacheKey(key), value, expire);
		} catch (DataAccessResourceFailureException e) {
			log.warn(e.getMessage(), e);
		}

	}

	@Override
	public V get(String key) {
		V v = null;
		try {
			if (!reconnect) {
				log.warn("redis缓存服务器异常,走数据库查询...");
				v = doSet(key);
				return v;
			}
			if (!cacheTemplet.isExitKey(getCacheKey(key))) {
				if(!notCachetKey.isExitKey(getCacheKey(key))){
					v = doSet(key);
					if(v == null){
						log.warn("缓存key:"+getCacheKey(key)+"查询数据为空,进行防穿透设置,设置时间5分钟");
						notCachetKey.set(getCacheKey(key), "not-exit-Key", 60*5);
					}
					if (getExpire() != null) {
						set(key, v, getExpire());
					} else {
						set(key, v);
					}
				}
				return v;
			} else {
				if (json) {
					String jsonStr = (String) cacheTemplet
							.get(getCacheKey(key));
					v = JsonUtil.parseJson(jsonStr, getGenericClass());
				} else {
					v = cacheTemplet.get(getCacheKey(key));
				}
//				if (v == null) {
//					log.warn("redis缓存服务器返回null,走数据库查询...");
//					v = doSet(key);
//				}
			}
		} catch (Exception e) {
			log.warn("redis缓存服务器异常,走数据库查询...", e);
			v = doSet(key);
			if (reconnect) {
				taskExecutor.execute(new Runnable() {
					@SuppressWarnings("unchecked")
					@Override
					public void run() {
						boolean flag = true;
						while (flag) {
							try {
								cacheTemplet.set("reconnect", (V) "reconnect");
								flag = false;
								reconnect = true;
								log.error("尝试连接redis成功");
							} catch (Exception e2) {
								log.error("尝试连接redis出错", e2);
								try {
									TimeUnit.SECONDS.sleep(60);
								} catch (Exception e3) {
								}
							}
						}
					}
				});
			}
			reconnect = false;
		}
		return v;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (cacheTemplet == null) {
			cacheTemplet = new MemoryCacheTemplet<V>();
		}
		cacheSerialize = cacheTemplet.getCacheSerialize();
		if (cacheSerialize != null) {
			if (cacheSerialize.equals(JsonCacheSerialize.class)) {
				json = true;
			}
		}
		CacheManager.getInstance().registerCacheProvider(this);
	}

	@Override
	public void invalid(String... keys) {
		try {
			cacheTemplet.invalid(getCacheKey(keys));
		} catch (DataAccessResourceFailureException e) {
			log.warn(e.getMessage(), e);
		}

	}

	public void setCacheTemplet(ICacheTemplet<String, V> cacheTemplet) {
		this.cacheTemplet = cacheTemplet;
	}

	@SuppressWarnings("unchecked")
	private Class<V> getGenericClass() {
		Class<?> clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		while (!(type instanceof ParameterizedType) && clazz != null
				&& clazz != Object.class) {
			clazz = clazz.getSuperclass();
			type = clazz.getGenericSuperclass();
		}

		if (!(type instanceof ParameterizedType)) {
			Class<?>[] iclazzs = clazz.getInterfaces();
			if (iclazzs.length > 0) {
				int index = -1;
				for (int i = 0; i < iclazzs.length; i++) {
					if (RowMapper.class.isAssignableFrom(iclazzs[i])) {
						index = i;
						break;
					}
				}
				if (index >= 0) {
					if (clazz.getGenericInterfaces()[index] instanceof ParameterizedType)
						type = clazz.getGenericInterfaces()[index];
				}
			}

		}

		if (!(type instanceof ParameterizedType)) {
			throw new RuntimeException("Can not find the right Generic Class.");
		}

		ParameterizedType pType = (ParameterizedType) type;
		return (Class<V>) pType.getActualTypeArguments()[0];
	}

}

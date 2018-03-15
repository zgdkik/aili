//package org.hbhk.aili.cache.server.templet.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.hbhk.aili.cache.server.serialize.ICacheSerialize;
//import org.hbhk.aili.cache.server.serialize.impl.JsonCacheSerialize;
//import org.hbhk.aili.cache.server.templet.ICacheTemplet;
//import org.hbhk.aili.cache.share.pojo.KeyValue;
//import org.springframework.beans.factory.InitializingBean;
//
//import redis.clients.jedis.JedisCluster;
//
//public class Redis3CacheTemplet<V> implements ICacheTemplet<String, V>,
//		InitializingBean {
//	private static final Log log = LogFactory.getLog(Redis3CacheTemplet.class);
//
//	private JedisCluster jedisCluster;
//
//	private ICacheSerialize<String> cacheSerialize;
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public V get(String key) {
//		log.info("缓存key:"+key);
//		keyNot(key);
//		String str = jedisCluster.get(key);
//		return (V) str;
//	}
//
//	public void invalid(String... keys) {
//		if (keys == null || keys.length == 0) {
//			log.error("key不允许为null或空串!");
//			throw new RuntimeException("key不允许为null或空串!");
//		}
//		for (String key : keys) {
//			log.info("失效缓存key:"+key);
//			jedisCluster.del(key);
//		}
//
//	}
//
//	@Override
//	public boolean set(String key, V value, int expire) {
//		try {
//			keyNot(key);
//			String jsonStr = cacheSerialize.serialize(value);
//			log.info("设置缓存key:"+key);
//			jedisCluster.setex(key, expire, jsonStr);
//			return true;
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			return false;
//		}
//
//	}
//
//	public boolean setNX(String key, V value, int expire) {
//		try {
//			keyNot(key);
//			String jsonStr = cacheSerialize.serialize(value);
//			log.info("设置缓存key:"+key);
//			jedisCluster.setex(key, expire, jsonStr);
//			return true;
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			return false;
//		}
//
//	}
//
//	public boolean setPi(final List<KeyValue<V>> keyValues, int expire) {
//		try {
//			return false;
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			return false;
//		}
//
//	}
//
//	@Override
//	public void invalid(String key) {
//		jedisCluster.del(key);
//	}
//
//	@Override
//	public boolean set(Map<String, V> values) {
//		return false;
//	}
//
//	@Override
//	public boolean set(Map<String, V> values, int expire) {
//		return false;
//	}
//
//	@Override
//	public long ttl(String key) {
//		if (StringUtils.isBlank((java.lang.String) key)) {
//			log.error("key不允许为null或空串!");
//			throw new RuntimeException("key不允许为null或空串!");
//		}
//		log.info("获取 key ttl:"+key);
//		Long ttl = jedisCluster.ttl(key);
//		return ttl;
//	}
//
//	@Override
//	public boolean set(String key, V value) {
//		keyNot(key);
//		String jsonStr = cacheSerialize.serialize(value);
//		log.info("设置缓存key:"+key);
//		jedisCluster.set(key, jsonStr);
//		return false;
//	}
//
//	@Override
//	public boolean isExitKey(String key) {
//		boolean isExitKey = jedisCluster.exists(key);
//		return isExitKey;
//	}
//
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		if (cacheSerialize == null) {
//			cacheSerialize = new JsonCacheSerialize();
//		}
//	}
//
//	private void keyNot(String key) {
//		if (StringUtils.isEmpty(key)) {
//			throw new RuntimeException("key不允许为null或空串!");
//		}
//	}
//
//	public ICacheSerialize<String> getCacheSerialize() {
//		return cacheSerialize;
//	}
//
//	public void setCacheSerialize(ICacheSerialize<String> cacheSerialize) {
//		this.cacheSerialize = cacheSerialize;
//	}
//
//	public JedisCluster getJedisCluster() {
//		return jedisCluster;
//	}
//
//	public void setJedisCluster(JedisCluster jedisCluster) {
//		this.jedisCluster = jedisCluster;
//	}
//
//}

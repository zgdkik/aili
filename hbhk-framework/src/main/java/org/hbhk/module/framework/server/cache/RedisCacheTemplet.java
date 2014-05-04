package org.hbhk.module.framework.server.cache;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisCacheTemplet<V> implements ICacheTemplet<String, V> {
	private static final Log LOG = LogFactory.getLog(RedisCacheTemplet.class);
	private StringRedisTemplate StringRedisTemplate;

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		StringRedisTemplate = stringRedisTemplate;
	}

	@Override
	public V get(String key) {
		if (StringUtils.isBlank((java.lang.String) key)) {
			LOG.error("key不允许为null或空串!");
			throw new RuntimeException("key不允许为null或空串!");
		}
		final String newKey = key;
		V value = StringRedisTemplate.execute(new RedisCallback<V>() {
			@SuppressWarnings("unchecked")
			@Override
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				V obj = null;
				if (connection.exists(newKey.getBytes())) {
					byte[] values = connection.get(newKey.getBytes());
					if (values != null && values.length > 0) {
						obj = (V) SerializeUtil.deserializeObject(values);
					}
				}
				return obj;
			}
		});

		return value;
	}

	public void invalid(String... keys) {
		if (keys == null || keys.length == 0) {
			LOG.error("key不允许为null或空串!");
			throw new RuntimeException("key不允许为null或空串!");
		}
		final String[] newKeys = keys;
		StringRedisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				for (String key : newKeys) {
					if (connection.exists(key.getBytes())) {
						connection.del(key.getBytes());
					}
				}
				return null;
			}

		});

	}

	@Override
	public boolean set(String key, V value, int expire) {

		if (StringUtils.isBlank(key) || value == null) {
			LOG.error("key或value不允许为null或空串!");
			throw new RuntimeException("key或value不允许为null或空串!");
		}
		final String newKey = key;
		final V newValue = value;
		StringRedisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				// String valueStr = CacheUtils.toJsonString(newValue);
				byte[] bs = SerializeUtil.serializeObject(newValue);
				connection.set(newKey.getBytes(), bs);
				return null;
			}

		});

		return false;
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMulti(String... keys) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean set(Map<String, V> values) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean set(Map<String, V> values, int expire) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long ttl(String key) {
		return 0;
	}

	@Override
	public boolean set(String key, V value) {

		if (StringUtils.isBlank(key) || value == null) {
			LOG.error("key或value不允许为null或空串!");
			throw new RuntimeException("key或value不允许为null或空串!");
		}
		final String newKey = key;
		final V newValue = value;
		StringRedisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection connection)
					throws DataAccessException {
				// String valueStr = CacheUtils.toJsonString(newValue);
				byte[] bs = SerializeUtil.serializeObject(newValue);
				connection.set(newKey.getBytes(), bs);
				return null;
			}
		});
		return false;
	}

	@Override
	public boolean isExitKey(String key) {
		final String newKey = key;
		boolean isExitKey = StringRedisTemplate
				.execute(new RedisCallback<Boolean>() {
					@Override
					public Boolean doInRedis(RedisConnection connection)
							throws DataAccessException {
						return connection.exists(newKey.getBytes());
					}
				});

		return isExitKey;
	}

}

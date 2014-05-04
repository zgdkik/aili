package org.hbhk.module.framework.server.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.module.framework.server.cache.CacheSupport;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class MessageCache {

	public static final String UUID = "MessageCache";
	public static final String I18NKEYS = "i18nkeys";
	private Log logger = LogFactory.getLog(MessageCache.class);

	private CacheSupport<Properties> cacheSupport;

	private CacheSupport<String> cacheSupportI18nKeys;

	public void setCacheSupport(CacheSupport<Properties> cacheSupport) {
		this.cacheSupport = cacheSupport;
	}

	public void setCacheSupportI18nKeys(
			CacheSupport<String> cacheSupportI18nKeys) {
		this.cacheSupportI18nKeys = cacheSupportI18nKeys;
	}

	public CacheSupport<Properties> getCacheSupport() {
		return cacheSupport;
	}

	public CacheSupport<String> getCacheSupportI18nKeys() {
		return cacheSupportI18nKeys;
	}

	public void doInitialization() {
		final String prefix = "org/hbhk/";
		try {
			// 加载指定位置的properties文件
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			Resource[] resources = resolver.getResources("classpath*:" + prefix
					+ "**/server/META-INF/messages/message*.properties");
			Properties properties = new Properties();
			Map<String, Properties> map = new HashMap<String, Properties>();
			for (Resource resource : resources) {
				String path = resource.getURL().getPath();
				String classpath = path.substring(path.lastIndexOf(prefix));
				if (logger.isInfoEnabled()) {
					logger.info("[Framework] add message bundle: " + classpath);
				}
				String subStr = "module/";
				String moduleStr =  classpath.substring(classpath.indexOf(subStr)+subStr.length(), classpath.length());
				final String moduleName =moduleStr.substring(0,moduleStr.indexOf("/"));
				InputStream in = resource.getInputStream();
				try {
					Properties p = new Properties();
					p.load(in);
					map.put(moduleName, p);
					properties.putAll(map);
					cacheSupportI18nKeys.set(moduleName+I18NKEYS, convertI18nKeys(p));

				} finally {
					in.close();
				}

			}
			cacheSupport.set(UUID, properties);

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String getCacheId() {
		return UUID;
	}

	public Properties getI18nProperties(String key) {
		return cacheSupport.get(key);
	}

	public String getI18nKeys(String key) {
		return cacheSupportI18nKeys.get(key);
	}

	@SuppressWarnings("rawtypes")
	private String convertI18nKeys(Properties pros) {
		Enumeration enumeration = pros.propertyNames();
		StringBuffer keys = new StringBuffer();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement()+",";
					keys.append(key);
		}
		return keys.toString();
	}
}

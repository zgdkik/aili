package com.feisuo.sds.base.share.util;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.server.properties.AiliPropertyPlaceholderConfigurer;

import com.feisuo.sds.base.share.constants.FrontendConstants;

public class SystemParameterUtil {

	public static String getValue(String key) {
		ICache<String, String> cache = CacheManager.getInstance().getCache(
				FrontendConstants.SYSTEM_PARAMETER_CACHE_UUID);
		String val = cache.get(key);
		if(StringUtils.isEmpty(val)){
			val = AiliPropertyPlaceholderConfigurer.getProperties(key);
		}
		return val;
	}
	
	public static void invalidate(String key) {
		ICache<String, String> cache = CacheManager.getInstance().getCache(
				FrontendConstants.SYSTEM_PARAMETER_CACHE_UUID);
		cache.invalid(key);
	}
}

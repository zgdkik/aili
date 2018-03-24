package com.yimidida.ows.base.share.util;

import org.apache.commons.lang3.StringUtils;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ymdp.cache.server.CacheManager;
import com.yimidida.ymdp.cache.server.ICache;
import com.yimidida.ymdp.core.server.properties.MutilPropertyPlaceholderConfigurer;

public class SystemParameterUtil {

	public static String getValue(String key) {
		ICache<String, String> cache = CacheManager.getInstance().getCache(
				FrontendConstants.SYSTEM_PARAMETER_CACHE_UUID);
		String val = cache.get(key);
		if(StringUtils.isEmpty(val)){
			val = MutilPropertyPlaceholderConfigurer.getProperties(key);
		}
		return val;
	}
	
	public static void invalidate(String key) {
		ICache<String, String> cache = CacheManager.getInstance().getCache(
				FrontendConstants.SYSTEM_PARAMETER_CACHE_UUID);
		cache.invalid(key);
	}
}

package org.hbhk.ams.gateway.server.filter;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

public class RateLimitCache {

	public static Map<String, RateLimiter> ｒateLimiterCache = Maps
			.newConcurrentMap();

	public static RateLimiter getRateLimiter(String url, double permitsPerSecond) {
		if (ｒateLimiterCache.containsKey(url)) {
			return ｒateLimiterCache.get(url);
		} else {
			RateLimiter rateLimiter = RateLimiter.create(permitsPerSecond);
			ｒateLimiterCache.put(url, rateLimiter);
			return rateLimiter;
		}
	}

	public static RateLimiter getRateLimiter(String url) {
		return ｒateLimiterCache.get(url);
	}

}

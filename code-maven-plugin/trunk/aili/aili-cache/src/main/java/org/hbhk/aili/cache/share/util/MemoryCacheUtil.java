package org.hbhk.aili.cache.share.util;

import org.hbhk.aili.cache.server.templet.impl.MemoryCacheTemplet;

public class MemoryCacheUtil {

	private static MemoryCacheTemplet<Object> memoryCacheTemplet = new MemoryCacheTemplet<Object>();

	public static void set(String key, Object value) {
		memoryCacheTemplet.set(key, value);
	}

	public static void set(String key, Object value, int expire) {
		memoryCacheTemplet.set(key, value,expire*60);
	}

	@SuppressWarnings("unchecked")
	public static <V> V get(String key) {
		return (V) memoryCacheTemplet.get(key);
	}

	public void invalid(String... key) {
		memoryCacheTemplet.invalid(key);
	}
}

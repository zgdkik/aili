package org.hbhk.test.cache;

import org.hbhk.module.framework.server.cache.CacheSupport;

public class CacheSupportTest  extends  CacheSupport<String>{

	@Override
	public String getCacheId() {
		
		return "hbhk";
	}

	@Override
	public String doSet(String key) {
		return "hebo";
	}

}

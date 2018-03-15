package org.hbhk.aili.cache.server.serialize.impl;

import org.hbhk.aili.cache.server.serialize.ICacheSerialize;
import org.hbhk.aili.core.share.util.JsonUtil;

public class JsonCacheSerialize implements ICacheSerialize<String> {

	@Override
	public String serialize(Object obj) {
		return JsonUtil.toJson(obj);
	}

	@Override
	public Object deserialize(String obj) {
		
		return JsonUtil.parseJson(obj, null);
	}

	
}

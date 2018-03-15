package com.feisuo.sds.user.server.cache;

import org.hbhk.aili.cache.server.CacheSupport;
import org.springframework.stereotype.Component;

import com.feisuo.sds.base.share.constants.FrontendConstants;
import com.feisuo.sds.user.share.entity.PrivilegeEntity;

@Component
public class FunctionCache extends CacheSupport<PrivilegeEntity> {

	@Override
	public String getCacheId() {
		return FrontendConstants.FUNCTION_ENTITY_CACHE_UUID;
	}

	@Override
	public PrivilegeEntity doSet(String key) {
		return null;
	}

}

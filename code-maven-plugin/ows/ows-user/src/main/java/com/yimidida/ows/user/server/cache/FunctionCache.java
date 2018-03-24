package com.yimidida.ows.user.server.cache;

import org.springframework.stereotype.Component;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.user.share.entity.PrivilegeEntity;
import com.yimidida.ymdp.cache.server.CacheSupport;

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

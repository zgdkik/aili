package org.hbhk.aili.user.server.cache;

import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.user.share.entity.PrivilegeEntity;
import org.springframework.stereotype.Component;

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

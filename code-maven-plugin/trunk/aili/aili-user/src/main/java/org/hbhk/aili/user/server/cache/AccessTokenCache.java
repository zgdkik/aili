package org.hbhk.aili.user.server.cache;

import java.util.List;

import org.hbhk.aili.base.share.constants.FrontendConstants;
import org.hbhk.aili.base.share.entity.AccessToken;
import org.hbhk.aili.cache.server.CacheSupport;
import org.hbhk.aili.core.share.util.BeanToMapUtil;
import org.hbhk.aili.user.server.dao.IAccessTokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AccessTokenCache extends CacheSupport<AccessToken> {

	@Autowired
	private IAccessTokenDao accessTokenDao;

	@Override
	public String getCacheId() {
		return FrontendConstants.ACCESS_TOKEN_CACHE_KEY;
	}

	@Override
	public AccessToken doSet(String key) {
		AccessToken at = new AccessToken();
		at.setAccessToken(key);
		List<AccessToken> list = accessTokenDao.get(BeanToMapUtil.convert(at));
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	 @Override
	public Integer getExpire() {
		return 60*24*7;
	}
}

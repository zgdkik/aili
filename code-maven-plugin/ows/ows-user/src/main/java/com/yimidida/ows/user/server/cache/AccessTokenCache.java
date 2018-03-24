package com.yimidida.ows.user.server.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.yimidida.ows.base.share.constants.FrontendConstants;
import com.yimidida.ows.base.share.entity.AccessToken;
import com.yimidida.ows.user.server.dao.IAccessTokenDao;
import com.yimidida.ymdp.cache.server.CacheSupport;
import com.yimidida.ymdp.core.share.util.BeanToMapUtil;

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

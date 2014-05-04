package org.hbhk.module.framework.server.service.impl;

import javax.annotation.Resource;

import org.hbhk.module.framework.server.cache.CacheSupport;
import org.hbhk.module.framework.server.service.IUserCache;
import org.hbhk.module.framework.shared.domain.UserEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;
import org.springframework.util.StringUtils;

public class UserCacheImpl implements IUserCache {

	@Resource
	private CacheSupport<UserEntity> cacheSupport;
	@Resource(name="cacheSupport")
	private CacheSupport<UsersEntity> usersCacheSupport;

	public UserEntity getUser(String key) {
		UserEntity u = cacheSupport.get(key);
		return u;
	}

	public UsersEntity queryUserCache(String key) {

		UsersEntity users = usersCacheSupport.get(key);

		return users;
	}

	public void saveUserCache(String key,UsersEntity users) {
		
		if(users!=null && !StringUtils.isEmpty(key)){
			usersCacheSupport.set(key, users);
		}
	
	}

	public CacheSupport<UserEntity> getCacheSupport() {
		return cacheSupport;
	}

	public void setCacheSupport(CacheSupport<UserEntity> cacheSupport) {
		this.cacheSupport = cacheSupport;
	}

}

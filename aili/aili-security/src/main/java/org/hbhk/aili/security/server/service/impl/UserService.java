package org.hbhk.aili.security.server.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.hbhk.aili.cache.server.CacheManager;
import org.hbhk.aili.cache.server.ICache;
import org.hbhk.aili.core.server.context.RequestContext;
import org.hbhk.aili.security.server.cache.LoginLimitCache;
import org.hbhk.aili.security.server.cache.UserResourceCache;
import org.hbhk.aili.security.server.context.LoginLimitContext;
import org.hbhk.aili.security.server.context.UserContext;
import org.hbhk.aili.security.server.dao.IUserDao;
import org.hbhk.aili.security.server.service.IUserService;
import org.hbhk.aili.security.share.define.UserConstants;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

	@Resource
	private IUserDao userDao;

	@Autowired(required=false)
	private LoginLimitCache limitCache;

	@Override
	public UserInfo getMe(String username) {
		return null;
	}

	@Override
	public boolean login(String username, String password) {
		UserInfo userInfo = userDao.login(username, password);
		if (userInfo != null) {
			UserContext.setCurrentUser(userInfo);
			UserContext.setCurrentUserName(username);
			// 设置session
			RequestContext.setSessionAttribute(UserConstants.CURRENT_USER_NAME,
					username);
			if (limitCache != null) {
				limitCache.set(username, LoginLimitContext.getSessionId());
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean validate(String url) {
		return false;
	}

	@Override
	public boolean validate(String username, String url) {
		try {
			ICache<String, Set<String>> cache = CacheManager.getInstance()
					.getCache(UserResourceCache.cacheID);
			Set<String> urls = cache.get(username);
			if (urls.contains(url)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void logout() {
		UserContext.remove();
	}

}
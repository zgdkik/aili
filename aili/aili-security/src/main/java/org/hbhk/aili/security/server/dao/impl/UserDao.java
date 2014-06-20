package org.hbhk.aili.security.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hbhk.aili.cache.server.aop.annotation.CacheKey;
import org.hbhk.aili.cache.server.aop.annotation.ReadCache;
import org.hbhk.aili.mybatis.server.dao.impl.AiliDaoSupport;
import org.hbhk.aili.security.server.dao.IUserDao;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AiliDaoSupport<UserInfo,String> implements IUserDao {

	private final String NAMESPACE = "aili.user.";

	@Override
	@ReadCache(namespace="user",expire=60)
	public UserInfo getMe(@CacheKey String username) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("username", username);
		List<UserInfo> userInfos = getSqlSession().selectList(
				NAMESPACE + "getMe", parameter);
		if (CollectionUtils.isEmpty(userInfos)) {
			return null;
		}
		return userInfos.get(0);
	}

	@Override
	@ReadCache(namespace="userInfo",expire=60)
	public UserInfo login(String username, @CacheKey String password) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("username", username);
		parameter.put("password", password);
		List<UserInfo> userInfos = getSqlSession().selectList(
				NAMESPACE + "login", parameter);
		if (CollectionUtils.isEmpty(userInfos)) {
			return null;
		}
		return userInfos.get(0);
	}

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}

}
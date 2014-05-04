package org.hbhk.aili.security.server.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.hbhk.aili.core.server.data.AiliSqlSessionDaoSupport;
import org.hbhk.aili.security.server.dao.IUserDao;
import org.hbhk.aili.security.share.pojo.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AiliSqlSessionDaoSupport implements IUserDao {

	private final String NAMESPACE = "aili.user.";

	@Override
	public UserInfo getMe(String username) {
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
	public UserInfo login(String username, String password) {
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

}
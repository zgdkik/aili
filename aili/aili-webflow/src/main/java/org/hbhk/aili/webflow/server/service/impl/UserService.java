package org.hbhk.aili.webflow.server.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hbhk.aili.webflow.server.service.IUserService;
import org.hbhk.aili.webflow.share.model.UserInfo;

public class UserService implements IUserService {

	private static Map<String, UserInfo> users = new ConcurrentHashMap<String, UserInfo>();

	@Override
	public UserInfo createUser(String code) {
		UserInfo user = new UserInfo();
		user.setUserCode(code);
		users.put(code, user);
		return null;
	}

	@Override
	public UserInfo getUser(String code) {
		return users.get(code);
	}

}

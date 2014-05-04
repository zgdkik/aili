package org.hbhk.module.framework.server.service;

import org.hbhk.module.framework.shared.domain.UserEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;

public interface IUserCache {

	UserEntity getUser(String key);

	UsersEntity queryUserCache(String key);

	void saveUserCache(String key, UsersEntity users);

}

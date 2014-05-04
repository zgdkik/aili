package org.hbhk.module.framework.server.dao;

import org.hbhk.module.framework.shared.domain.security.UsersEntity;

public interface IUserDao {

	 void userTest();
	 UsersEntity queryUsers(String username);
}

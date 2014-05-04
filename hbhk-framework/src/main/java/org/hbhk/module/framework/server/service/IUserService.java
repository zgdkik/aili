package org.hbhk.module.framework.server.service;

import org.hbhk.module.framework.shared.domain.UserEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;

public interface IUserService {

	UserEntity getUserByName(String username);

	int insertUser(UserEntity user);

	UsersEntity queryUsers(String username);

}

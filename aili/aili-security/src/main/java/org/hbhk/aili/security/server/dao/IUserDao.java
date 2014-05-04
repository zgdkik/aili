package org.hbhk.aili.security.server.dao;

import org.hbhk.aili.security.share.pojo.UserInfo;

public interface IUserDao {

	UserInfo getMe(String username);

	UserInfo login(String username, String password);

}
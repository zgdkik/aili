package org.hbhk.aili.security.server.dao;

import org.hbhk.aili.mybatis.server.dao.IAiliDao;
import org.hbhk.aili.security.share.pojo.UserInfo;

public interface IUserDao  extends IAiliDao<UserInfo,String>{
	UserInfo getMe(String username);
	UserInfo login(String username, String password);

}
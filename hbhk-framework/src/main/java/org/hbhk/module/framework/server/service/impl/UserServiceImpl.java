package org.hbhk.module.framework.server.service.impl;

import javax.annotation.Resource;

import org.hbhk.module.framework.server.dao.IUserDao;
import org.hbhk.module.framework.server.service.IUserCache;
import org.hbhk.module.framework.server.service.IUserService;
import org.hbhk.module.framework.shared.domain.UserEntity;
import org.hbhk.module.framework.shared.domain.security.UsersEntity;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class UserServiceImpl extends SqlSessionDaoSupport implements IUserService  {

	@Resource
	private IUserCache userCache;
	@Resource
	private IUserDao  userDao;
	
	public UserEntity getUserByName(String username) {

		UserEntity u = new UserEntity();
		u.setPassword("135246");
		u.setUsername(username);
		return u;
	}

	public int insertUser(UserEntity user) {
	       int num= this.getSqlSession().insert("org.hbhk.insertUser", user);
		return num;
	}

	@Override
	public UsersEntity queryUsers(String username) {
		UsersEntity	usersCache = userCache.queryUserCache(username);
		if(usersCache==null){
			usersCache = userDao.queryUsers(username);
			userCache.saveUserCache(username, usersCache);
		}
		return usersCache;
	}

	public void setUserCache(IUserCache userCache) {
		this.userCache = userCache;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	

}

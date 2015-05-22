package org.hbhk.aili.mybatis.server.service.impl;

import org.hbhk.aili.mybatis.server.dao.IUserDao;
import org.hbhk.aili.mybatis.server.model.UserInfo;
import org.hbhk.aili.mybatis.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService  implements IUserService{
	
	@Autowired
	private IUserDao userDao;

	@Override
	public void insert(UserInfo t) {
		userDao.insert(t);
	}
	

}

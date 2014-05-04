package org.hbhk.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hbhk.dao.UserDAO;
import org.hbhk.domain.Person;
import org.hbhk.domain.User;
import org.hbhk.service.UserService;

@WebService(endpointInterface = "org.hbhk.service.UserService")
public class UserServiceImpl implements UserService {
	// 默认采用名称对应规则将Spring容器中dao注入
	private UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
//
//	public boolean findLogin(User user) {
//
//		User usr = userDao.findByEmail(user.getEmail());
//		if (usr != null) {
//			if (usr.getPassword().equals(user.getPassword())) {
//				return true;
//			}
//		}
//		return false;
//	}

	@WebMethod
	public boolean saveUser(Person p)   {

		
			System.out.println(p.getName());
		
		return false;

	}

}

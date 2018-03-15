package org.hbhk.aili.rest.server.service;

import org.hbhk.aili.base.share.util.UuidUtil;
import org.hbhk.aili.rest.server.dao.IUserDao;
import org.hbhk.aili.rest.share.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public String registerUser() {
		System.out.println("111111111111");
		User u = new User();
		
		u.setId(UuidUtil.getUuid());
		u.setName("hbhk");
		userDao.insert(u);
		return "qqqqqqqqqqqq";
	}

}

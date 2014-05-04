package org.hbhk.aili.security.server;

import org.hbhk.aili.security.server.dao.IRoleDao;
import org.hbhk.aili.security.server.dao.IUserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCache {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:cacheContext.xml");
		IUserDao userDao = (IUserDao) context.getBean("userDao");
		userDao.getMe("hbhk");
		IRoleDao roleDao = (IRoleDao) context.getBean("roleDao");
		roleDao.getRole("r001");
	}

}

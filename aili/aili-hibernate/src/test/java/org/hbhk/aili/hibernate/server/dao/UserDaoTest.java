package org.hbhk.aili.hibernate.server.dao;

import javax.annotation.Resource;

import org.hbhk.aili.hibernate.server.service.CateDao;
import org.hbhk.aili.hibernate.server.service.UsersDao1;
import org.hbhk.aili.hibernate.share.model.Category;
import org.hbhk.aili.hibernate.share.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring.xml" })
public class UserDaoTest {

	@Resource
	UsersDao1 userDao;
	@Resource
	CateDao cateDao;

	@Test
	public void t1() {
		User user = new User();
		user.setAge("12");
		user.setId("1");
		user.setUserName("hbhk");
		userDao.save(user);

	}

	@Test
	public void t2() {
		Category c = new Category();
		c.setId(2);
		c.setName("c1");
		c.setDescription("desc");
		cateDao.save(c);

	}

	@Test
	public void t3() {
		cateDao.findAll();

	}
}

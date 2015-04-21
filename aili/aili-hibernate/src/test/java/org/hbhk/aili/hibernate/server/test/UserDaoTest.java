package org.hbhk.aili.hibernate.server.test;

import org.hbhk.aili.hibernate.server.dao.IUserDao;
import org.hbhk.aili.hibernate.server.model.TestModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/spring.xml" })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class UserDaoTest {
	
	@Autowired
	private IUserDao userDao;
	
	
	@Test
	@Rollback(false)
	public void testDao() throws Exception {
		TestModel t = new TestModel();
		t.setId(1l);
		t.setName("hbhk");
		System.out.println(userDao.saveOrUpdate(t));
	}
	
	@Test
	public void getById() throws Exception {
		System.out.println(userDao.getById(1L).getName());
	}
}

package org.hbhk.aili.jpa.server;

import org.hbhk.aili.jpa.server.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml" })
@Transactional
public class AppTest {

	@Autowired
	IUserDao userDao;
	@Test
	public void test3() throws Exception {
	}

}
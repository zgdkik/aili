package org.hbhk.aili.mybatis.server.tx;

import org.hbhk.aili.mybatis.server.model.UserInfo;
import org.hbhk.aili.mybatis.server.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml" })
@Transactional
public class TxTest {

	@Autowired
	IUserService userService;
	
	@Test
	public void insertTest() throws Exception {
		UserInfo t = new UserInfo();
		userService.insert(t);
	}
	

}
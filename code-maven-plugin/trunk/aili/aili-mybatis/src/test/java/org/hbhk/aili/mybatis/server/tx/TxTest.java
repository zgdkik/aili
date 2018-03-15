package org.hbhk.aili.mybatis.server.tx;

import org.hbhk.aili.mybatis.server.SpringTestManagerBase;
import org.hbhk.aili.mybatis.server.entity.UserInfo;
import org.hbhk.aili.mybatis.server.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;



@TransactionConfiguration(defaultRollback = false)
public class TxTest extends SpringTestManagerBase {

	@Autowired
	IUserService userService;

	@Test
	public void insertTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setName("hbhk");
		userService.insert(t);
	}
	
	@Test
	public void insert1Test() throws Exception {
		UserInfo t = new UserInfo();
		t.setName("hbhk");
	}


}
package com.yimidida.ymdp.dao.test.server.tx;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yimidida.ymdp.dao.test.server.SpringTestManagerBase;
import com.yimidida.ymdp.dao.test.server.dao.IUserDao;
import com.yimidida.ymdp.dao.test.server.entity.UserInfo;



@TransactionConfiguration(defaultRollback= false)
public class TxTest extends SpringTestManagerBase {

	@Autowired
	private IUserDao userDao;

	@Test
	public void insertTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setName("hbhk");
		t.setVersion("1.0.0");
		userDao.insert(t); 
	}
	
	@Test
	public void updateTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setId(3l);
		t.setName("hbhk");
		t.setVersion("1.0.0");
		userDao.update(t);
	}
	@Test
	public void getTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setName("hbhk");
		//userDao.get(PropertiesHelper.beanToMap(t));
		List<UserInfo>  list =  userDao.getByObj(t);
		for (UserInfo userInfo : list) {
			System.out.println(userInfo.getId());
		}
	}

}
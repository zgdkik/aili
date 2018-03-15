package org.hbhk.aili.mybatis.server.tx;

import java.util.ArrayList;
import java.util.List;

import org.hbhk.aili.mybatis.server.SpringTestManagerBase;
import org.hbhk.aili.mybatis.server.dao.IUserDao;
import org.hbhk.aili.mybatis.server.entity.UserInfo;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;



@TransactionConfiguration(defaultRollback= false)
public class TxTest extends SpringTestManagerBase {

	@Autowired
	private IUserDao userDao;
	
	public static void main(String[] args) {
		AutoCreateTable.createTable(UserInfo.class);
	}

	@Test
	public void insertTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setName("hbhk");
		userDao.insert(t);
	}
	
	@Test
	public void insertBatchTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setName("hbhk");
		UserInfo t1 = new UserInfo();
		t1.setName("hbhk");
		List<UserInfo> list = new ArrayList<UserInfo>();
		list.add(t);
		list.add(t1);
		userDao.insertBatch(list);
	}
	
	@Test
	public void updateTest() throws Exception {
		UserInfo t = new UserInfo();
		t.setId("e4b18c51-e6a7-427b-a174-94497c7bdf2f");
		t.setName("hbhk1");
		t.setVersion(1l);
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
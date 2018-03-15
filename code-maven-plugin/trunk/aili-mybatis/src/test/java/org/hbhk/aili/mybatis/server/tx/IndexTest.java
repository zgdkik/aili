package org.hbhk.aili.mybatis.server.tx;

import org.hbhk.aili.mybatis.server.SpringTestManagerBase;
import org.hbhk.aili.mybatis.server.dao.IinventoryDao;
import org.hbhk.aili.mybatis.server.entity.Inventory;
import org.hbhk.aili.mybatis.server.entity.UserInfo;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

@TransactionConfiguration(defaultRollback = false)
public class IndexTest extends SpringTestManagerBase {

	@Autowired
	private IinventoryDao userDao;

	public static void main(String[] args) {
		AutoCreateTable.createTable(UserInfo.class);
	}

	@Test
	public void insertTest() throws Exception {
		Inventory t = new Inventory();
		t.setDeptCode("531A");
		t.setWaybillNo(200000012675l);
		userDao.getByIndex(t);
	}

}
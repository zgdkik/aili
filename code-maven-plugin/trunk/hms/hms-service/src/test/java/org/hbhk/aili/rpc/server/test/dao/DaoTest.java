package org.hbhk.aili.rpc.server.test.dao;

import org.hbhk.aili.rest.server.dao.IOrderDao;
import org.hbhk.aili.rest.share.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:dao/spring-dao.xml" })
public class DaoTest {

	
	@Autowired
	private  IOrderDao orderDao;
	
	
	@Test
	public void  testSave(){
		Order order = new Order();
		order.setOrderId(1l);
		order.setUserId(1l);
		order.setCreateTime("ddd");
		order.setOrderNo(System.currentTimeMillis() + "");
		orderDao.save(order);
	}
}

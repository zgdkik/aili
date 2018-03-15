package org.hbhk.aili.rest.server.service;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.rest.server.dao.IOrderDao;
import org.hbhk.aili.rest.share.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderDao orderDao;

	@Override
	public Boolean save() {
		Order order = new Order();
		order.setOrderId(1l);
		order.setUserId(1l);
		order.setCreateTime("ddd");
		order.setOrderNo(System.currentTimeMillis() + "");
		return orderDao.save(order) == 1 ? true : false;
	}

	@Override
	public List<Order> get(Map<String, Object> p) {
		return orderDao.get(p);
	}

}

package org.hbhk.aili.rest.server.dao;

import org.hbhk.aili.mybatis.server.dao.IBaseDao;
import org.hbhk.aili.rest.share.entity.Order;

public interface IOrderDao extends IBaseDao<Order, Long> {

	int save(Order oder);

}

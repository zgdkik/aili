package org.hbhk.aili.orm.server.test;

import java.util.List;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.share.model.UserInfo;

public interface TestAiliDao extends GenericEntityDao<String, Integer> {

	@NativeQuery(model = UserInfo.class, value = "hbhk")
	List<UserInfo> create(String name,Page p);

}

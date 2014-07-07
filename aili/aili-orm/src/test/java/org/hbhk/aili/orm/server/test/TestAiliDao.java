package org.hbhk.aili.orm.server.test;

import java.util.List;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.annotation.QueryParam;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;
import org.hbhk.aili.orm.server.surpport.Page;
import org.hbhk.aili.orm.share.model.UserInfo;

public interface TestAiliDao extends GenericEntityDao<UserInfo, Integer> {

	@NativeQuery(model = UserInfo.class, value = "testsql")
	List<UserInfo> create(@QueryParam("id") int id,Page p);

}

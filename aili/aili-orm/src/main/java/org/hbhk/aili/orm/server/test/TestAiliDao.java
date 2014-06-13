package org.hbhk.aili.orm.server.test;

import org.hbhk.aili.orm.server.annotation.NativeQuery;
import org.hbhk.aili.orm.server.dao.GenericEntityDao;

public interface TestAiliDao extends GenericEntityDao<String, Integer> {

	@NativeQuery(model = String.class)
	String create(String name);

}

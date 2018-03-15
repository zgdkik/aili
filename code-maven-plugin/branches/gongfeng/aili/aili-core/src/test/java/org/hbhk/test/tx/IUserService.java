package org.hbhk.test.tx;

import org.hbhk.aili.core.server.tx.aop.Tx;

public interface IUserService {
	@Tx
	void save(String sql) throws Exception;
}

package org.hbhk.test.tx.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.hbhk.aili.core.server.tx.context.ConnectionContext;
import org.hbhk.test.tx.IUserService;

public class UserService implements IUserService {

	private Connection conn;

	@Override
	public void save(String sql) throws Exception {
		conn = ConnectionContext.getConnLocal();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.execute();
		//throw new  RuntimeException("");
	}

}

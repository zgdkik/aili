package org.hbhk.core.server;

import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.hbhk.home.core.share.model.CustomerModel;

public class SqlTest {

	
	public static void main(String[] args) {
		String str = AutoCreateTable.createTable(CustomerModel.class);
		System.out.println(str);
	}
}

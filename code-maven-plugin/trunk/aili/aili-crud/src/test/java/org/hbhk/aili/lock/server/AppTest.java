package org.hbhk.aili.lock.server;

import org.hbhk.aili.crud.share.entity.ColumnEntity;
import org.hbhk.aili.crud.share.entity.Condition;
import org.hbhk.aili.crud.share.entity.CrudEntity;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;

public class AppTest {

	public static void main(String[] args) {
		String sql = AutoCreateTable.createTable(CrudEntity.class);
		System.err.println(sql);
		sql = AutoCreateTable.createTable(ColumnEntity.class);
		System.err.println(sql);
		sql = AutoCreateTable.createTable(Condition.class);
		System.err.println(sql);
	}
}

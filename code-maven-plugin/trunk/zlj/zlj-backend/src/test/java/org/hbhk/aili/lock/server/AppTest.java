package org.hbhk.aili.lock.server;

import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.hbhk.zlj.backend.share.entity.ColumnEntity;
import org.hbhk.zlj.backend.share.entity.Condition;
import org.hbhk.zlj.backend.share.entity.CrudEntity;

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

package org.hbhk.aili.workflow;

import org.hbhk.aili.base.share.util.TableColunsUtil;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.hbhk.aili.workflow.share.entity.Leave;

public class SqlTest {

	public static void main(String[] args) {
		String sql = AutoCreateTable.createTable(Leave.class);
		System.out.println(sql);
//		sql = AutoCreateTable.createTable(org.hbhk.aili.workflow.share.entity.Leave.class);
//		System.out.println(sql);
		TableColunsUtil.genTableColuns(Leave.class);
	}

}

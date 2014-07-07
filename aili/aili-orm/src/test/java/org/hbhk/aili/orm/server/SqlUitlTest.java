package org.hbhk.aili.orm.server;

import org.hbhk.aili.orm.server.handler.DefaultNameHandler;
import org.hbhk.aili.orm.share.model.Delete;
import org.hbhk.aili.orm.share.util.SqlUtil;

public final class SqlUitlTest {
	public static void main(String[] args) {
		Delete del = new Delete();
		del.setClazz("sdfdf");
		del.setId("1");
		del.setSql("sq");
		String ss = SqlUtil.buildInsertSql(del, new DefaultNameHandler()).getSql().toString();
		
		System.out.println(ss);
	}
}

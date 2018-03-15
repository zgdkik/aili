package test;

import org.hbhk.aili.common.share.entity.SystemParameterEntity;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;

public class AppTest {
	//http://www.tuicool.com/articles/ZV7vE3f
	public static void main(String[] args) {
		
		String sql = AutoCreateTable.createTable(SystemParameterEntity.class);
		
		System.out.println(sql);
	}

}

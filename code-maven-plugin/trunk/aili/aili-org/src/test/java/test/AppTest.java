package test;

import org.hbhk.aili.base.share.util.TableColunsUtil;
import org.hbhk.aili.gen.server.GenerateMain;
import org.hbhk.aili.mybatis.server.support.AutoCreateTable;
import org.hbhk.aili.org.share.entity.EmployeeEntity;
import org.hbhk.aili.org.share.entity.Region;

public class AppTest {
	//http://www.tuicool.com/articles/ZV7vE3f
	public static void main(String[] args) {
		
//		String sql = AutoCreateTable.createTable(EmployeeEntity.class);
//		
//		System.out.println(sql);
//		
//		TableColunsUtil.genTableColuns(EmployeeEntity.class);
		TableColunsUtil.genTableColuns(Region.class);
		GenerateMain.execute("aili","org",Region.class, "何波");
		
	}

}

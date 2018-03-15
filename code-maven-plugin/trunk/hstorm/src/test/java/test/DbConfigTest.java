package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import org.hbhk.hstorm.sync.DBCPUtils;
import org.hbhk.hstorm.sync.DbSyncUtil;

public class DbConfigTest {

	
	public static void main(String[] args) throws Exception {
//		String driver = "com.mysql.jdbc.Driver";
//		String url = "jdbc:mysql://192.168.50.84:3306/jstorm?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
//		String user = "root";
//		String password = "MySQL@123";
//		Class.forName(driver);
//		Connection conn = DriverManager.getConnection(url, user, password);
//		DbSyncUtil.intiDbInfo(conn);
//		List<Map<String, Object>> dataList =  DbSyncUtil.select(conn, "t1");
//		DbSyncUtil.insert(conn, null, "t2", dataList, 2);
		DBCPUtils.getConnection();
		
	}
}

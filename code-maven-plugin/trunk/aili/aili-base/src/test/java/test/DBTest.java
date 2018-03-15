package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.io.FileUtils;

public class DBTest {

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.9.1.2)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = omtkadg)))", "omtk", "LongJingCha_haoHe_56");
		System.out.println(conn);
		String sql = FileUtils.readFileToString(new File("E:/workspace/hbhk00/metertick/metertick-base/src/test/resources/sql.txt"));
		PreparedStatement pst = conn.prepareStatement(sql);
		System.out.println(System.currentTimeMillis()/1000);
		ResultSet rs = pst.executeQuery();
		System.out.println(System.currentTimeMillis()/1000);
	}
}

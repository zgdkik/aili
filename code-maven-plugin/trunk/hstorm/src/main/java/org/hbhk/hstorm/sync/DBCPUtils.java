package org.hbhk.hstorm.sync;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	private static DataSource ds;// 定义一个连接池对象
	public static void  init() {
		try {
			Properties pro = new Properties();
			pro.load(DBCPUtils.class.getClassLoader().getResourceAsStream(
					"dbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(pro);// 得到一个连接池对象
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 从池中获取一个连接
	public static synchronized Connection getConnection() throws SQLException {
		if(ds == null){
			init();
		}
		return ds.getConnection();
	}

	public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();// 关闭
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
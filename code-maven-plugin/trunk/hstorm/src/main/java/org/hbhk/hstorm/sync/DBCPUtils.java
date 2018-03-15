package org.hbhk.hstorm.sync;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtils {
	private static DataSource ds;// ����һ�����ӳض���
	public static void  init() {
		try {
			Properties pro = new Properties();
			pro.load(DBCPUtils.class.getClassLoader().getResourceAsStream(
					"dbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(pro);// �õ�һ�����ӳض���
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// �ӳ��л�ȡһ������
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
				conn.close();// �ر�
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
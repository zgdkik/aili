package org.hbhk.hstorm.sync;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class SrcDBCPUtils {
	private static DataSource ds;// ����һ�����ӳض���
	public static void  init() {
		try {
			Properties pro = new Properties();
			pro.load(SrcDBCPUtils.class.getClassLoader().getResourceAsStream(
					"srcdbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(pro);// �õ�һ�����ӳض���
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// �ӳ��л�ȡһ������
	public static Connection getConnection() throws SQLException {
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
				throw new RuntimeException(e);
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		if (conn != null) {
			try {
				conn.close();// �ر�
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
package org.hbhk.aili.support.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.Server;

public class HSQlUtil {

	private static Log Logger = LogFactory.getLog(HSQlUtil.class);

	public static final int PORT = 9002;
	public static final String DB_PATH = "./database/";
	// 数据库文件名，同时也是本类中的数据库名
	public static final String DB_NAME = "hbhk"; 
	public static final String USER_NAME = "sa";
	public static final String PASSWORD = "";

	/**
	 * 启动数据库服务
	 */
	public static boolean startHSQL() {
		// 它可是hsqldb.jar里面的类啊。
		Server server = new Server();
		server.setDatabaseName(0, DB_NAME);
		server.setDatabasePath(0, DB_PATH + DB_NAME);
		server.setPort(PORT);
		server.setSilent(true);
		// 自动多线程运行
		server.start();
		Logger.info("hsqldb started...");
		return true;
	}

	/**
	 * 关闭数据库服务
	 */
	public static boolean stopHSQL() {
		try {
			Statement statement = getConnection().createStatement();
			statement.executeUpdate("SHUTDOWN;");
			return true;
		} catch (SQLException ex) {
			Logger.error(ex.getMessage(), ex);
			return false;
		}
	}

	/**
	 * 获取连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			conn = DriverManager.getConnection(
					"jdbc:hsqldb:file:database/hbhk", USER_NAME, PASSWORD);
		} catch (ClassNotFoundException ex) {
			Logger.error(ex.getMessage(), ex);
		} catch (SQLException ex) {
			Logger.error(ex.getMessage(), ex);
		}
		return conn;
	}

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		HSQlUtil.startHSQL();
		try {
			Statement statement = getConnection().createStatement();
			System.out.println(statement);
			statement
					.executeUpdate("create table customer(id integer not null primary key,firstname varchar,lastname varchar)");
			for (int i = 10; i < 20; i++) {
				statement.executeUpdate("insert into customer values(" + i
						+ ",'liu','zhaoyang')");
			}

			statement
					.executeUpdate("create table customer1(id integer not null primary key,firstname varchar,lastname varchar)");
			for (int i = 10; i < 20; i++) {
				statement.executeUpdate("insert into customer1 values(" + i
						+ ",'liu','zhaoyang')");
			}
			statement.close();
		} catch (SQLException ex) {
			Logger.error(ex.getMessage(), ex);
		}

		HSQlUtil.stopHSQL();
	}
}

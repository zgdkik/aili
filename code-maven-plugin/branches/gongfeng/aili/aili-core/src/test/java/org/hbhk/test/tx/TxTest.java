package org.hbhk.test.tx;

import java.sql.Connection;
import java.sql.DriverManager;

import org.hbhk.aili.core.server.proxy.ProxyFactory;
import org.hbhk.aili.core.server.tx.context.ConnectionManager;
import org.hbhk.test.tx.impl.UserService;

public class TxTest {
	public static final String url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.10.0.4)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = omtkdb_sit) ))";
	public static final String name = "oracle.jdbc.driver.OracleDriver";
	public static final String user = "omtk_sit";
	public static final String password = "omtk_sit";

	public static Connection conn = null;

	public static void main(String[] args) throws Exception {
		// 加载数据库连接
		Class.forName(name);
		conn = DriverManager.getConnection(url, user, password);
		String sql = "INSERT INTO t_auth_user1 VALUES ('hbhk', '135246', '5')";
//		conn.setAutoCommit(false);
//		PreparedStatement pst = conn.prepareStatement(sql);
//		pst.execute();
		ConnectionManager.getConnectionManager().setConn(conn);
		ProxyFactory proxyFactory = new ProxyFactory();
		IUserService userService = proxyFactory.newInstance(UserService.class);
		
		userService.save(sql);
		
	}
}

package org.hbhk.test.tx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.UUID;

import org.hbhk.aili.core.server.proxy.ProxyFactory;
import org.hbhk.aili.core.server.tx.context.ConnectionManager;
import org.hbhk.test.tx.impl.UserService;

public class TxTestMysql {
	public static final String url = "jdbc:mysql://192.168.10.59:3306/ows?useUnicode=true&characterEncoding=UTF-8";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "135246";

	public static Connection conn = null;

	public static void main(String[] args) throws Exception {
		// 加载数据库连接
		Class.forName(name);
		conn = DriverManager.getConnection(url, user, password);
		String sql = "INSERT INTO t_auth_user(id,username,password) VALUES ('"+UUID.randomUUID().toString()+"','hbhk', '135246')";
		ConnectionManager.getConnectionManager().setConn(conn);
		ProxyFactory proxyFactory = new ProxyFactory();
		IUserService userService = proxyFactory.newInstance(UserService.class);
		
		userService.save(sql);
		
	}
}

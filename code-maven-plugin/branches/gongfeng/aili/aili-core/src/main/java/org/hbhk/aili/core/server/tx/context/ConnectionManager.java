package org.hbhk.aili.core.server.tx.context;

import java.sql.Connection;

public class ConnectionManager {

	private Connection conn;
	private static ConnectionManager connectionManager;

	public static ConnectionManager getConnectionManager() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	private ConnectionManager() {
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}

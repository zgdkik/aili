package org.hbhk.aili.core.server.tx.context;

import java.sql.Connection;

public class ConnectionContext {

	private static ThreadLocal<Connection> connLocal = new ThreadLocal<Connection>();

	public static Connection getConnLocal() {
		return connLocal.get();
	}

	public static void setConnLocal(Connection conn) {
		connLocal.set(conn);
	}

	public static void remove() {
		if (connLocal.get() != null) {
			connLocal.remove();
		}
	}
}

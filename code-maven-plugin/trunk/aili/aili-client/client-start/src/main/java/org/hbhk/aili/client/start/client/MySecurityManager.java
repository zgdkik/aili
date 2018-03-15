package org.hbhk.aili.client.start.client;

import java.security.Permission;

public class MySecurityManager extends SecurityManager {

	public MySecurityManager() {
		super();
	}

	public void checkPermission(Permission perm) {
		// super.checkPermission(perm);
	}

	public void checkPermission(Permission perm, Object context) {
	}
}

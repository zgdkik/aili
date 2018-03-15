package org.hbhk.test.proxy;

import java.io.Serializable;


public interface UserService extends Serializable {
	public void addUser(String id, String password);
	public String delUser(String id);
}

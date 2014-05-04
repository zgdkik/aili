package org.hbhk.dao;

import org.hbhk.domain.User;

public interface UserDAO {
	public User findByEmail(String email);
	public boolean saveUser(String user);
}

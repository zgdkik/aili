package org.hbhk.aili.cache.encache;

import java.util.List;

public interface IUserService {
	List<User> getAll();
	void removeAll() ;
}

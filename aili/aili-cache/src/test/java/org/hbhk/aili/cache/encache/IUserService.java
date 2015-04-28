package org.hbhk.aili.cache.encache;

import java.util.List;

public interface IUserService {
	List<User> getAll();
	void removeAll() ;
	User findById(Integer id);
	void removeById(Integer id);
}

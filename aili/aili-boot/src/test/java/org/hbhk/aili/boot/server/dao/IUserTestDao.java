package org.hbhk.aili.boot.server.dao;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.boot.server.model.UserInfo;


public interface IUserTestDao<T> {

	List<T> get(Map<String, Object> params);
	
	UserInfo insert(UserInfo user);
	
}

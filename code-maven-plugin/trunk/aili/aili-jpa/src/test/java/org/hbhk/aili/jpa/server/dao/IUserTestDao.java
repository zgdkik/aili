package org.hbhk.aili.jpa.server.dao;

import java.util.List;
import java.util.Map;

import org.hbhk.aili.jpa.server.model.UserInfo;


public interface IUserTestDao<T> {

	List<T> get(Map<String, Object> params);
	
	UserInfo insert(UserInfo user);
	
}

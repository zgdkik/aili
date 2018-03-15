package com.yimidida.ymdp.dao.test.server.dao;

import java.util.List;
import java.util.Map;

import com.yimidida.ymdp.dao.test.server.entity.UserInfo;


public interface IUserTestDao<T> {

	List<T> get(Map<String, Object> params);
	
	UserInfo insert(UserInfo user);
	
}

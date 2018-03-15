package org.hbhk.aili.mybatis.server.service;

import java.util.Map;

import org.hbhk.aili.mybatis.server.entity.UserInfo;


public interface IUserService {
	
	void insert(UserInfo t);
	
	java.util.List<UserInfo> query(Map<String, Object> params);
	
}

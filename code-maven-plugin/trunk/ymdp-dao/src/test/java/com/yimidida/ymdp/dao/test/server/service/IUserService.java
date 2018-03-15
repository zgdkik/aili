package com.yimidida.ymdp.dao.test.server.service;

import java.util.Map;

import com.yimidida.ymdp.dao.test.server.entity.UserInfo;


public interface IUserService {
	
	void insert(UserInfo t);
	
	java.util.List<UserInfo> query(Map<String, Object> params);
	
}

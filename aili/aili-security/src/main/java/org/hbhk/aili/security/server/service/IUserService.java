package org.hbhk.aili.security.server.service;

import org.hbhk.aili.security.share.model.UserInfo;

public interface IUserService extends IBizBaseService<Long,UserInfo> {
	
	UserInfo getMe(String username);
	UserInfo getUser(UserInfo user);
	boolean login(String username, String password);
	
	boolean validate(String url);
	boolean validate(String username,String url);
	void logout();
}
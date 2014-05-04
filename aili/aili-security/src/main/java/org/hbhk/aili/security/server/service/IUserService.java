package org.hbhk.aili.security.server.service;

import org.hbhk.aili.security.share.pojo.UserInfo;

public interface IUserService {
	
	UserInfo getMe(String username);

	boolean login(String username, String password);
	
	boolean validate(String url);
	boolean validate(String username,String url);
	void logout();
}
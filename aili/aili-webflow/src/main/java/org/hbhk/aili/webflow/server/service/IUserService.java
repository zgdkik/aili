package org.hbhk.aili.webflow.server.service;

import org.hbhk.aili.webflow.share.model.UserInfo;

public interface IUserService {

	
	UserInfo createUser(String code);
	
	UserInfo getUser(String code);
	
}

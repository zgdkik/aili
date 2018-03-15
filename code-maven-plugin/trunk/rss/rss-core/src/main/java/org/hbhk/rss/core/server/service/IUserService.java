package org.hbhk.rss.core.server.service;

import org.hbhk.rss.core.shared.pojo.UserMsgLogEntity;

public interface IUserService {

	void saveCurrMenu(String user, String menu);

	void removeCurrMenu(String user);

	String returnlastMenu(String user);

	String getCurrMenu(String user);
	
	void saveUserMsgLog(UserMsgLogEntity log);
}

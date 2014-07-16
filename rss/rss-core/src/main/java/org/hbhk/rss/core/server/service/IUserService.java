package org.hbhk.rss.core.server.service;

public interface IUserService {

	void saveCurrMenu(String user, String menu);

	String getCurrMenu(String user);
}

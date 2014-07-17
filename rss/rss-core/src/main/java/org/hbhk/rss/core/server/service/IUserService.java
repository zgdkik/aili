package org.hbhk.rss.core.server.service;

public interface IUserService {

	void saveCurrMenu(String user, String menu);

	void removeCurrMenu(String user);

	String returnlastMenu(String user);

	String getCurrMenu(String user);
}

package org.hbhk.rss.core.server.service.impl;

import org.hbhk.rss.core.server.cache.MemoryCacheTemplet;
import org.hbhk.rss.core.server.service.IUserService;
import org.springframework.stereotype.Service;
@Service
public class UserService implements IUserService {
	private static MemoryCacheTemplet<String> cacheTemplet = new MemoryCacheTemplet<String>();

	public void saveCurrMenu(String user, String menu) {
		cacheTemplet.set(user, menu, 1800);
	}

	public String getCurrMenu(String user) {
		return cacheTemplet.get(user);
	}

}

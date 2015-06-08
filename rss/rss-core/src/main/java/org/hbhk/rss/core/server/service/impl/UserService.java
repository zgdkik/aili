package org.hbhk.rss.core.server.service.impl;

import java.util.Date;

import org.hbhk.rss.core.server.cache.MemoryCacheTemplet;
import org.hbhk.rss.core.server.context.UserContext;
import org.hbhk.rss.core.server.dao.IUserDao;
import org.hbhk.rss.core.server.service.IUserService;
import org.hbhk.rss.core.shared.pojo.UserMsgLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	private static MemoryCacheTemplet<String> cacheTemplet = new MemoryCacheTemplet<String>();

	private  final String subPrex="->";
	@Autowired
	private IUserDao userDao;
	// menu 1->20->30;
	public void saveCurrMenu(String user, String menu) {
		String usermenu = getCurrMenu(user);
		if (usermenu != null) {
			if( usermenu.indexOf(subPrex)>-1){
				if(usermenu.substring(usermenu.lastIndexOf(subPrex)+subPrex.length(),usermenu.length()).equals(menu)){
					return;
				}
			}
			if(!menu.equals(usermenu)){
				usermenu = usermenu + subPrex + menu;
			}
		} else {
			usermenu = menu;
		}
		
		cacheTemplet.set(user, usermenu, 1800);
	}

	public String getCurrMenu(String user) {
		String usermenu = cacheTemplet.get(user);
		if(usermenu!=null && usermenu.indexOf(subPrex)>-1){
			usermenu = usermenu.substring(usermenu.lastIndexOf(subPrex)+subPrex.length(),usermenu.length());
		}
		return usermenu;
	}

	public void removeCurrMenu(String user) {
		cacheTemplet.invalid(user);
	}

	public String returnlastMenu(String user) {
		String menu = getCurrMenu(user);
		if (menu == null) {
			return menu;
		}
		if (menu.lastIndexOf("-") > -1) {
			menu = menu.substring(0, menu.lastIndexOf("-"));
		}
		cacheTemplet.set(user, menu, 1800);
		return menu;
	}

	public void saveUserMsgLog(UserMsgLogEntity log) {
		log.setCreate_time(new Date());
		log.setUser_name(UserContext.getCurrentContext().getCurrentUserName());
		userDao.insert(log);
	}
}

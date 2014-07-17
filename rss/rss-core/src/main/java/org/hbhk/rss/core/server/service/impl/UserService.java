package org.hbhk.rss.core.server.service.impl;

import org.hbhk.rss.core.server.cache.MemoryCacheTemplet;
import org.hbhk.rss.core.server.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	private static MemoryCacheTemplet<String> cacheTemplet = new MemoryCacheTemplet<String>();

	private  final String subPrex="->";
	// menu 1->20->30;
	public void saveCurrMenu(String user, String menu) {
		String usermenu = getCurrMenu(user);
		if (usermenu != null) {
			if(usermenu.substring(usermenu.lastIndexOf(subPrex)+subPrex.length(),usermenu.length()).equals(menu)){
				return;
			}
			usermenu = usermenu + subPrex + menu;
		} else {
			usermenu = menu;
		}
		
		cacheTemplet.set(user, usermenu, 1800);
	}

	public String getCurrMenu(String user) {
		String usermenu = getCurrMenu(user);
		usermenu = usermenu.substring(usermenu.lastIndexOf(subPrex)+subPrex.length(),usermenu.length());
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

	public static void main(String[] args) {
		String menu = "1->20->30";
		menu = menu.substring(menu.lastIndexOf("->")+"->".length(), menu.length());
		System.out.println(menu);
	}
}

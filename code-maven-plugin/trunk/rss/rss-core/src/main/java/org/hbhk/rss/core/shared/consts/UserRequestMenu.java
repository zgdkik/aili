package org.hbhk.rss.core.shared.consts;

import java.util.HashSet;
import java.util.Set;

public class UserRequestMenu {
	public static String weather = "1";
	public static String express = "2";
	public static Set<String> menus = new HashSet<String>();
	static {
		menus.add(weather);
		menus.add(express);
	}

}

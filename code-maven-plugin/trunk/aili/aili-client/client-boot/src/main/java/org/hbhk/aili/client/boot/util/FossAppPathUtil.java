package org.hbhk.aili.client.boot.util;

import java.io.File;

/**
 * 客户端路径获取对象帮助类
 */
public class FossAppPathUtil {

	/**
	 * 定义一个常量key_app_name
	 */
	public static final String key_app_name = "deppon.foss.app";
	/**
	 * 定义一个常量key_path_login
	 */
	public static final String key_path_login = "login";
	/**
	 * 定义一个常量key_file_remind_set
	 */
	public static final String key_file_remind_set = "remindset.properties";

	public static String getAppLocalPath() {
		/**
		 * 获取配置文件key为user.home的值，然后进行追加File.separator和key_app_name并将值赋给path
		 */
		String path = System.getProperty("user.home") + File.separator
		// + PropertiesUtil.getKeyValue("env")
				+ File.separator + key_app_name;

		return path; // 返回path
	}

	public static String getAppLocalPathForLogin() {
		/**
		 * 获取应用本地路径追加File.separator和key_path_login并返回追加后的值
		 */
		return getAppLocalPath() + File.separator + key_path_login;
	}

	public static String getRemindSetFileName() {
		/**
		 * 获取应用本地路径追加File.separator和key_file_remind_set并返回追加后的值
		 */
		return getAppLocalPath() + File.separator + key_file_remind_set;
	}
}
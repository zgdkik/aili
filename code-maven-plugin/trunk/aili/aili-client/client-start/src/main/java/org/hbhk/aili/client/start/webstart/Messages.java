package org.hbhk.aili.client.start.webstart;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 国际化信息源 (JDK1.3兼容)
 */
public class Messages {

	/**
	 * 国际化文件名
	 */
	private static final String BUNDLE_NAME = Messages.class.getPackage()
			.getName().replace('.', '/')
			+ "/i18n/messages";

	/**
	 * 工具, 私有化构造函数
	 */
	private Messages() {
	}

	/**
	 * 获取国际化信息
	 *
	 * @param key
	 *            国际化信息KEY
	 * @return 国际化信息
	 */
	public static String getString(String key) {
		try {
			return ResourceBundle.getBundle(BUNDLE_NAME).getString(key);
		} catch (MissingResourceException e) {
			return "NoSuch:" + key;
		}
	}

}
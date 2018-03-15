package org.hbhk.aili.client.start.client;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 国际化信息源 (JDK1.3兼容)
 */
public class ClientAppMessages {

	/**
	 * 国际化文件名
	 */
	private static final String BUNDLE_NAME = "org/hbhk/aili/client/start/i18n/messages";

	/**
	 * 工具, 私有化构造函数
	 */
	private ClientAppMessages() {
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
			return "?:" + key;
		}
	}

}
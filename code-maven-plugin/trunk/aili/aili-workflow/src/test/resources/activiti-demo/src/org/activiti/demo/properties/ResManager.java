package org.activiti.demo.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * 资源管理器
 * 
 * @Title: ResManager.java
 * @Description: org.app.ticket.msg
 * @Package org.app.ticket.msg
 * @author hncdyj123@163.com
 * @date 2012-9-29
 * @version V1.0
 * 
 */
public class ResManager {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ResManager.class);

	private static final String BUNDLE_NAME = "ftl.resources"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private static ResourceBundle EXIT_RESOURCE_BUNDLE = null;

	private final static String OA_HOME_NAME = "OA_HOME";
	private final static String OA_HOME = "config" + File.separator + "form" + File.separator + "dynamicform.properties";

	static {
		try {
			// 初始外部resources文件
			readSoures();
			// logger.debug("ResManager init key" + getString("class.package"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 读取资源文件
	 * 
	 * @throws IOException
	 */
	public static void readSoures() throws IOException {
		Map m = System.getenv();
		String formPath = (String) m.get(OA_HOME_NAME) + File.separator + OA_HOME;
		initProperties(formPath);
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static void initProperties(String filePath) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		EXIT_RESOURCE_BUNDLE = new PropertyResourceBundle(in);
	}

	public static String getByKey(String key) {
		try {
			return EXIT_RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "";
		}
	}

	public static void main(String[] arg0) throws Exception {
		// initProperties("E:\\config.properties");
		// String[] keys = ResManager.getByKey("traincode").split(",");
		// for (int i = 0; i < keys.length; i++) {
		// System.out.println("key" + i + " = " + keys[i]);
		// }
		// System.out.println(ResManager.getByKey("traincode"));
		// System.out.println(System.getProperty("user.dir"));
		System.out.println(getByKey("class.path"));
		System.out.println(getByKey("web.path"));
	}
}

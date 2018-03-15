package org.hbhk.aili.route.jms.server.common.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 读取文本文件，并转换为String对象.
 * 
 * @author HuangHua
 */
public class TextFile2StringUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TextFile2StringUtil.class);

	/**
	 * 读取一个文本文件，转换到String.
	 * 
	 * @param pathName
	 *            the path name
	 * @return the string
	 * @author HuangHua
	 * @date 2012-12-21 上午10:43:26
	 */
	public static String readFile2String(String pathName) {
		StringBuffer sb = new StringBuffer();
		String content = "";
		BufferedReader bufferedReader = null;
		InputStream inputStream = Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(pathName);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		try {
			bufferedReader = new BufferedReader(inputStreamReader);
			while (content != null) {
				content = bufferedReader.readLine();
				if (content == null) {
					break;
				}
				sb.append(content);
			}
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return sb.toString();
	}
}

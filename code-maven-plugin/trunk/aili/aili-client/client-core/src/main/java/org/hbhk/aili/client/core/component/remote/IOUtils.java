package org.hbhk.aili.client.core.component.remote;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 将InputStream写入OutputStream
 */
public class IOUtils {
	private static Log log = LogFactory.getLog(IOUtils.class);

	private IOUtils() {
	}

	/**
	 * <p>
	 * 将InputStream写入OutputStream，不关闭流
	 * </p>
	 */
	public static void copy(InputStream input, OutputStream output)
			throws IOException {
		copy(input, output, false);
	}

	/**
	 * <p>
	 * 将InputStream写入OutputStream
	 * </p>
	 */
	public static void copy(InputStream input, OutputStream output,
			boolean isClose) throws IOException {
		int len = 0;
		byte[] buffer = new byte[8192];
		try {
			while ((len = input.read(buffer, 0, 8192)) != -1) {
				output.write(buffer, 0, len);
			}
			output.flush();
		} finally {
			if (isClose) {
				// 使用公用代码
				close(input);
				close(output);
			}
		}
	}

	/**
	 * 关闭流
	 * @author 平台开发小组
	 */
	public static void close(Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException exp) {
			log.error(exp.getMessage());
		}
	}

}

package org.hbhk.aili.core.share.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

public class IOUtils {
	private static Log log = LogFactory.getLog(IOUtils.class);

	private IOUtils() {
	}

	public static void copy(InputStream input, OutputStream output)
			throws IOException {
		copy(input, output, false);
	}

	public static void writeFile(InputStream input, OutputStream output)
			throws IOException {
		copy(input, output, false);
	}

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

	public static void close(Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException exp) {
			log.error(exp.getMessage());
		}
	}

	public static boolean saveFile(InputStream is, String path, String name)
			throws IOException {
		Resource resource = FileLoadUtil.getResourceForServletpath(path);
		if (resource == null) {
			throw new IOException("path not find");
		}
		File f = resource.getFile();
		String uploadFile = f.getAbsolutePath();
		FileUtils.copyInputStreamToFile(is, new File(uploadFile, name));

		return false;

	}

	public static boolean saveFile(InputStream is, String path,
			String childPath, String name) throws IOException {
		String sep = System.getProperty("file.separator");
		Resource resource = FileLoadUtil.getResourceForServletpath(path);
		if (resource == null) {
			throw new IOException("path not find");
		}
		File file = resource.getFile();
		String uploadFile = file.getAbsolutePath() + sep + childPath;
		File f = new File(uploadFile);
		if (!f.exists()) {
			f.mkdir();
		}
		FileUtils.copyInputStreamToFile(is, new File(uploadFile, name));

		return false;

	}

}

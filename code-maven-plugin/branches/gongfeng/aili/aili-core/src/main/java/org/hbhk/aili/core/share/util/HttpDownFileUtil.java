package org.hbhk.aili.core.share.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;

public class HttpDownFileUtil {

	public static void download(String fileName, InputStream inputStream, int size,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] b = new byte[size];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			os.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void download(String fileName, File file,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}

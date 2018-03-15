package org.activiti.demo.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Title：UnZipFile.java
 * @Description：解压zip文件
 * @Package com.isoftstone.dynamicform.util
 * @author phYang
 * @date 2012-12-5
 * @version V1.0
 */
public class UnZipFile {

	/**
	 * 解压zip文件
	 * 
	 * @param zipFilePath
	 */
	public static void unzip(String zipFilePath) {
		File zipFile = new File(zipFilePath);
		String targetDirectoryPath = null;
		// 当目标目录为空的时候将文件解压到文件所在目录
		String targetDirectory = targetDirectoryPath;
		ZipInputStream zis = null;
		if (targetDirectoryPath == null) {
			targetDirectory = zipFile.getParent()
					+ System.getProperty("file.separator");
		}

		try {
			zis = new ZipInputStream(new FileInputStream(zipFilePath));
			ZipEntry entry;

			// 创建解压后的文件夹。
			while ((entry = zis.getNextEntry()) != null) {
				if (!entry.isDirectory()) {
					continue;
				}
				File directory = new File(targetDirectory, entry.getName());
				if (!directory.exists()) {
					if (!directory.mkdirs()) {
						System.exit(0);
					}
					zis.closeEntry();
				}
			}
			zis.close();

			// 解压文件。
			zis = new ZipInputStream(new FileInputStream(zipFilePath));
			while (((entry = zis.getNextEntry()) != null)) {
				if (entry.isDirectory()) {
					continue;
				}
				File unzippedFile = new File(targetDirectory, entry.getName());

				String filePath = unzippedFile.getPath();
				if (filePath.endsWith(".")) {
					String newPath = filePath.substring(0,
							filePath.length() - 1);
					File dir = new File(newPath);
					if (!dir.exists()) {
						dir.mkdir();
					}
					continue;
				}

				if (!unzippedFile.exists()) {
					unzippedFile.createNewFile();
				}
				FileOutputStream fout = new FileOutputStream(unzippedFile);
				DataOutputStream dout = new DataOutputStream(fout);

				byte[] b = new byte[1024];
				int len = 0;
				while ((len = zis.read(b)) != -1) {
					dout.write(b, 0, len);
				}
				dout.close();
				fout.close();
				zis.closeEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zis != null) {
					zis.close();
				}
				// 删除压缩包
				zipFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		unzip("E:\\test\\test.zip");
	}
}

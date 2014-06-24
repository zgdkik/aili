package org.hbhk.ioc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileScanUtil {

	private Log log = LogFactory.getLog(getClass());

	public List<String> scanBeansXml(String dirPath, String filename)
			throws IOException {
		Enumeration<URL> urls = null;
		if (StringUtils.isEmpty(dirPath)) {
			if (StringUtils.isNotEmpty(filename)) {
				urls = this.getClass().getClassLoader().getResources(filename);
			} else {
				urls = this.getClass().getClassLoader().getResources("orm.xml");
			}
		} else {
			urls = this.getClass().getClassLoader().getResources(dirPath);
		}
		List<String> beansXml = new ArrayList<String>();
		while (urls.hasMoreElements()) {
			// 获取下一个元素
			URL url = urls.nextElement();
			if (url == null) {
				throw new IOException(dirPath + "找不到");
			}

			String protocol = url.getProtocol();
			log.debug("protocol:" + protocol);
			if (protocol.equals("jar")) {
				JarURLConnection jarCon = (JarURLConnection) url
						.openConnection();
				JarFile jarFile = jarCon.getJarFile();
				Enumeration<JarEntry> jarEntrys = jarFile.entries();
				while (jarEntrys.hasMoreElements()) {
					JarEntry entry = jarEntrys.nextElement();
					// 简单的判断路径，如果想做到想Spring，Ant-Style格式的路径匹配需要用到正则。
					String name = entry.getName();
					if (!name.startsWith(dirPath) || entry.isDirectory()) {
						continue;
					}
					// if (!name.endsWith("orm.xml")) {
					// continue;
					// }
					if (StringUtils.isNotEmpty(filename)) {
						if (!name.endsWith(filename)) {
							continue;
						}
					}
					// 开始读取文件内容
					InputStream is = this.getClass().getClassLoader()
							.getResourceAsStream(name);
					String context = readJarContext(is);
					if (StringUtils.isNotEmpty(context)) {
						beansXml.add(context);
					}
					log.debug("jar:" + context);

				}
			} else if (protocol.equals("file")) {

				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				readFileContext(filePath, beansXml, filename);

			}
		}
		return beansXml;

	}

	private String readJarContext(InputStream is) throws IOException {
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuilder context = new StringBuilder();
		try {
			isr = new InputStreamReader(is, "utf8");
			br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				context.append(line);
			}
		} finally {
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return context.toString();

	}

	private List<String> readFileContext(String filePath, List<String> fileStr,
			final String filename) throws IOException {

		// 获取此包的目录 建立一个File
		File dir = new File(filePath);

		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			// log.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return null;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				boolean  ename = true;
				 if(StringUtils.isNotEmpty(filename)){
					 ename = file.getName().endsWith(filename);
				 }
				return (file.isDirectory())
						|| (ename);
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				readFileContext(file.getAbsolutePath(), fileStr, filename);
			} else {
				InputStream is = null;
				InputStreamReader isr = null;
				BufferedReader br = null;
				try {
					is = new FileInputStream(file);
					isr = new InputStreamReader(is, "utf8");
					br = new BufferedReader(isr);
					StringBuilder context = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						context.append(line + "\t\n");
					}
					fileStr.add(context.toString());
				} finally {
					if (br != null) {
						br.close();
					}
					if (isr != null) {
						isr.close();
					}
					if (is != null) {
						is.close();
					}
				}

			}
		}
		return fileStr;

	}

	public static void main(String[] args) throws Exception {
		FileScanUtil f = new FileScanUtil();
		// List<String> paths = f.getFilePath("org/hbhk/");
		// System.out.println(paths.size());
		String dirPath = "org/hbhk/aili";
		List<String> xmls = f.scanBeansXml(dirPath, "orm.xml");
		for (String string : xmls) {
			System.out.println(string);
		}

	}
}

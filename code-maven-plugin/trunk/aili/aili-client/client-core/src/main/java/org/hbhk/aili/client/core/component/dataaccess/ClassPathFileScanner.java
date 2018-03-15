package org.hbhk.aili.client.core.component.dataaccess;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java.plugin.util.IoUtil;

/**
 * 
 *	文件扫描类，用来查找一些资源文件
 */
public final class ClassPathFileScanner {

	private static final Log LOG = LogFactory.getLog(ClassPathFileScanner.class);
	
	private ClassPathFileScanner(){
		
	}
	
	/**
	 *扫描类路径下的所有的以endString结尾的文件，这些文件可以包含在jar包里面。
	 * 
	 * @param endString
	 * 	以endString结尾的文件
	 * @return List<URL> 
	 * 扫描指定文件的List集合
	 * @exception
	 */
	
	public static List<URL> scanFile(ClassLoader classLoader, String suffix){
		List<URL> mappers = new ArrayList<URL>();
		URLClassLoader urlClassLoader = null;
		if (classLoader instanceof URLClassLoader) {
			urlClassLoader = (URLClassLoader) classLoader;
		}else {
			return mappers;
		}
		URL[] urls = urlClassLoader.getURLs();
		for (URL url : urls) {
			String path=url.getPath();
			try {
				path = URLDecoder.decode(path,System.getProperty("file.encoding"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			File file = IoUtil.url2file(url);
			if(file != null && file.exists() && file.isDirectory()){
				mappers.addAll(files2urls(getFiles(file, suffix)));
			}else if (url.getPath().indexOf("!/")!=-1 || url.getPath().indexOf(".zip")!=-1) {
				File jarFile = new File(path.substring(path.indexOf("file:")+"file:".length(), path.lastIndexOf(".zip"))+".zip");
				List<String> jarMappers = scannerJarFile(jarFile, suffix);
				for (String string : jarMappers) {
					try{
						URL urlFile = new URL("jar:"+path.substring(0,path.lastIndexOf(".zip"))+".zip!/"+string);
						InputStream stream = urlFile.openStream();
						stream.close();
						mappers.add(urlFile);
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}
		}
		return mappers;
	}
	
	/**
	 * 方法完成的功能为，把File对象转换成URL对象，
	 * 借助了org.java.plugin.util.IoUtil类的方法
	 * files2urls
	 * @param files
	 * @return
	 * @return List<URL>
	 * @since:0.6
	 */
	public static List<URL> files2urls(List<File> files){
		List<URL> urls = new ArrayList<URL>();
		
		for (File file : files) {
			try {
				URL url = IoUtil.file2url(file);
				urls.add(url);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				LOG.info(String.format("文件转换成URL失败...%s",file),e);
			}
		}
		return urls;
	}
	
	/**
	 * 扫描Jar文件，查找指定的某些文件，这些文件是通过后缀来指定的
	 * scannerJarFile
	 * @param file
	 * 需要扫描的jar文件
	 * @param suffix
	 * 扫描jar包中以suffix结尾的文件
	 * @return List<String>
	 * 返回一个list对象，包含了找到的文件的jar包中的相对路径
	 * @since:0.6
	 */
	public static List<String> scannerJarFile(File file, String suffix) {
		List<String> names = new ArrayList<String>();
		JarFile jarFile;
		try {
			jarFile = new JarFile(file);
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if (name.toLowerCase().endsWith(suffix)) {
					names.add(name);
				}
			}
		} catch (IOException e) {
			LOG.error("file " + file.getName() + "can not be found");
		}
		return names;
	}

	/**
	 * 查找指定目录下以suffix结尾的文件，并且返回所有文件句柄
	 * getFiles
	 * @param dir
	 * 需要查找的目录
	 * @param suffix
	 * 文件后缀或者包括一部分的名字，比如mapper.xml，就表示查找dir目录下的一mapper.xml
	 * 结尾的文件
	 * @return List<File>
	 * 返回找到的所有文件的句柄，保存在list中。
	 * @since:0.6
	 */
	public static List<File> getFiles(File dir, final String suffix) {

		List<File> files = new ArrayList<File>();
		if (dir.isDirectory()) {
			File[] fs = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					if (pathname.isDirectory()
							|| pathname.getAbsolutePath().endsWith(suffix)) {
						return true;
					}
					return false;
				}
			});
			for (File file : fs) {
				if (file.isDirectory()) {
					files.addAll(getFiles(file, suffix));
				} else {
					
					files.add(file);
				}
			}
		} else {
			if (dir.getAbsolutePath().endsWith(suffix)) {
				files.add(dir);
			}
		}
		return files;
	}
}

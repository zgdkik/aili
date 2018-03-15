package org.hbhk.zk.share.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

/**
 * 提供文件操作的的工具类
 */
public final class FileLoadUtil {

	private static Log log = LogFactory.getLog(FileLoadUtil.class);

	

	public static Resource[] getResourcesForClasspathByPath(String path)
			throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		if (!path.startsWith("classpath*:")) {
			path = "classpath*:" + path;
		}
		Resource[] resources = resolver.getResources(path);
		if (log.isDebugEnabled()) {
			if (resources != null && resources.length > 0) {
				for (Resource resource : resources) {
					String name = resource.getFilename();
					log.debug("file info:" + path + ":" + name);
				}
			}
		}

		return resources;
	}

	public static File getInputStreamForClasspath(String fileName)
			throws FileNotFoundException, IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:" + fileName);
		if (resources == null || resources.length < 1) {
			throw new FileNotFoundException("file '" + fileName
					+ "' not found in this root path!");
		}
		return  resources[0].getFile();
		
	}

}

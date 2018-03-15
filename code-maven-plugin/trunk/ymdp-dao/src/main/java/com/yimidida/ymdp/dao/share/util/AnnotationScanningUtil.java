package com.yimidida.ymdp.dao.share.util;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * 
 * @Description: aili框架核心处理和基本支持
 * @author 何波
 * @date 2015年3月11日 上午10:05:24 
 *
 */
public class AnnotationScanningUtil {
	
	private static  Map<String, List<String>> urlsCache = new ConcurrentHashMap<String, List<String>>();
	/**
	 * 获取指定注解的所有类
	 * 
	 * @param annotation
	 * @param scannPackage
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>>  getAnnotatedClasses(Class<? extends Annotation> annotation,
			String... scannPackage) throws ClassNotFoundException {
		// 是否使用默认过滤器true使用
		ClassPathScanningCandidateComponentProvider packageScan = new ClassPathScanningCandidateComponentProvider(
				false);
		packageScan.addIncludeFilter(new AnnotationTypeFilter(annotation));
		if (scannPackage == null) {
			return null;
		}
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (String pack : scannPackage) {
			Set<BeanDefinition> bds = packageScan.findCandidateComponents(pack);
			for (BeanDefinition beanDefinition : bds) {
				annotatedClasses.add(Class.forName(beanDefinition
						.getBeanClassName()));
			}
		}
		return annotatedClasses;
	}
	
	

}


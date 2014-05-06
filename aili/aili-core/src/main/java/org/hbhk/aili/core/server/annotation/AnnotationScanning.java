package org.hbhk.aili.core.server.annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;



public class AnnotationScanning {

	private ClassPathScanningCandidateComponentProvider packageScan = new ClassPathScanningCandidateComponentProvider(false);
	
	private List<Class<?>> annotatedClasses ;
	
	public AnnotationScanning(Annotation annotation ,String... basePackage) throws ClassNotFoundException {
		this.packageScan.addIncludeFilter(new AnnotationTypeFilter(annotation.getClass()));
		if (basePackage == null) {
			return;
		}
		annotatedClasses = new ArrayList<Class<?>>();
		for (String pack : basePackage) {
			Set<BeanDefinition> bds = this.packageScan.findCandidateComponents(pack);
			for (BeanDefinition beanDefinition : bds) {
				annotatedClasses.add(Class.forName(beanDefinition.getBeanClassName()));
			}
		}
	}

	public List<Class<?>> getAnnotatedClasses() {
		return annotatedClasses;
	}

	
}

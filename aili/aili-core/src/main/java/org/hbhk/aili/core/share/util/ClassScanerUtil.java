package org.hbhk.aili.core.share.util;

import java.io.IOException;   
import java.lang.annotation.Annotation;   
import java.util.HashSet;   
import java.util.LinkedList;   
import java.util.List;   
import java.util.Set;   
  


import org.springframework.beans.factory.BeanDefinitionStoreException;   
import org.springframework.context.ResourceLoaderAware;   
import org.springframework.core.io.Resource;   
import org.springframework.core.io.ResourceLoader;   
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;   
import org.springframework.core.io.support.ResourcePatternResolver;   
import org.springframework.core.io.support.ResourcePatternUtils;   
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;   
import org.springframework.core.type.classreading.MetadataReader;   
import org.springframework.core.type.classreading.MetadataReaderFactory;   
import org.springframework.core.type.filter.AnnotationTypeFilter;   
import org.springframework.core.type.filter.TypeFilter;   
import org.springframework.util.SystemPropertyUtils;   
  
public class ClassScanerUtil implements ResourceLoaderAware {   
  
    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();   
  
    private final static List<TypeFilter> includeFilters = new LinkedList<TypeFilter>();   
  
    private final static List<TypeFilter> excludeFilters = new LinkedList<TypeFilter>();   
  
    private static MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(   
            resourcePatternResolver);   
  
    public ClassScanerUtil() {   
  
    }   
  
    public void setResourceLoader(ResourceLoader resourceLoader) {   
        resourcePatternResolver = ResourcePatternUtils   
                .getResourcePatternResolver(resourceLoader);   
        metadataReaderFactory = new CachingMetadataReaderFactory(   
                resourceLoader);   
    }   
  
    public final ResourceLoader getResourceLoader() {   
        return resourcePatternResolver;   
    }   
  
    public void addIncludeFilter(TypeFilter includeFilter) {   
        includeFilters.add(includeFilter);   
    }   
  
    public void addExcludeFilter(TypeFilter excludeFilter) {   
        excludeFilters.add(0, excludeFilter);   
    }   
  
    public void resetFilters(boolean useDefaultFilters) {   
        includeFilters.clear();   
        excludeFilters.clear();   
    }   
  
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Set<Class> scan(String basePackage,   
            Class<? extends Annotation>... annotations) {   
        ClassScanerUtil cs = new ClassScanerUtil();   
        for (Class anno : annotations)   
            cs.addIncludeFilter(new AnnotationTypeFilter(anno));   
        return doScan(basePackage);   
    }   
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Set<Class> scan(String[] basePackages,   
            Class<? extends Annotation>... annotations) {   
        ClassScanerUtil cs = new ClassScanerUtil();   
        for (Class anno : annotations)   
            cs.addIncludeFilter(new AnnotationTypeFilter(anno));   
        Set<Class> classes = new HashSet<Class>();   
        for (String s : basePackages)   
            classes.addAll(doScan(s));   
        return classes;   
    }   
    @SuppressWarnings({ "rawtypes" })
    public static Set<Class> doScan(String basePackage) {   
        Set<Class> classes = new HashSet<Class>();   
        try {   
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX   
                    + org.springframework.util.ClassUtils   
                            .convertClassNameToResourcePath(SystemPropertyUtils   
                                    .resolvePlaceholders(basePackage))   
                    + "/**/*.class";   
            Resource[] resources = resourcePatternResolver   
                    .getResources(packageSearchPath);   
  
            for (int i = 0; i < resources.length; i++) {   
                Resource resource = resources[i];   
                if (resource.isReadable()) {   
                    MetadataReader metadataReader = metadataReaderFactory   
                            .getMetadataReader(resource);   
                    if ((includeFilters.size() == 0 && excludeFilters.size() == 0)   
                            || matches(metadataReader)) {   
                        try {   
                            classes.add(Class.forName(metadataReader   
                                    .getClassMetadata().getClassName()));   
                        } catch (ClassNotFoundException e) {   
                            throw new RuntimeException(e);   
                        }   
  
                    }   
                }   
            }   
        } catch (IOException ex) {   
            throw new BeanDefinitionStoreException(   
                    "I/O failure during classpath scanning", ex);   
        }   
        return classes;   
    }   
  
    protected static boolean matches(MetadataReader metadataReader) throws IOException {   
        for (TypeFilter tf : excludeFilters) {   
            if (tf.match(metadataReader, metadataReaderFactory)) {   
                return false;   
            }   
        }   
        for (TypeFilter tf : includeFilters) {   
            if (tf.match(metadataReader, metadataReaderFactory)) {   
                return true;   
            }   
        }   
        return false;   
    }   
  
}  
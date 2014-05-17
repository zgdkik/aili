package org.hbhk.code.template;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class FreeMarkerConfig {

	public static final FreeMarkerConfig freeMarkerConfig = new FreeMarkerConfig();
	
	private Configuration configuration;
	
	private FreeMarkerConfig(){ }
	
	public static FreeMarkerConfig getInstance(){
		return freeMarkerConfig;
	}

	public Configuration getConfiguration(File templatePath) throws MojoExecutionException {
		return createConfiguration(templatePath);
	}

	public Configuration createConfiguration(String templatePath) {
		Configuration configuration = new Configuration();
		configuration.setTemplateLoader(new SpringTemplateLoader(
						new PathMatchingResourcePatternResolver(), templatePath));
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		configuration.setDefaultEncoding("UTF-8");
		return configuration;
	}
	
	private Configuration createConfiguration(File templatePath) throws MojoExecutionException{
		configuration = new Configuration();
		try {
			configuration.setTemplateLoader(new FileTemplateLoader(templatePath));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setDefaultEncoding("UTF-8");
//			Properties p = new Properties();
//			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("freemarker.properties"));		
		} catch (IOException e) {
			throw new MojoExecutionException("ERROR:获取freemarker Configuration错误。",e);
		}
		return configuration;
	}
	
}

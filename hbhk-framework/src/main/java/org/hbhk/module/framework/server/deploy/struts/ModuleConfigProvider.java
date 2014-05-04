package org.hbhk.module.framework.server.deploy.struts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.StrutsXmlConfigurationProvider;
import org.hbhk.module.framework.server.deploy.FrameworkFilter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.ConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.location.LocatableProperties;
public class ModuleConfigProvider implements ConfigurationProvider {
	private static final Log LOGGER = LogFactory
			.getLog(ModuleConfigProvider.class);

	private PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	private List<ConfigurationProvider> providers = new ArrayList<ConfigurationProvider>();

	private ObjectFactory objectFactory;

	public ModuleConfigProvider() {
		try {
			ServletContext servletContext = FrameworkFilter.getServletContext();
			//查找指定位置的struts配置文件
			Resource[] resources = resolver
					.getResources("classpath*:org/hbhk/**/server/META-INF/struts.xml");
			for (Resource resource : resources) {
				String path = resource.getURL().getPath();
				String classpath = path.substring(path
						.lastIndexOf("org/hbhk/"));
				if (LOGGER.isInfoEnabled()) {
					LOGGER.info("[Framework] add action config: " + classpath);
				}
				//创建StrutsXmlConfigurationProvider并加入providers
				providers.add(new StrutsXmlConfigurationProvider(classpath,
						true, servletContext));
			}
		} catch (IOException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	/**
	 * 初始化provider
	 */
	public void init(Configuration config) throws ConfigurationException {
		for (ConfigurationProvider provider : providers) {
			provider.init(config);
		}
	}

	/**
	 * 销毁所有provider
	 */
	public void destroy() {
		for (ConfigurationProvider provider : providers) {
			provider.destroy();
		}
	}

	/**
	 * 加载package
	 */
	public void loadPackages() throws ConfigurationException {
		for (ConfigurationProvider provider : providers) {
			StrutsXmlConfigurationProvider p = (StrutsXmlConfigurationProvider)provider;
			//注入objectFactory
			p.setObjectFactory(objectFactory);
			p.loadPackages();
		}
	}

	public boolean needsReload() {
		for (ConfigurationProvider provider : providers) {
			if (provider.needsReload()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 绑定ContainerBuilder
	 */
	public void register(ContainerBuilder builder,
			LocatableProperties properties) throws ConfigurationException {
		for (ConfigurationProvider provider : providers) {
			provider.register(builder, properties);
		}
	}

	/**
	 * 注入struts ObjectFactory
	 */
	@Inject
	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}



}

package org.hbhk.aili.core.server.context;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hbhk.aili.core.share.entity.DynamicBean;
import org.hbhk.aili.core.share.entity.Property;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

public class DynamicLoadBean implements ApplicationContextAware {

	private ConfigurableApplicationContext applicationContext = null;

	@Autowired(required = false)
	private IDynamicBeanReader dynamicBeanReader;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}

	public void LoadDynBean() {

		DefaultListableBeanFactory acf = (DefaultListableBeanFactory) applicationContext
				.getAutowireCapableBeanFactory();

		List<DynamicBean> dynamicBeans = dynamicBeanReader.loadBean();
		if(dynamicBeans!=null && dynamicBeans.size()>0){
			for (DynamicBean dynamicBean : dynamicBeans) {
				String id = dynamicBean.getId();
				Class<?> cls = dynamicBean.getCls();
				BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(cls.getName());
				bdb.getBeanDefinition().setAttribute("id", id);
				List<Property> properties = dynamicBean.getProperties();
				if(properties!=null && properties.size()>0){
					for (Property property : properties) {
						String name = property.getName();
						String value = property.getValue();
						String ref = property.getRef();
						if(StringUtils.isNotEmpty(name)){
							if(StringUtils.isNotEmpty(value)){
								 bdb.addPropertyValue(name,value);  
							}
							if(StringUtils.isNotEmpty(ref)){
								bdb.addPropertyReference(name, ref);
							}
						}
					}
				}
				acf.registerBeanDefinition(id, bdb.getBeanDefinition());
			}
			
		}
		

		
	}

	public IDynamicBeanReader getDynamicBeanReader() {
		return dynamicBeanReader;
	}

	public void setDynamicBeanReader(IDynamicBeanReader dynamicBeanReader) {
		this.dynamicBeanReader = dynamicBeanReader;
	}

}

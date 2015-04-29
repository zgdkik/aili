package org.hbhk.aili.core.server.context;

import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class AiliPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	private Logger log = LoggerFactory.getLogger(AiliPropertyPlaceholderConfigurer.class);
	
	private IPropertiesService propertiesService;

	private void addProperties(Properties props, Properties newProps) {
		Set<Object> keys = newProps.keySet();
		for (Object key : keys) {
			Object val = newProps.get(key);
			log.debug("设置自定义 properties key : "+key +" value : "+val);
			props.put(key, val);
		}
	}
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		if(propertiesService != null){
			Properties newProps = propertiesService.getProperties();
			if(newProps != null){
				addProperties(props, newProps);
			}
		}
		super.processProperties(beanFactoryToProcess, props);
	}


}

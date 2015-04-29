package org.hbhk.aili.core.server.context;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
/**
 * 
* @Description:提供程序获取spring properties 和外部添加properties功能
* @author 何波
* @date 2015年4月29日 下午3:30:52 
*
 */
public class AiliPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	private Logger log = LoggerFactory.getLogger(AiliPropertyPlaceholderConfigurer.class);
	
	private IPropertiesService propertiesService;
	/**
	 * 提供程序获取spring读取到的properties内容
	 * 不能外部修改,修改对系统属性也不生效
	 */
	public static Properties properties = new Properties();
	/**
	 * 覆盖属性路径
	 */
	private List<String> overrideLocaltions;

	public List<String> getOverrideLocaltions() {
		return overrideLocaltions;
	}

	public void setOverrideLocaltions(List<String> overrideLocaltions) {
		this.overrideLocaltions = overrideLocaltions;
	}

	private void addProperties(Properties props, Properties newProps) {
		Set<Object> keys = newProps.keySet();
		for (Object key : keys) {
			Object val = newProps.get(key);
			log.debug("设置自定义 properties key : "+key +" value : "+val);
			props.put(key, val);
		}
	}
	
	private void addOverrideLocations(Properties props, Properties newProps) {
		if(overrideLocaltions != null){
			for (String location : overrideLocaltions) {
				PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
				try {
					Resource resource = pmrpr.getResource(location);
					Properties prop = PropertiesLoaderUtils.loadProperties(resource);
					addProperties(props, prop);
				} catch (Exception e) {
					log.error(e.getMessage(),e);
					throw new RuntimeException(e);
				}
			}
		}
	}
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		if(propertiesService != null && props != null){
			Properties newProps = propertiesService.getProperties();
			if(newProps != null){
				addProperties(props, newProps);
				//添加覆盖属性
				addOverrideLocations(props, newProps);
			}
		}
		if(props!=null){
			properties.putAll(props);
		}
		
		super.processProperties(beanFactoryToProcess, props);
	}


}

package org.hbhk.aili.route.jms.common.spring.props;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * TODO JNDI资源加载
 * @author davidcun
 * @date 2013-4-12 下午04:44:10
 */
public class JndiPropertiesConfigurer extends PropertyPlaceholderConfigurer {
	
	private Properties jndiProperties;
	
	private static final String prefix = "jndi-";

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		
		if (jndiProperties!=null && placeholder!=null && placeholder.startsWith(prefix) && placeholder.length()>=prefix.length() ) {
			return (String) jndiProperties.get(placeholder.substring(placeholder.indexOf(prefix)+prefix.length()));
		}
		return super.resolvePlaceholder(placeholder, props);
	}
	
	public Properties getJndiProperties() {
		return jndiProperties;
	}
	
	public void setJndiProperties(Properties jndiProperties) {
		this.jndiProperties = jndiProperties;
	}
	
}

package com.deppon.esb.propertyplaceholder;

import java.util.Properties;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.DefaultPropertiesResolver;
import org.apache.log4j.Logger;


/**
 * The Class ESBPropertiesResolver.
 */
public class ESBPropertiesResolver extends DefaultPropertiesResolver {
	
	/** The log. */
	private Logger log = Logger.getLogger(ESBPropertiesResolver.class);

    /**
	 * Resolve properties.
	 * 
	 * @param context
	 *            the context
	 * @param uri
	 *            the uri
	 * @return the properties
	 * @throws Exception
	 *             the exception
	 */
    public Properties resolveProperties(CamelContext context, String... uri) throws Exception {
        Properties answer = new Properties();

/*        for (String path : uri) {
            if (path.startsWith("ref:")) {
                Properties prop = loadPropertiesFromRegistry(context, path);
                prop = prepareLoadedProperties(prop);
                answer.putAll(prop);
            } else if (path.startsWith("file:")) {
                Properties prop = loadPropertiesFromFilePath(context, path);
                prop = prepareLoadedProperties(prop);
                answer.putAll(prop);
            } else {
                // default to classpath
                Properties prop = loadPropertiesFromClasspath(context, path);
                prop = prepareLoadedProperties(prop);
                answer.putAll(prop);
            }
        }*/
     //   Properties prop = loadPropertiesFromServiceConfigLoader();
        Properties prop = new Properties();
        prop.put("myKey", "myValues");
        prop = prepareLoadedProperties(prop);
        answer.putAll(prop);
        return answer;
    }
	
/*	*//**
	 * 从服务配置信息中获取后端地址
	 *//*
    protected Properties loadPropertiesFromServiceConfigLoader() throws Exception {
    	Properties answer = new Properties();
        Map<String, ESBRuntimeConfiguration> esb_config =ServiceConfigLoader.ESB_CONFIG;
        if(esb_config == null){
        	log.error("ServiceConfigLoader.ESB_CONFIG is null");
        }else{
        	 for(Map.Entry<String, ESBRuntimeConfiguration> entry : esb_config.entrySet())    
        	 {    
        	     String key = entry.getKey();
        	     ESBRuntimeConfiguration config = entry.getValue();
        	     answer.put(key+".addr", config.getTargetServiceAddr());
        	     log.info("add camel property.key["+key+"] value["+config.getTargetServiceAddr()+"]"); 
        	 }   
        }
        return answer;
    }*/
}

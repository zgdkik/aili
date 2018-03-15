package com.deppon.esb.springplaceholder;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


/**
 * The Class ESBPropertyPlaceHolder.
 */
public class ESBPropertyPlaceHolder extends PropertyPlaceholderConfigurer {
	
	/** The log. */
	private Logger log = Logger.getLogger(ESBPropertyPlaceHolder.class);
	
	/** The Constant PREFIX. */
	public static final String PREFIX = "#{";
	
	/**
	 * Instantiates a new eSB property place holder.
	 */
	public 	ESBPropertyPlaceHolder(){
		super();
		setPlaceholderPrefix(PREFIX);
	}	
	
	/**
	 * 加载properties信息
	 * 
	 * @param props
	 *            the props
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @author HuangHua
	 * @date 2012-12-25 下午5:30:57
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#loadProperties(java.util.Properties)
	 */
	@Override
	protected void loadProperties(Properties props) throws IOException {
		//super.loadProperties(props);
		if(props == null){
			log.info("===========props is null, create props");
			props = new Properties();
		}
		props.setProperty("myName", "JIM");
		//props.put("myName", "JIM");
	}
}

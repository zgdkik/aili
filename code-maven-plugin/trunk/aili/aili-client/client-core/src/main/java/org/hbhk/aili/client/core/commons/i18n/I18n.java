package org.hbhk.aili.client.core.commons.i18n;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 提供获取国际化资源功能
 */
@SuppressWarnings({"unused"})
public class I18n implements II18n,I18nConstants {
	
	private static final Log    LOG = LogFactory.getLog(I18n.class);
	
	private Map<String,Map<String,String>> resources;
	private Map<String, Map<String,String>> defaultResources;
	private Locale              locale;
	private String              sourceLocation;
	private ClassLoader         classLoader;
	

	/**
	 * 
	 * @param locale
	 * @param baseName
	 *           国际化资源文件路径及基本名字 example：C:/resource/i18n_zh_CN.resource 的bseName为
	 *           C:/resource/i18n
	 */
	public I18n(Locale locale, ClassLoader classLoader) {
		
		if(classLoader==null){
			this.classLoader = Thread.currentThread().getContextClassLoader();
		}else {
			this.classLoader = classLoader;
		}
		if(locale==null){
			this.locale = Locale.getDefault();
		}else {
			this.locale = locale;
		}
		
		init();

	}

	/**
	 * 
	 * @Title:init
	 * @Description:初始化国际化资源文件
	 * @param 
	 * @return void
	 * @throws
	 */
	private void init() {
		resources = new HashMap<String, Map<String,String>>();
		defaultResources = new HashMap<String, Map<String,String>>();
		loadDefaultI18n();
		loadCurrentI18n();
	}
	/**
	 * 
	 * @Title:loadCurrentI18n
	 * @Description:加载当前语言的国际化资源文件，即message_zh_CN.properties文件
	 * @param 
	 * @return void
	 * @throws
	 */
	private void loadCurrentI18n(){
		String basePath =  DEFAULT_PATH +FILE_SEPARATOR+ getMsgName();
		try {
			Enumeration<URL> enumberation = classLoader.getResources(basePath);
			while (enumberation.hasMoreElements()) {
				URL url = enumberation.nextElement();
				Map<String, String> resource = new HashMap<String, String>();
				resource.putAll(I18nUtil.loadResource(url.openStream()));
				resources.put(url.getFile(), resource);
			}
		} catch (IOException e) {
			LOG.error(String.format("read file %s error",basePath));
		}
	}
	/**
	 * 
	 * @Title:loadDefaultI18n
	 * @Description:加载默认的国际化资源文件，即message.properties文件
	 * @param 
	 * @return void
	 * @throws
	 */
	private void loadDefaultI18n(){
		String basePath = DEFAULT_PATH +FILE_SEPARATOR+ BASE_NAME+ ".properties";
		try {
			Enumeration<URL> enumberation = classLoader.getResources(basePath);
			while (enumberation.hasMoreElements()) {
				URL url = (URL) enumberation.nextElement();
				Map<String, String> resource = new HashMap<String, String>();
				resource.putAll(I18nUtil.loadResource(url.openStream()));
				defaultResources.put(url.getFile(), resource);
			}
		} catch (IOException e) {
			LOG.error(String.format("read file %s error",BASE_NAME),e);
		}
	}

	/**
	 * 
	 * <p>Title: getMsgName</p>
	 * <p>Description: 获取信息名称</p>
	 * @return
	 */
	private String getMsgName() {
		String name = BASE_NAME;
		if (locale.getLanguage() != null && !"".equals(locale.getLanguage())) {
			name += "_" + locale.getLanguage();
		}
		if (locale.getCountry() != null && !"".equals(locale.getCountry())) {
			name += "_" + locale.getCountry();
		}
		name += ".properties";
		return name;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.i18n.II18n#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		String des = null;
		des = getSource(key);
		if (des == null || "".equals(des)) {
			des = getDefaultSource(key);
		}
		return des;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.i18n.II18n#get(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public String get(String key, Object... args) {
		String des = get(key);
		if (des != null && args != null) {
			return MessageFormat.format(des, args);
		}
		return des;
	}

	/**
	 * 
	 * <p>Title: getSource</p>
	 * <p>Description: 获取来源</p>
	 * @param key
	 * @return
	 */
	private String getSource(String key) {
		String src = null;
		if (resources != null) {
			try {
				for (Map<String, String> resource : resources.values()) {
					src = resource.get(key);
					if (src!=null) {
						break;
					}
				}
			} catch (Exception e) {
				LOG.error("get source error",e);
				src = null;
			}
		}
		return src;
	}

	/**
	 * 
	 * <p>Title: getDefaultSource</p>
	 * <p>Description: 获取默认来源</p>
	 * @param key
	 * @return
	 */
	private String getDefaultSource(String key) {
		String src = null;
		if (defaultResources != null) {
			try {
				for (Map<String, String> resource : defaultResources.values()) {
					src = resource.get(key);
					if (src!=null) {
						break;
					}
				}
			} catch (Exception e) {
				LOG.error("get source error",e);
				src = null;
				// ignore
			}
		}
		return src;
	}

	@Override
	public void reload(Locale locale) {
		this.locale = locale;
		init();
	}
}
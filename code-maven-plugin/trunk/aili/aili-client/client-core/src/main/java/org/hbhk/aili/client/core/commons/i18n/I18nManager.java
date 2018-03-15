package org.hbhk.aili.client.core.commons.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 资源文件统一放在org/hbhk/aili/client/META-INF/i18n/目录下或者放在clazz文件所在包下
 * 资源文件基本名字为i18n。
 * 此类加载的国际化会是整个classLoader下的所有国际化资源。
 */
public final class I18nManager implements I18nConstants {
	private static final Log LOG = LogFactory.getLog(I18nManager.class);
	private static Map<ClassLoader, Map<Locale, II18n>> classLoaderMap; 
	
	private static I18nManager instance=new I18nManager();
	private Locale locale;
	private I18nManager(){
		this.locale = Locale.getDefault();
		classLoaderMap    = new HashMap<ClassLoader, Map<Locale, II18n>>();
	}
	
	/**
	 * 获取国际化信息统一的语言环境
	 * getDefaultLocale
	 * @return
	 * @return Locale
	 * @since:0.6
	 */
	public static Locale getDefaultLocale() {
		return instance.locale;
	}
	
	/**
	 * 设置国际化信息默认的
	 * setDefaultLocale
	 * @param locale
	 * @return void
	 * @since:0.6
	 */
	public static void setDefaultLocale(Locale locale) {
		synchronized (locale) {
			instance.locale = locale;
		}
	}
	
	/**
	 * 通过指定的class对象，获取加载此class的classLoader所能加载到的所有国际化资源。
	 * 这里包括了父ClassLoader下的国际化资源。
	 * getI18n
	 * @param clazz
	 * @return
	 * @return II18n
	 * @since:0.6
	 */
	public static II18n getI18n(Class<?> clazz) {
		return getI18n(clazz, getDefaultLocale());
	}
	
	/**
	 * 重新载入国际化资源。
	 * reload
	 * @param locale
	 * @return void
	 * @since:0.6
	 */
	public static synchronized void reload(Locale locale) {
		if (getDefaultLocale().equals(locale)) {
			return;
		}
		setDefaultLocale(locale);
		for (Map<Locale,II18n> localeI18n : classLoaderMap.values()) {
			for (II18n i18n : localeI18n.values()) {
				i18n.reload(locale);
			}
		}
	}
	
	/**
	 * 
	 * <p>Title: getI18n</p>
	 * <p>Description: 获得国际化接口实例</p>
	 * @param clazz 类
	 * @param locale 本地对象
	 * @return
	 */
	public static II18n getI18n(Class<?> clazz, Locale locale) {
		//II18n empty = new I18n(null,null);
		ClassLoader classLoader = null;
		try {
			classLoader = clazz.getClassLoader();
		} catch(SecurityException e) {
			//ignore
			return new I18n(null,null);
		}
		if(classLoader == null) {
			LOG.info(String.format("can not get classLoader from class %s",clazz));
			return new I18n(null,null);
		}
		return getI18n(classLoader, locale);
	}
	
	/**
	 * 
	 * <p>Title: getI18n</p>
	 * <p>Description: 获得国际化接口实例</p>
	 * @param loader 类加载器
	 * @return
	 */
	public static II18n getI18n(ClassLoader loader) {
		return getI18n(loader, getDefaultLocale());
	}
	
	/**
	 * 
	 * <p>Title: getI18n</p>
	 * <p>Description: 获得国际化接口实例</p>
	 * @param loader 类加载器
	 * @param locale 本地对象
	 * @return
	 */
	public static II18n getI18n(ClassLoader loader, Locale locale) {
		Map<Locale, II18n> localeI18n = classLoaderMap.get(loader);
		II18n i18n = null;
		if (localeI18n!=null) {
			i18n = localeI18n.get(locale);
			if (i18n != null) {
				return i18n;
			}
		} else {
			localeI18n = new HashMap<Locale, II18n>();
			classLoaderMap.put(loader, localeI18n);
		}
		i18n = new I18n(locale, loader);
		localeI18n.put(locale, i18n);
		
		return i18n;
	}
}
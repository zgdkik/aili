package org.hbhk.aili.client.core.commons.i18n;

import java.util.Locale;

/**
 *	国际化资源接口
 */
public interface II18n {
	/**
	 * 用给定的语言环境信息重新加载国际化信息
	 * reload
	 * @param locale 本地对象
	 * @return void
	 * @since:0.6
	 */
	void reload(Locale locale);
	
	/**
	 * 获取当前国际化语言信息
	 * getLocale
	 * @return
	 * @return Locale
	 * @since:0.6
	 */
	Locale getLocale();
	
	/**
	 * 通过指定的key值获取指定的国际化信息
	 * get
	 * @param key 键
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String get(String key);
	
	/**
	 * 获取指定key值的国际化信息，并且可以传入参数
	 * 这里国际化信息编写方式应该是，比如key=value1{0}value2{1}。
	 * 其中的{0}{1}位置的信息将会被传入的参数代替
	 * get
	 * @param key 键
	 * @param args 参数列表
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String get(String key, Object ... args);
}
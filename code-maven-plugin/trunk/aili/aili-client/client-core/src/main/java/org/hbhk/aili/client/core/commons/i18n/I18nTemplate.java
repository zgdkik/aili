package org.hbhk.aili.client.core.commons.i18n;

/**
 * 
 *国际化信息模板类。此类主要是为了获取类#{key}类型的国际化信息。
 */
public final class I18nTemplate {
	
	
	private I18nTemplate(){
		
	}
	/**
	 * 此方法是为了整合国际化信息。比如传入的参数是:prefix#{key}postfix。
	 * 那么最终获取的国际化信息将会是prefix+i18n.get(key)+postfix类型的字符串。
	 * merge
	 * @param i18n
	 * @param string
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public static String merge(II18n i18n, String string) {
		if (string == null || "".equals(string)) {
			return null;
		}
		String result = string;

		int start = result.indexOf("#{");
		if (start == -1) {
			return result;
		}
		int end = result.indexOf('}', start + 2);

		if (end == -1) {
			return result;
		}
		String prefix = result.substring(0, start);
		String postfix = result.substring(end + 1, result.length());
		String key = result.substring(start + 2, end);
		return prefix + i18n.get(key) + postfix;
	}

	/**
	 * 此方法是为了整合国际化信息。比如传入的参数是:prefix#{key}postfix。
	 * 那么最终获取的国际化信息将会是prefix+i18n.get(key)+postfix类型的字符串。
	 * 这里国际化信息编写方式应该是，比如key=value1{0}value2{1}。
	 * 其中的{0}{1}位置的信息将会被传入的参数代替
	 * merge
	 * @param i18n
	 * @param string
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public static String merge(II18n i18n, String string, Object... args) {
		if (string == null || "".equals(string)) {
			return null;
		}
		String result = string;

		int start = result.indexOf("#{");
		if (start == -1) {
			return result;
		}
		int end = result.indexOf('}', start + 2);

		if (end == -1) {
			return result;
		}
		String prefix = result.substring(0, start);
		String postfix = result.substring(end + 1, result.length());
		String key = result.substring(start + 2, end);
		return prefix + i18n.get(key, args) + postfix;
	}
}
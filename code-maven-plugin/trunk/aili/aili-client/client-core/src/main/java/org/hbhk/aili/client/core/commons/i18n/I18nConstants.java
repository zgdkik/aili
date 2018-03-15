package org.hbhk.aili.client.core.commons.i18n;

public interface I18nConstants {
	/**
	 * 文件路径分隔符
	 */
	public static final String FILE_SEPARATOR       = "/";
	
	/**
	 * 包路径
	 */
	public static final String COMMON_PACKAGE_PATH  = "org" + FILE_SEPARATOR +"hbhk"+FILE_SEPARATOR+"aili"+FILE_SEPARATOR+"client";
	
	/**
	 * 国际化信息文件路径
	 */
	public static final String DEFAULT_PATH         = COMMON_PACKAGE_PATH+FILE_SEPARATOR+"META-INF"+FILE_SEPARATOR+"message";
	
	/**
	 * 国际化信息文件基本名称
	 */
	public static final String BASE_NAME            = "message";
}

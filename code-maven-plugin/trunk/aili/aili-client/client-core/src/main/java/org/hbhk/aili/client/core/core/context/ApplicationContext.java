package org.hbhk.aili.client.core.core.context;

import java.io.File;

import org.hbhk.aili.client.core.core.application.IApplication;
/**
 * 
 *	应用对象像上文，包含了应用主目录及应用对象本身
 */
public final class ApplicationContext {
	
	private final static ApplicationContext context = new ApplicationContext();
	private String appHome;
	private IApplication application;
	
	private ApplicationContext() {
		
	}
	
	public static final String getDBHome(){
		return getApplicationHome() + File.separator + "database" + File.separator + "hsqldb";
	}
	
	/**
	 * 获取应用主目录字符串表示形式
	 * getApplicationHome
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public static final String getApplicationHome() {
		return context.appHome;
	}
	
	/**
	 * 设置应用主目录
	 * setApplicationHome
	 * @param appHome
	 * @return void
	 * @since:0.6
	 */
	public static final void setApplicationHome(String appHome) {
		context.appHome = appHome;
	}
	
	/**
	 * 获取应用插件目录
	 * getPluginsHome
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public static final String getPluginsHome() {
		return getApplicationHome() + File.separator + "plugins";
	}
	
	/**
	 * 应用配置文件路径 
	 * @param      
	 * @return      
	 * @exception
	 */
	public static final String getAppConfigHome(){
		return getApplicationHome()+ File.separator + "conf";
	}
	
	/**
	 * 
	 * getApplication
	 * @return
	 * @return IApplication
	 * @since:0.6
	 */
	public static final IApplication getApplication() {
		return context.application;
	}
	
	/**
	 * 
	 * @param application
	 */
	public static final void setApplication(IApplication application) {
		context.application = application;
	}
}

package org.hbhk.aili.rpc.server.hessian.context;

import java.io.File;

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
	 * 鑾峰彇搴旂敤涓荤洰褰曞瓧绗︿覆琛ㄧず褰㈠紡
	 * getApplicationHome
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public static final String getApplicationHome() {
		return context.appHome;
	}
	
	/**
	 * 璁剧疆搴旂敤涓荤洰褰�
	 * setApplicationHome
	 * @param appHome
	 * @return void
	 * @since:0.6
	 */
	public static final void setApplicationHome(String appHome) {
		context.appHome = appHome;
	}
	
	/**
	 * 鑾峰彇搴旂敤鎻掍欢鐩綍
	 * getPluginsHome
	 * @return
	 * @return String
	 * @since:0.6
	 */
	public static final String getPluginsHome() {
		return getApplicationHome() + File.separator + "plugins";
	}
	
	/**
	 * 搴旂敤閰嶇疆鏂囦欢璺緞 
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

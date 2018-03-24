package com.yimidida.ows.base.server.context;

public final class AppContext {

	/**
	 * 应用名
	 */
	private final String appName;

	/**
	 * 静态资源地址
	 */
	private final String staticAddress;

	/**
	 * 上下文路径
	 */
	private final String contextPath;

	/**
	 * 单例示例
	 */
	private static AppContext context;
	
	private final String env;

	public String getAppName() {
		return appName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public static AppContext getAppContext() {
		return context;
	}

	public AppContext(String appName, String staticAddress, String contextPath,String env) {
		this.appName = appName;
		this.staticAddress = staticAddress;
		this.contextPath = contextPath;
		this.env = env;
	}

	public static synchronized void initAppContext(String appName,
			String staticAddress, String contextPath,String env) {
		if (context == null) {
			context = new AppContext(appName, staticAddress, contextPath,env);
		}
	}

	public String getStaticAddress() {
		return staticAddress;
	}

	public String getEnv() {
		return env;
	}

}

package org.hbhk.aili.base.server.context;


public abstract class AppContext {
	/**
	 * 应用名
	 */
	private final String appName;
	
	/**
	 * 静态资源地址
	 */
	private final String staticServerAddress;
	
	/**
	 * 上下文路径
	 * 
	 * @author ningyu
	 */
	private final String contextPath;
	
	/**
	 * 单例示例
	 */
	private static AppContext context;
	
//	/**
//	 * 参数校验,放置xss
//	 */
//	private static ParametersValidator parametersValidator;
//
//	public static ParametersValidator getParametersValidator() {
//		return parametersValidator;
//	}
//
//	public static void setParametersValidator(
//			ParametersValidator parametersValidator) {
//		AppContext.parametersValidator = parametersValidator;
//	}

	public String getAppName() {
		return appName;
	}
	
	public String getStaticServerAddress() {
		return staticServerAddress;
	}
	
	public String getContextPath() {
        return contextPath;
    }
	
	public static AppContext getAppContext() {
        return context;
    }

    public AppContext(String appName, String staticServerAddress, String contextPath) {
		this.appName = appName;
		this.staticServerAddress = staticServerAddress;
		this.contextPath = contextPath;
	}
	
	public static synchronized void initAppContext(String appName, String staticServerAddress, String contextPath) {
		if (context == null) {
			context = new AppContext(appName, staticServerAddress, contextPath) {};
		}
	}
}

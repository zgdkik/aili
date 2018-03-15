package org.hbhk.aili.client.core.core.application;

/**
 * 
 *应用对象注入类，在对应的插件扩展点上实现了此类，框架就会注入IApplication对象到实现类里面
 */
public interface IApplicationAware {
	/**
	 * 
	 * <p>Title: setApplication</p>
	 * <p>Description: 向action中注册一个IApplication对象</p>
	 * @param application 应用程序
	 */
	void setApplication(IApplication application);
}

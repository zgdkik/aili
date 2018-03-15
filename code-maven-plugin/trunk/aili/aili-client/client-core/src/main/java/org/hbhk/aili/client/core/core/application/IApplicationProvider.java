package org.hbhk.aili.client.core.core.application;

/**
 * 
 *	定了应用对象的提供者，实现了此类需要能够返回一个可应用的应用对象
 */
public interface IApplicationProvider {
	/**
	 * 
	 * <p>Title: getApplication</p>
	 * <p>Description: 获取应用程序接口对象</p>
	 * @return
	 */
	IApplication getApplication();
}

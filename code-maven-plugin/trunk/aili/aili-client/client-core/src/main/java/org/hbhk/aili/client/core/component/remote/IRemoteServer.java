package org.hbhk.aili.client.core.component.remote;

import org.hbhk.aili.client.core.component.networkstatus.INetworkStatusListener;
import org.hbhk.aili.client.core.component.transport.ITransport;

/**
 * 远程应用调用统一接口
 */
public interface IRemoteServer {
	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 获取远程服务对象</p>
	 * @param <T>
	 * @param serviceCls 远程服务接口
	 * @param executor 执行者
	 * @return
	 */
	<T extends IService> T getService(Class<T> serviceCls, T executor);

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 获取远程服务对象</p>
	 * @param <T>
	 * @param serviceCls 远程服务接口
	 * @param aliasName 别名
	 * @param executor 执行者
	 * @return
	 */
	<T extends IService> T getService(Class<T> serviceCls, String aliasName,
			T executor);

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 获取远程服务对象</p>
	 * @param <T>
	 * @param serviceCls 远程服务接口
	 * @return
	 */
	<T extends IService> T getService(Class<T> serviceCls);

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 获取远程服务对象</p>
	 * @param <T>
	 * @param serviceCls 远程服务接口
	 * @param aliasName 别名
	 * @return
	 */
	<T extends IService> T getService(Class<T> serviceCls, String aliasName);

	/**
	 * 
	 * <p>Title: addRemoteServerStatusListener</p>
	 * <p>Description: 添加远程服务状态监听器</p>
	 * @param listener 网络状态监听器
	 */
	void addRemoteServerStatusListener(INetworkStatusListener listener);

	/**
	 * 
	 * <p>Title: removeRemoteServerStatusListener</p>
	 * <p>Description: 移除指定远程服务状态监听器</p>
	 * @param listener 网络状态监听器
	 */
	void removeRemoteServerStatusListener(INetworkStatusListener listener);

	/**
	 * 
	 * <p>Title: shutdown</p>
	 * <p>Description: 关闭</p>
	 */
	void shutdown();
	
	/**
	 * 
	 * <p>Title: getTransport</p>
	 * <p>Description: 获得传输对象</p>
	 * @return 传输对象
	 */
	ITransport getTransport();
}
package org.hbhk.aili.client.core.component.remote;

/**
* <b style="font-family:微软雅黑"><small>Description:远程服务器工厂接口</small></b>   </br>
 */
public interface IRemoteServerFactory {
	/**
	 * 
	 * <p>Title: init</p>
	 * <p>Description: 初始化</p>
	 */
	void init();

	/**
	 * 
	 * <p>Title: getExceptionHandler</p>
	 * <p>Description: 获取异常处理器</p>
	 * @return
	 */
	IRemoteExceptionHandler getExceptionHandler();

	/**
	 * 
	 * <p>Title: getRemoteServer</p>
	 * <p>Description: 获取远程服务</p>
	 * @return
	 */
	IRemoteServer getRemoteServer();

	/**
	 * 
	 * <p>Title: getRemoteServer</p>
	 * <p>Description: 根据服务名获取远程服务</p>
	 * @param remoteName 服务名
	 * @return
	 */
	IRemoteServer getRemoteServer(String remoteName);

	/**
	 * 
	 * <p>Title: shutdown</p>
	 * <p>Description: 关闭</p>
	 */
	void shutdown();
}
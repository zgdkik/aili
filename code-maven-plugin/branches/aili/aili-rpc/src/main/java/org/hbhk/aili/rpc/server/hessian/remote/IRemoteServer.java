package org.hbhk.aili.rpc.server.hessian.remote;

import org.hbhk.aili.rpc.server.hessian.IHessianRemoting;
import org.hbhk.aili.rpc.server.hessian.transport.INetworkStatusListener;
import org.hbhk.aili.rpc.server.hessian.transport.ITransport;

/**
 * 杩滅▼搴旂敤璋冪敤缁熶竴鎺ュ彛
 * 
 * @author Steven Cheng
 * 
 */
public interface IRemoteServer {
	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param serviceCls 杩滅▼鏈嶅姟鎺ュ彛
	 * @param executor 鎵ц鑰�
	 * @return
	 */
	<T extends IHessianRemoting> T getService(Class<T> serviceCls, T executor);

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param serviceCls 杩滅▼鏈嶅姟鎺ュ彛
	 * @param aliasName 鍒悕
	 * @param executor 鎵ц鑰�
	 * @return
	 */
	<T extends IHessianRemoting> T getService(Class<T> serviceCls, String aliasName,
			T executor);

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param serviceCls 杩滅▼鏈嶅姟鎺ュ彛
	 * @return
	 */
	<T extends IHessianRemoting> T getService(Class<T> serviceCls);

	/**
	 * 
	 * <p>Title: getService</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟瀵硅薄</p>
	 * @param <T>
	 * @param serviceCls 杩滅▼鏈嶅姟鎺ュ彛
	 * @param aliasName 鍒悕
	 * @return
	 */
	<T extends IHessianRemoting> T getService(Class<T> serviceCls, String aliasName);

	/**
	 * 
	 * <p>Title: addRemoteServerStatusListener</p>
	 * <p>Description: 娣诲姞杩滅▼鏈嶅姟鐘舵�鐩戝惉鍣�/p>
	 * @param listener 缃戠粶鐘舵�鐩戝惉鍣�
	 */
	void addRemoteServerStatusListener(INetworkStatusListener listener);

	/**
	 * 
	 * <p>Title: removeRemoteServerStatusListener</p>
	 * <p>Description: 绉婚櫎鎸囧畾杩滅▼鏈嶅姟鐘舵�鐩戝惉鍣�/p>
	 * @param listener 缃戠粶鐘舵�鐩戝惉鍣�
	 */
	void removeRemoteServerStatusListener(INetworkStatusListener listener);

	/**
	 * 
	 * <p>Title: shutdown</p>
	 * <p>Description: 鍏抽棴</p>
	 */
	void shutdown();
	
	/**
	 * 
	 * <p>Title: getTransport</p>
	 * <p>Description: 鑾峰緱浼犺緭瀵硅薄</p>
	 * @return 浼犺緭瀵硅薄
	 */
	ITransport getTransport();
}
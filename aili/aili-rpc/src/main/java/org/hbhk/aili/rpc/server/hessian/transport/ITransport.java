package org.hbhk.aili.rpc.server.hessian.transport;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.hbhk.aili.rpc.server.hessian.client.NetworkMonitor;


public interface ITransport {

	/**
	 * 浼犺緭缁勪欢鍒濆鍖栵紝蹇呴』璋冪敤涓�
	 */
	void init();

	/**
	 * 绯荤粺閫�嚭涔嬪墠闇�璋冪敤浼犺緭缁勪欢鐨勫叧闂姛鑳�
	 */
	void destroy();
	/**
	 * 
	 * @Title:getTransportor
	 * @Description:鑾峰緱浼犺緭鍣�
	 * @param @return
	 * @return HttpClient
	 * @throws
	 */
	HttpClient getTransportor();
	/**
	 * 
	 * @Title:getTransportProtocol
	 * @Description:鑾峰緱浼犺緭鍗忚
	 * @param @return
	 * @return Map<String,String>
	 * @throws
	 */
	Map<String, String> getTransportProtocol();

	/**
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	NetworkMonitor getNetworkMonitor();
}

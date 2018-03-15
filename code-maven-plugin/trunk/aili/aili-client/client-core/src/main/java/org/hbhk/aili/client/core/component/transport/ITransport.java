package org.hbhk.aili.client.core.component.transport;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.hbhk.aili.client.core.component.networkstatus.NetworkMonitor;
/**
* <b style="font-family:微软雅黑"><small>Description:数据传输接口</small></b>   </br>
 */
public interface ITransport {

	/**
	 * 传输组件初始化，必须调用一次
	 */
	void init();

	/**
	 * 系统退出之前需要调用传输组件的关闭功能
	 */
	void destroy();
	/**
	 * 
	 * @Title:getTransportor
	 * @Description:获得传输器
	 * @param @return
	 * @return HttpClient
	 * @throws
	 */
	HttpClient getTransportor();
	/**
	 * 
	 * @Title:getTransportProtocol
	 * @Description:获得传输协议
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

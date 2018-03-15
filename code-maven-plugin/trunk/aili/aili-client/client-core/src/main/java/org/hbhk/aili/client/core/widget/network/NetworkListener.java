package org.hbhk.aili.client.core.widget.network;

import java.util.EventListener;

/**
 * 连接事件监听器
 */
public interface NetworkListener extends EventListener {

	/**
	 * 当连接状态变更时触发
	 * @param event 事件信息
	 */
	void onNetworkChanged(NetworkEvent event);

}
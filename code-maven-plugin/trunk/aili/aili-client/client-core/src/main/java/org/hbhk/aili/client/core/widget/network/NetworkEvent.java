package org.hbhk.aili.client.core.widget.network;

import java.net.InetSocketAddress;
import java.util.EventObject;

/**
 * 连接事件信息
 */
public class NetworkEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final InetSocketAddress address;

	private final boolean connected;

	/**
	 * 构造连接事件信息
	 * @param source 事件源(事件发起者)
	 * @param connected 连接状态
	 */
	public NetworkEvent(Object source, InetSocketAddress address, boolean connected) {
		super(source);
		this.address = address;
		this.connected = connected;
	}
	/**
	 * 
	 * @Title:getAddress
	 * @param @return
	 * @return InetSocketAddress
	 * @throws
	 */
	public InetSocketAddress getAddress() {
		return address;
	}
	/**
	 * 
	 * @Title:isConnected
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean isConnected() {
		return connected;
	}

}

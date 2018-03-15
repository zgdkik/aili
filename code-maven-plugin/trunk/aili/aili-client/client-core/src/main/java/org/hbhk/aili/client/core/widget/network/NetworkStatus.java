package org.hbhk.aili.client.core.widget.network;

import java.net.InetSocketAddress;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.hbhk.aili.client.core.commons.util.ImageFactory;
/**
* <b style="font-family:微软雅黑"><small>Description:网络状态：连通和不连通</small></b>   </br>
 */
public class NetworkStatus extends JLabel implements NetworkListener {

	private static final long serialVersionUID = 1L;

	private static final Icon CONNECTED_ICON = ImageFactory.getIcon("network/connected.gif");

	private static final Icon DISCONNECTED_ICON = ImageFactory.getIcon("network/disconnected.gif");
	
	private InetSocketAddress address;
	
	private boolean connected;
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:</p>
	 *
	 * @param host
	 * @param port
	 */
	public NetworkStatus(String host, int port) {
		this(InetSocketAddress.createUnresolved(host, port));
	}
	/**
	 * 
	 * <p>Title:</p>
	 * <p>Description:</p>
	 *
	 * @param address
	 */
	public NetworkStatus(InetSocketAddress address) {
		this.address = address;
		NetworkMonitor.getNetworkMonitor(address).addNetworkListener(this);
	}
	/**
	 * 
	 * <p>Title:onNetworkChanged
	 * <p>Description:当网络状态改变时，设置网络连接状态</p>
	 * @param event
	 * @see org.hbhk.aili.client.core.widget.network.NetworkListener#onNetworkChanged(org.hbhk.aili.client.core.widget.network.NetworkEvent)
	 */
	@Override
	public void onNetworkChanged(NetworkEvent event) {
		setConnected(event.isConnected());
	}
	/**
	 * 
	 * @Title:setConnected
	 * @Description:设置网络连接状态
	 * @param @param connected
	 * @return void
	 * @throws
	 */
	private void setConnected(boolean connected) {
		this.connected = connected;
		if (connected) {
			NetworkStatus.this.setIcon(CONNECTED_ICON);
			NetworkStatus.this.setToolTipText("已连接服务器：" + address);
		} else {
			NetworkStatus.this.setIcon(DISCONNECTED_ICON);
			NetworkStatus.this.setToolTipText("无法连接服务器：" + address);
		}
	}
	/**
	 * 
	 * @Title:isConnected
	 * @Description:判断网络是否连通
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean isConnected() {
		return connected;
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

}

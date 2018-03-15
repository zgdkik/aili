package org.hbhk.aili.client.core.widget.network;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.util.NamedThreadFactory;
/**
* <b style="font-family:微软雅黑"><small>Description:网络连接状况监视器</small></b>   </br>
 */
public final class NetworkMonitor {
	
	private static final Log log = LogFactory.getLog(NetworkMonitor.class);

    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3, new NamedThreadFactory("Network Check Thread", true));

	private static final ConcurrentMap<InetSocketAddress, NetworkMonitor> monitors = new ConcurrentHashMap<InetSocketAddress, NetworkMonitor>();
	
	
	/**
	 * 
	 * @Title:getNetworkMonitor
	 * @param @param host
	 * @param @param port
	 * @param @return
	 * @return NetworkMonitor
	 * @throws
	 */
	public static NetworkMonitor getNetworkMonitor(String host, int port) {
		return getNetworkMonitor(InetSocketAddress.createUnresolved(host, port));
	}
	/**
	 * 
	 * @Title:getNetworkMonitor
	 * @param @param address
	 * @param @return
	 * @return NetworkMonitor
	 * @throws
	 */
	public static NetworkMonitor getNetworkMonitor(InetSocketAddress address) {
		NetworkMonitor monitor = monitors.get(address);
		if (monitor == null) {
			monitors.putIfAbsent(address, new NetworkMonitor(address));
			monitor = monitors.get(address);
		}
		return monitor;
	}
	
	private final Set<NetworkListener> listeners = new CopyOnWriteArraySet<NetworkListener>();

	private final InetSocketAddress address;

	private volatile boolean connected;

    private volatile ScheduledFuture<?> future;

    private static final long DEFAULT_INTERVAL = 3000;

    private long interval = DEFAULT_INTERVAL;
    /**
     * 
     * <p>Title:</p>
     * <p>Description:</p>
     *
     * @param address
     */
	private NetworkMonitor(InetSocketAddress address) {
		this.address = address;
		this.connected = check();
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
	/**
	 * 
	 * @Title:getCheckInterval
	 * @param @return
	 * @return long
	 * @throws
	 */
    public long getCheckInterval() {
    	return interval;
    }
    /**
     * 
     * @Title:setCheckInterval
     * @param @param interval
     * @return void
     * @throws
     */
    public void setCheckInterval(long interval) {
    	this.interval = interval;
    }
    /**
     * 
     * @Title:init
     * @Description:初始化一个网络状态监视器
     * @param 
     * @return void
     * @throws
     */
	private void init() {
		if (future != null) {
			return;
		}
		synchronized (this) {
			if (future != null) { // double check
				return;
			}
			future = executor.scheduleWithFixedDelay(new Runnable() {
				public void run() {
					try {
			    		boolean status = check();
		    			if (log.isDebugEnabled()) {
			    			log.debug("Check network " + address + " status " + status);
			    		}
			    		if (connected != status) {
			    			connected = status;
							for (NetworkListener listener : listeners) {
								try {
									listener.onNetworkChanged(new NetworkEvent(this, address, status));
								} catch (Exception e) {
									log.error(e.getMessage(), e);
								}
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}, interval, interval, TimeUnit.MILLISECONDS);
		}
	}
	/**
	 * 
	 * @Title:check
	 * @Description:检查网络状态
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean check() {
		Socket socket = null;
		try {
			socket = new Socket(address.getHostName(), address.getPort());
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 * @Title:addNetworkListener
	 * @Description:添加网络监听器
	 * @param @param listener
	 * @return void
	 * @throws
	 */
	public void addNetworkListener(NetworkListener listener) {
		listeners.add(listener);
		init();
		listener.onNetworkChanged(new NetworkEvent(this, address, connected));
	}
	
	/**
	 * 
	 * @Title:removeNetworkListener
	 * @Description:删除网络监听器
	 * @param @param listener
	 * @return void
	 * @throws
	 */
	public void removeNetworkListener(NetworkListener listener) {
		listeners.remove(listener);
	}
	/**
	 * 
	 * @Title:destory
	 * @Description:销毁监听器线程
	 * @param 
	 * @return void
	 * @throws
	 */
	public void destory() {
		if (future != null && ! future.isCancelled()) {
			future.cancel(true);
		}
		monitors.remove(address);
	}
	
}

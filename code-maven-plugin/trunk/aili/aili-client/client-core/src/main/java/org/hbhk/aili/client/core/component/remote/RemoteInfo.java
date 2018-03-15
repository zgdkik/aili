package org.hbhk.aili.client.core.component.remote;

import java.net.URL;
/**
 * <b style="font-family:微软雅黑"><small>Description:远程服务器信息</small></b>   </br>
 */
public class RemoteInfo implements IRemoteInfo {
	// 远程服务的主机名，可以是IP或者域名
	private String hostName;
	// 远程应用的上下文
	private String hessianPrefix;
	// 远程服务的主机服务端口
	private int port;
	// 管理本地空闲的连接的存活时间
	private int idleTimeout;
	// 管理每一个连接等待服务端完成的最长时间，缺省为30秒
	private int waitTimeout;
	// 管理与某一个主机服务连接的会话存活时间，缺省为永远存活(-1)
	private int sessionTimeout;
	// 管理本地与某一个远程服务连接的最大数，缺省为5
	private int maxConnections;
	// 管理远程服务本地查找的UUID名称
	private String name;

	private boolean useZIP;

	private boolean useEncypt;

	private URL remoteURL;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#isUseZIP()
	 */
	@Override
	public boolean isUseZIP() {
		return useZIP;
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#setUseZIP(boolean)
	 */
	@Override
	public void setUseZIP(boolean useZIP) {
		this.useZIP = useZIP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#isUseEncypt()
	 */
	@Override
	public boolean isUseEncypt() {
		return useEncypt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#setUseEncypt(boolean
	 * )
	 */
	@Override
	public void setUseEncypt(boolean useEncypt) {
		this.useEncypt = useEncypt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#setName(java.lang
	 * .String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#getHostName()
	 */
	@Override
	public String getHostName() {
		return hostName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#setHostName(java
	 * .lang.String)
	 */
	@Override
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#getHessianPrefix()
	 */
	@Override
	public String getHessianPrefix() {
		return hessianPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#setHessianPrefix
	 * (java.lang.String)
	 */
	@Override
	public void setHessianPrefix(String hessianPrefix) {
		this.hessianPrefix = hessianPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#getPort()
	 */
	@Override
	public int getPort() {
		return (0 == port) ? 80 : port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#setPort(int)
	 */
	@Override
	public void setPort(int port) {
		this.port = port;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#getIdleTimeout()
	 */
	@Override
	public int getConnectionTimeout() {
		return (0 == idleTimeout) ? 5000 : idleTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#setIdleTimeout(int)
	 */
	@Override
	public void setConnectionTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.deppon.foss.client.component.remote.IRemoteInfo1#getWaitTimeout()
	 */
	@Override
	public int getWaitTimeout() {
		return (0 == waitTimeout) ? 5000 : waitTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#setWaitTimeout(int)
	 */
	@Override
	public void setWaitTimeout(int waitTimeout) {
		this.waitTimeout = waitTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#getMaxConnections()
	 */
	@Override
	public int getMaxConnections() {
		return (0 == maxConnections) ? 10 : maxConnections;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.deppon.foss.client.component.remote.IRemoteInfo1#setMaxConnections
	 * (int)
	 */
	@Override
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	private URL compositeRemoteURL() {
		final String host = getHostName();
		final String prefix = getHessianPrefix();
		final int hostPort = getPort();
		try {
			remoteURL = new URL("HTTP", host, hostPort, prefix);
			
			return remoteURL;
		} catch (Exception e) {
			;
		}
		return null;
	}

	@Override
	public URL getRemoteURL() {
		if (remoteURL!=null) {
			return remoteURL;
		}
		return this.compositeRemoteURL();
	}



	@Override
	public int getSessionTimeout() {
		return sessionTimeout==0?-1:sessionTimeout;
	}



	@Override
	public void setSessionTimeout(int sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

}

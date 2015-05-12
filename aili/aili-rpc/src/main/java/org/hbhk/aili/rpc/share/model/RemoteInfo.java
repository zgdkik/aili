package org.hbhk.aili.rpc.share.model;

import java.net.URL;

import org.hbhk.aili.rpc.server.hessian.remote.IRemoteInfo;
/**
*******************************************
* <b style="font-family:寰蒋闆呴粦"><small>Description:杩滅▼鏈嶅姟鍣ㄤ俊鎭�/small></b>   </br>
* <b style="font-family:寰蒋闆呴粦"><small>HISTORY</small></b></br>
* <b style="font-family:寰蒋闆呴粦"><small>
*  ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:寰蒋闆呴粦,font-size:70%"> 
* 1   2011-6-11   璋紶鍥�      鏂板
* </div>  
********************************************
 */
public class RemoteInfo implements IRemoteInfo {
	// 杩滅▼鏈嶅姟鐨勪富鏈哄悕锛屽彲浠ユ槸IP鎴栬�鍩熷悕
	private String hostName;
	// 杩滅▼搴旂敤鐨勪笂涓嬫枃
	private String hessianPrefix;
	// 杩滅▼鏈嶅姟鐨勪富鏈烘湇鍔＄鍙�
	private int port;
	// 绠＄悊鏈湴绌洪棽鐨勮繛鎺ョ殑瀛樻椿鏃堕棿
	private int idleTimeout;
	// 绠＄悊姣忎竴涓繛鎺ョ瓑寰呮湇鍔＄瀹屾垚鐨勬渶闀挎椂闂达紝缂虹渷涓�0绉�
	private int waitTimeout;
	// 绠＄悊涓庢煇涓�釜涓绘満鏈嶅姟杩炴帴鐨勪細璇濆瓨娲绘椂闂达紝缂虹渷涓烘案杩滃瓨娲�-1)
	private int sessionTimeout;
	// 绠＄悊鏈湴涓庢煇涓�釜杩滅▼鏈嶅姟杩炴帴鐨勬渶澶ф暟锛岀己鐪佷负5
	private int maxConnections;
	// 绠＄悊杩滅▼鏈嶅姟鏈湴鏌ユ壘鐨刄UID鍚嶇О
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


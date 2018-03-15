package org.hbhk.aili.client.core.component.remote;

import java.util.HashMap;
import java.util.Map;
/**
* <b style="font-family:微软雅黑"><small>Description:远程服务接口抽象类</small></b>   </br>
 */
public abstract class AbstractRemoteServerFactory implements
		IRemoteServerFactory {

	private Map<String, IRemoteServer> remoteServer = new HashMap<String, IRemoteServer>();
	private Map<String, IRemoteInfo> remoteInfos = new HashMap<String, IRemoteInfo>();
	private boolean inited;
	private IRemoteExceptionHandler exceptionhandler;

	protected abstract void parseTransportConfigurations();
	/**
	 * 
	 * <p>Title:init
	 * <p>Description:初始化远程通讯注册</p>
	 * @see org.hbhk.aili.client.core.component.remote.IRemoteServerFactory#init()
	 */
	final public void init() {
		this.parseTransportConfigurations();
		this.inited = true;
	}
	/**
	 * 
	 * @Title:addRemoteInfo
	 * @Description:添加远程信息
	 * @param @param info
	 * @return void
	 * @throws
	 */
	final protected void addRemoteInfo(IRemoteInfo info) {
		remoteInfos.put(info.getName(), info);
	}

	@Override
	public IRemoteExceptionHandler getExceptionHandler() {
		return this.exceptionhandler;
	}
	/**
	 * 
	 * <p>Title:getRemoteServer
	 * <p>Description:获得远程服务器</p>
	 * @return
	 * @see org.hbhk.aili.client.core.component.remote.IRemoteServerFactory#getRemoteServer()
	 */
	@Override
	final public IRemoteServer getRemoteServer() {
		return getRemoteServer(IRemoteInfo.DEFAULT_NAME);
	}
	/**
	 * 
	 * <p>Title:getRemoteServer
	 * <p>Description:根据远程服务器名称获得远程服务器</p>
	 * @param remoteName
	 * @return
	 * @see org.hbhk.aili.client.core.component.remote.IRemoteServerFactory#getRemoteServer(java.lang.String)
	 */
	@Override
	final public IRemoteServer getRemoteServer(String remoteName) {
		IRemoteServer rs = this.remoteServer.get(remoteName);
		if (null == rs) {
			if (!this.inited){
				throw new RemoteConfigurationException();
			}
			IRemoteInfo ri = this.remoteInfos.get(remoteName);
			rs = new RemoteServer(ri, this.exceptionhandler);
			this.remoteServer.put(remoteName, rs);
		}
		return rs;
	}
	/**
	 * 
	 * <p>Title:shutdown
	 * <p>Description:关闭所有的远程服务器</p>
	 * @see org.hbhk.aili.client.core.component.remote.IRemoteServerFactory#shutdown()
	 */
	@Override
	final public void shutdown() {
		for (Map.Entry<String, IRemoteServer> entry : remoteServer.entrySet()) {
			entry.getValue().shutdown();
		}
	}

}

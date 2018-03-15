package org.hbhk.aili.rpc.server.hessian.remote;

import java.util.HashMap;
import java.util.Map;

import org.hbhk.aili.rpc.server.hessian.handler.IRemoteExceptionHandler;
/**
*******************************************
* <b style="font-family:寰蒋闆呴粦"><small>Description:杩滅▼鏈嶅姟鎺ュ彛鎶借薄绫�/small></b>   </br>
* <b style="font-family:寰蒋闆呴粦"><small>HISTORY</small></b></br>
* <b style="font-family:寰蒋闆呴粦"><small>
*  ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:寰蒋闆呴粦,font-size:70%"> 
* 1   2011-6-11   璋紶鍥�      鏂板
* </div>  
********************************************
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
	 * <p>Description:鍒濆鍖栬繙绋嬮�璁敞鍐�/p>
	 * @see com.deppon.foss.framework.client.component.remote.IRemoteServerFactory#init()
	 */
	final public void init() {
		this.parseTransportConfigurations();
		this.inited = true;
	}
	/**
	 * 
	 * @Title:addRemoteInfo
	 * @Description:娣诲姞杩滅▼淇℃伅
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
	 * <p>Description:鑾峰緱杩滅▼鏈嶅姟鍣�/p>
	 * @return
	 * @see com.deppon.foss.framework.client.component.remote.IRemoteServerFactory#getRemoteServer()
	 */
	@Override
	final public IRemoteServer getRemoteServer() {
		return getRemoteServer(IRemoteInfo.DEFAULT_NAME);
	}
	/**
	 * 
	 * <p>Title:getRemoteServer
	 * <p>Description:鏍规嵁杩滅▼鏈嶅姟鍣ㄥ悕绉拌幏寰楄繙绋嬫湇鍔″櫒</p>
	 * @param remoteName
	 * @return
	 * @see com.deppon.foss.framework.client.component.remote.IRemoteServerFactory#getRemoteServer(java.lang.String)
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
	 * <p>Description:鍏抽棴鎵�湁鐨勮繙绋嬫湇鍔″櫒</p>
	 * @see com.deppon.foss.framework.client.component.remote.IRemoteServerFactory#shutdown()
	 */
	@Override
	final public void shutdown() {
		for (Map.Entry<String, IRemoteServer> entry : remoteServer.entrySet()) {
			entry.getValue().shutdown();
		}
	}

}

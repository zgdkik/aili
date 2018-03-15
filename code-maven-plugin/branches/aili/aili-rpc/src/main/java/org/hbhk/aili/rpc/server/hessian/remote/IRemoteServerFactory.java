package org.hbhk.aili.rpc.server.hessian.remote;

import org.hbhk.aili.rpc.server.hessian.handler.IRemoteExceptionHandler;



/**
*******************************************
* <b style="font-family:寰蒋闆呴粦"><small>Description:杩滅▼鏈嶅姟鍣ㄥ伐鍘傛帴鍙�/small></b>   </br>
* <b style="font-family:寰蒋闆呴粦"><small>HISTORY</small></b></br>
* <b style="font-family:寰蒋闆呴粦"><small>
*  ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:寰蒋闆呴粦,font-size:70%">
* 1   2011-6-11   璋紶鍥�      鏂板
* </div>  
********************************************
 */
public interface IRemoteServerFactory {
	/**
	 * 
	 * <p>Title: init</p>
	 * <p>Description: 鍒濆鍖�/p>
	 */
	void init();

	/**
	 * 
	 * <p>Title: getExceptionHandler</p>
	 * <p>Description: 鑾峰彇寮傚父澶勭悊鍣�/p>
	 * @return
	 */
	IRemoteExceptionHandler getExceptionHandler();

	/**
	 * 
	 * <p>Title: getRemoteServer</p>
	 * <p>Description: 鑾峰彇杩滅▼鏈嶅姟</p>
	 * @return
	 */
	IRemoteServer getRemoteServer();

	/**
	 * 
	 * <p>Title: getRemoteServer</p>
	 * <p>Description: 鏍规嵁鏈嶅姟鍚嶈幏鍙栬繙绋嬫湇鍔�/p>
	 * @param remoteName 鏈嶅姟鍚�
	 * @return
	 */
	IRemoteServer getRemoteServer(String remoteName);

	/**
	 * 
	 * <p>Title: shutdown</p>
	 * <p>Description: 鍏抽棴</p>
	 */
	void shutdown();
}
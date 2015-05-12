package org.hbhk.aili.rpc.server.hessian.remote;

import java.net.URL;
/**
*******************************************
* <b style="font-family:寰蒋闆呴粦"><small>Description:杩滅▼鏈嶅姟淇℃伅鎺ュ彛</small></b>   </br>
* <b style="font-family:寰蒋闆呴粦"><small>HISTORY</small></b></br>
* <b style="font-family:寰蒋闆呴粦"><small>
*  ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:寰蒋闆呴粦,font-size:70%"> 
* 1   2011-6-11   璋紶鍥�      鏂板
* </div>  
********************************************
 */
public interface IRemoteInfo {
	/**
	 * ini鏂囦欢app name:[DEFAULT]
	 */
    public final static String DEFAULT_NAME = "[DEFAULT]";
    
    /**
	 * ini鏂囦欢app name:[DOWNLOADURL] zxy 20140308 绂荤嚎
	 */
    public final static String DOWNLOAD_NAME = "[DOWNLOADURL]";
    
    /**
     * 
     * <p>Title: isUseZIP</p>
     * <p>Description: </p>
     * @return
     */
    public abstract boolean isUseZIP();
    
    /**
     * 
     * <p>Title: setUseZIP</p>
     * <p>Description: </p>
     * @param useZIP
     */
    public abstract void setUseZIP(boolean useZIP);
    
    /**
     * 
     * <p>Title: isUseEncypt</p>
     * <p>Description: 鏄惁鍔犲瘑</p>
     * @return 甯冨皵鍊硷紝true:鏄紝false:鍚�
     */
    public abstract boolean isUseEncypt();
    
    /**
     * 
     * <p>Title: setUseEncypt</p>
     * <p>Description: 璁剧疆鏄惁鍔犲瘑</p>
     * @param useEncypt 甯冨皵鍊硷紝true:鏄紝false:鍚�
     */
    public abstract void setUseEncypt(boolean useEncypt);
    
    /**
     * 
     * <p>Title: getName</p>
     * <p>Description: 鑾峰彇杩滅▼鏈嶅姟鍚嶇О</p>
     * @return 杩滅▼鏈嶅姟鍚嶇О
     */
    public abstract String getName();
    
    /**
     * 
     * <p>Title: setName</p>
     * <p>Description: 璁剧疆杩滅▼鏈嶅姟鍚嶇О</p>
     * @param name 杩滅▼鏈嶅姟鍚嶇О
     */
    public abstract void setName(String name);
    
    /**
     * 
     * <p>Title: getHostName</p>
     * <p>Description: 鑾峰彇杩滅▼杩炴帴IP</p>
     * @return 杩滅▼杩炴帴IP
     */
    public abstract String getHostName();
    
    /**
     * 
     * <p>Title: setHostName</p>
     * <p>Description: 璁剧疆杩滅▼杩炴帴IP</p>
     * @param hostName 杩滅▼杩炴帴IP
     */
    public abstract void setHostName(String hostName);
    
    /**
     * 
     * <p>Title: getHessianPrefix</p>
     * <p>Description: 鑾峰彇hessian鍓嶇紑</p>
     * @return hessian鍓嶇紑
     */
    public abstract String getHessianPrefix();
    
    /**
     * 
     * <p>Title: setHessianPrefix</p>
     * <p>Description: 璁剧疆hessian鍓嶇紑</p>
     * @param hessianPrefix hessian鍓嶇紑
     */
    public abstract void setHessianPrefix(String hessianPrefix);
    
    /**
     * 
     * <p>Title: getPort</p>
     * <p>Description: 鑾峰彇绔彛鍙�/p>
     * @return 绔彛鍙�
     */
    public abstract int getPort();
    
    /**
     * 
     * <p>Title: setPort</p>
     * <p>Description: 璁剧疆绔彛鍙�/p>
     * @param port 绔彛鍙�
     */
    public abstract void setPort(int port);
    
    /**
     * 
     * <p>Title: getRemoteURL</p>
     * <p>Description: 鑾峰彇杩滅▼杩炴帴鍦板潃</p>
     * @return 杩滅▼杩炴帴鍦板潃
     */
    public URL getRemoteURL();
    
    /**
     * 
     * <p>Title: getConnectionTimeout</p>
     * <p>Description: 鑾峰彇杩炴帴瓒呮椂鏃堕棿</p>
     * @return 杩炴帴瓒呮椂鏃堕棿
     */
    public abstract int getConnectionTimeout();
    
    /**
     * 
     * <p>Title: setConnectionTimeout</p>
     * <p>Description: 璁剧疆杩炴帴瓒呮椂鏃堕棿</p>
     * @param connectionTimeout 杩炴帴瓒呮椂鏃堕棿
     */
    public abstract void setConnectionTimeout(int connectionTimeout);
    
    /**
     * 
     * <p>Title: getWaitTimeout</p>
     * <p>Description: 鑾峰彇绛夊緟瓒呮椂鏃堕棿</p>
     * @return 绛夊緟瓒呮椂鏃堕棿
     */
    public abstract int getWaitTimeout();
    
    /**
     * 
     * <p>Title: setWaitTimeout</p>
     * <p>Description: 璁剧疆绛夊緟瓒呮椂鏃堕棿</p>
     * @param waitTimeout 绛夊緟瓒呮椂鏃堕棿
     */
    public abstract void setWaitTimeout(int waitTimeout);
    
    /**
     * 
     * <p>Title: getMaxConnections</p>
     * <p>Description: 鑾峰彇鏈�ぇ杩炴帴鏁�/p>
     * @return 鏈�ぇ杩炴帴鏁�
     */
    public abstract int getMaxConnections();
    
    /**
     * 
     * <p>Title: setMaxConnections</p>
     * <p>Description: 璁剧疆鏈�ぇ杩炴帴鏁�/p>
     * @param maxConnections 鏈�ぇ杩炴帴鏁�
     */
    public abstract void setMaxConnections(int maxConnections);
    
    /**
     * 
     * <p>Title: getSessionTimeout</p>
     * <p>Description: 鑾峰彇浼氳瘽瓒呮椂鏃堕棿</p>
     * @return 浼氳瘽瓒呮椂鏃堕棿
     */
    int getSessionTimeout();
    
    /**
     * 
     * <p>Title: setSessionTimeout</p>
     * <p>Description: 璁剧疆浼氳瘽瓒呮椂鏃堕棿</p>
     * @param sessionTimeout 浼氳瘽瓒呮椂鏃堕棿
     */
    void setSessionTimeout(int sessionTimeout);
}
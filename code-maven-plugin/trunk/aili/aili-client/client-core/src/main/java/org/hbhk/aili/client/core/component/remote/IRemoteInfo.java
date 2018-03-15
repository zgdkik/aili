package org.hbhk.aili.client.core.component.remote;

import java.net.URL;
/**
* <b style="font-family:微软雅黑"><small>Description:远程服务信息接口</small></b>   </br>
 */
public interface IRemoteInfo {
	/**
	 * ini文件app name:[DEFAULT]
	 */
    public final static String DEFAULT_NAME = "[DEFAULT]";
    
    /**
	 * ini文件app name:[DOWNLOADURL] zxy 20140308 离线
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
     * <p>Description: 是否加密</p>
     * @return 布尔值，true:是，false:否
     */
    public abstract boolean isUseEncypt();
    
    /**
     * 
     * <p>Title: setUseEncypt</p>
     * <p>Description: 设置是否加密</p>
     * @param useEncypt 布尔值，true:是，false:否
     */
    public abstract void setUseEncypt(boolean useEncypt);
    
    /**
     * 
     * <p>Title: getName</p>
     * <p>Description: 获取远程服务名称</p>
     * @return 远程服务名称
     */
    public abstract String getName();
    
    /**
     * 
     * <p>Title: setName</p>
     * <p>Description: 设置远程服务名称</p>
     * @param name 远程服务名称
     */
    public abstract void setName(String name);
    
    /**
     * 
     * <p>Title: getHostName</p>
     * <p>Description: 获取远程连接IP</p>
     * @return 远程连接IP
     */
    public abstract String getHostName();
    
    /**
     * 
     * <p>Title: setHostName</p>
     * <p>Description: 设置远程连接IP</p>
     * @param hostName 远程连接IP
     */
    public abstract void setHostName(String hostName);
    
    /**
     * 
     * <p>Title: getHessianPrefix</p>
     * <p>Description: 获取hessian前缀</p>
     * @return hessian前缀
     */
    public abstract String getHessianPrefix();
    
    /**
     * 
     * <p>Title: setHessianPrefix</p>
     * <p>Description: 设置hessian前缀</p>
     * @param hessianPrefix hessian前缀
     */
    public abstract void setHessianPrefix(String hessianPrefix);
    
    /**
     * 
     * <p>Title: getPort</p>
     * <p>Description: 获取端口号</p>
     * @return 端口号
     */
    public abstract int getPort();
    
    /**
     * 
     * <p>Title: setPort</p>
     * <p>Description: 设置端口号</p>
     * @param port 端口号
     */
    public abstract void setPort(int port);
    
    /**
     * 
     * <p>Title: getRemoteURL</p>
     * <p>Description: 获取远程连接地址</p>
     * @return 远程连接地址
     */
    public URL getRemoteURL();
    
    /**
     * 
     * <p>Title: getConnectionTimeout</p>
     * <p>Description: 获取连接超时时间</p>
     * @return 连接超时时间
     */
    public abstract int getConnectionTimeout();
    
    /**
     * 
     * <p>Title: setConnectionTimeout</p>
     * <p>Description: 设置连接超时时间</p>
     * @param connectionTimeout 连接超时时间
     */
    public abstract void setConnectionTimeout(int connectionTimeout);
    
    /**
     * 
     * <p>Title: getWaitTimeout</p>
     * <p>Description: 获取等待超时时间</p>
     * @return 等待超时时间
     */
    public abstract int getWaitTimeout();
    
    /**
     * 
     * <p>Title: setWaitTimeout</p>
     * <p>Description: 设置等待超时时间</p>
     * @param waitTimeout 等待超时时间
     */
    public abstract void setWaitTimeout(int waitTimeout);
    
    /**
     * 
     * <p>Description: 获取最大连接数</p>
     */
    public abstract int getMaxConnections();
    
    /**
     * 
     * <p>Description: 设置最大连接数</p>
     */
    public abstract void setMaxConnections(int maxConnections);
    
    /**
     * 
     * <p>Description: 获取会话超时时间</p>
     */
    int getSessionTimeout();
    
    /**
     * 会话超时时间
     */
    void setSessionTimeout(int sessionTimeout);
}
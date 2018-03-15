package org.hbhk.aili.client.core.component.transport;
/**
* <b style="font-family:微软雅黑"><small>Description:传输信息接口</small></b>   </br>
 */
public interface ITransportInfo {
    /**
     * 
     * @return
     */
    public abstract boolean isUseZIP();
    
    /**
     * 
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
     * <p>Title: getUUID</p>
     * <p>Description: 获取UUID</p>
     * @return
     */
    public abstract String getUUID();
    
    /**
     * 
     * <p>Title: setUUID</p>
     * <p>Description: 设置UUID</p>
     * @param name UUID
     */
    public abstract void setUUID(String name);
    
    /**
     * 
     * <p>Title: getConnectionTimeout</p>
     * <p>Description: 获取连接超时时间</p>
     * @return
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
     * @return
     */
    public abstract int getWaitTimeout();
    
    /**
     * 
     * <p>Title: setWaitTimeout</p>
     * <p>Description: 设置等待超时时间</p>
     * @param waitTimeout 超时时间
     */
    public abstract void setWaitTimeout(int waitTimeout);
    
    /**
     * 
     * <p>Title: getMaxConnections</p>
     * <p>Description: 获取最大连接数</p>
     * @return
     */
    public abstract int getMaxConnections();
    
    /**
     * 
     * <p>Title: setMaxConnections</p>
     * <p>Description: 设置最大连接数</p>
     * @param maxConnections 最大连接数
     */
    public abstract void setMaxConnections(int maxConnections);
}
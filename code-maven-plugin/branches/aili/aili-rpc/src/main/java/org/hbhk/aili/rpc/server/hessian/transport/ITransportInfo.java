package org.hbhk.aili.rpc.server.hessian.transport;

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
     * <p>Title: getUUID</p>
     * <p>Description: 鑾峰彇UUID</p>
     * @return
     */
    public abstract String getUUID();
    
    /**
     * 
     * <p>Title: setUUID</p>
     * <p>Description: 璁剧疆UUID</p>
     * @param name UUID
     */
    public abstract void setUUID(String name);
    
    /**
     * 
     * <p>Title: getConnectionTimeout</p>
     * <p>Description: 鑾峰彇杩炴帴瓒呮椂鏃堕棿</p>
     * @return
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
     * @return
     */
    public abstract int getWaitTimeout();
    
    /**
     * 
     * <p>Title: setWaitTimeout</p>
     * <p>Description: 璁剧疆绛夊緟瓒呮椂鏃堕棿</p>
     * @param waitTimeout 瓒呮椂鏃堕棿
     */
    public abstract void setWaitTimeout(int waitTimeout);
    
    /**
     * 
     * <p>Title: getMaxConnections</p>
     * <p>Description: 鑾峰彇鏈�ぇ杩炴帴鏁�/p>
     * @return
     */
    public abstract int getMaxConnections();
    
    /**
     * 
     * <p>Title: setMaxConnections</p>
     * <p>Description: 璁剧疆鏈�ぇ杩炴帴鏁�/p>
     * @param maxConnections 鏈�ぇ杩炴帴鏁�
     */
    public abstract void setMaxConnections(int maxConnections);
}

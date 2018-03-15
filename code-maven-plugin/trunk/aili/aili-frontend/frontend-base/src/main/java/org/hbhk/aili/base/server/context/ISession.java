package org.hbhk.aili.base.server.context;

public interface ISession<V> {
    /**
     * 设置真实session
     */
    void init(javax.servlet.http.HttpSession session);
    
    /**
     * 设置session属性
     */
    void setObject(String k, V v);
    
    /**
     * 读取session属性
     */
    V getObject(String k);
    
    /**
     * session失效
     */
    void invalidate();
    
    /**
     * 获取sessionId
     */
    String getId();
    
    /**
     * 获取session有效期
     */
    int getMaxInactiveInterval();
}

package org.hbhk.aili.client.core.component.transport.exception;

import java.io.IOException;

/**
 * 由于网络连通故障，不允许远程操作的异常定义
 */
public class NetworkNotAllowException extends IOException {
    /**
     * 
     * <p>Title: NetworkNotAllowException</p>
     * <p>Description: 默认构造函数</p>
     */
    public NetworkNotAllowException(){ 
    }
    
    /**
     * 
     * <p>Title: NetworkNotAllowException</p>
     * <p>Description: 构造函数</p>
     * @param cause 异常原因
     */
    public NetworkNotAllowException(Throwable cause) {
        super(cause);
    }
    
    private static final long serialVersionUID = 106412932415322214L;
    
}

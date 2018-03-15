package org.hbhk.aili.rpc.server.hessian.handler;

public interface IRemoteExceptionHandler {
    /**
     * 
     * <p>Title: handle</p>
     * <p>Description: 瀵煎父澶勭悊</p>
     * @param t 寮傚父
     */
    void handle(Throwable t);
}
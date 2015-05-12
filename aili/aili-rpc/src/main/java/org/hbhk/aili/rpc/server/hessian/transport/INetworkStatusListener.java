package org.hbhk.aili.rpc.server.hessian.transport;

import org.hbhk.aili.rpc.server.hessian.client.NetworkStatus;

public interface INetworkStatusListener {
    /**
     * 
     * @Title:onStatusChange
     * @Description:褰撶綉缁滅姸鎬佸彂鐢熸敼鍙樻椂锛屼慨鏀圭綉缁滅姸鎬�
     * @param @param status 缃戠粶鐘舵�
     * @return void
     * @throws
     */
    void onStatusChange(NetworkStatus status);
}
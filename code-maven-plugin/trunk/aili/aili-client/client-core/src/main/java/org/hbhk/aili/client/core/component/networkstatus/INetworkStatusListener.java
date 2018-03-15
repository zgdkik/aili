package org.hbhk.aili.client.core.component.networkstatus;

/**
* <b style="font-family:微软雅黑"><small>Description:网络状态监控器接口</small></b>   </br>
 */
public interface INetworkStatusListener {
    /**
     * 
     * @Title:onStatusChange
     * @Description:当网络状态发生改变时，修改网络状态
     * @param @param status 网络状态
     * @return void
     * @throws
     */
    void onStatusChange(NetworkStatus status);
}
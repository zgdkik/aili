package org.hbhk.aili.client.core.component.sync;
/**
* <b style="font-family:微软雅黑"><small>Description:同步数据状态监听器接口</small></b>   </br>
 */
public interface ISyncDataStatusListener {
	/**
	 * 
	 * <p>Title: onStatusChange</p>
	 * <p>Description: 状态更改处理</p>
	 * @param message 消息
	 */
	void onStatusChange(String message);
}

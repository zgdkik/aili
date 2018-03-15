package org.hbhk.aili.client.core.component.remote;

/**
 * <b style="font-family:微软雅黑"><small>Description:远程服务异常处理器</small></b> </br>
 */
public interface IRemoteExceptionHandler {
	/**
	 * Description: 导常处理
	 */
	void handle(Throwable t);
}

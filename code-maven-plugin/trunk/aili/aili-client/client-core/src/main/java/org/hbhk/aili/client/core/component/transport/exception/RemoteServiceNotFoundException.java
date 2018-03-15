package org.hbhk.aili.client.core.component.transport.exception;

import java.io.IOException;
/**
* <b style="font-family:微软雅黑"><small>Description:远程服务不存在异常</small></b>   </br>
 */
public class RemoteServiceNotFoundException extends IOException {
	/**
	 * 
	 * <p>Title: RemoteServiceNotFoundException</p>
	 * <p>Description: 构造函数</p>
	 * @param cause 异常原因
	 */
    public RemoteServiceNotFoundException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 
     */
    private static final long serialVersionUID = 3603211514532188897L; 
}
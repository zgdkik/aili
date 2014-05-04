package org.hbhk.module.framework.server.exception;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:异常接口</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
 */
public interface IException {
    
    String getErrorCode();
    
    String getNativeMessage();
    
    void setErrorArguments(Object... objects);
    
    Object[] getErrorArguments();
    
}

package org.hbhk.module.framework.server.exception;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:未知异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
public final class UnknowGeneralException extends GeneralException {
    
    private static final long serialVersionUID = -7547624267762545457L;
    public static final String ERROR_CODE = "errror.common.unknow";
    
    public UnknowGeneralException(String msg) {
        super(msg);
        this.errCode = ERROR_CODE;
    }
    public UnknowGeneralException(String msg, Throwable t) {
        super(msg,t);
        this.errCode = ERROR_CODE;
        this.setStackTrace(t.getStackTrace());
    }
   
}

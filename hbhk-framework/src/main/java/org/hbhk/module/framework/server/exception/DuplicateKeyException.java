package org.hbhk.module.framework.server.exception;

/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:唯一键重复异常</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1 2011-4-9 steven.cheng 新增
* </div>  
********************************************
 */
public final class DuplicateKeyException extends GeneralException {
    
    private static final long serialVersionUID = 1L;
    public static final String ERROR_CODE = "errror.common.duplicatekey";
    
    public DuplicateKeyException(Throwable t) {
        super(t);
        this.errCode = ERROR_CODE;
    }
    
}

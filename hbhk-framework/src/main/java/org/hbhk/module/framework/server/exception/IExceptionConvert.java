package org.hbhk.module.framework.server.exception;


/**
 * 
 * 
*******************************************
* <b style="font-family:微软雅黑"><small>Description:异常转化接口定义</small></b>   </br>
* <b style="font-family:微软雅黑"><small>HISTORY</small></b></br>
* <b style="font-family:微软雅黑"><small> ID      DATE    PERSON     REASON</small></b><br>
********************************************
* <div style="font-family:微软雅黑,font-size:70%"> 
* 1  2011-3-27  steven.cheng 新增
* </div>  
********************************************
 */
public interface IExceptionConvert {
    /**
     * 转化成GeneralException
     * convert
     * @param target
     * @return
     * @return GeneralException
     * @since:0.6
     */
    GeneralException convert(Throwable target);
    
    /**
     * 转化成Exception
     * nativeConvert
     * @param target
     * @return
     * @return Exception
     * @since:0.6
     */
    Exception nativeConvert(Throwable target);
    
    /**
     * 转化成字符串
     * @param target
     * @return String
     * @throws 
     * @author ningyu
     */
    String parseExceptionMessage(Throwable target);
}
